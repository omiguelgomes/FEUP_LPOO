import java.util.*;

public class PhysPlayground {
    private int width, height;
    private int xBorder, yBorder;
    private int playerScore, playerLevel;
    private boolean run;

    private PhysPlayer physPlayer;
    private PhysBall ball;
    private PhysPowerUp powerUp;
    private List<Position> borders;
    private PhysWall wall;

    private Thread animation;

    public PhysPlayground(int w, int h, Position position) {
        this.width = w;
        this.height = h;
        this.xBorder = position.getX();
        this.yBorder = position.getY();
        this.playerScore = 0;
        this.playerLevel = 1;
        this.run = false;

        physPlayer = new PhysPlayer(getInitialPosition());
        ball = new PhysBall(physPlayer.getCenteredPosition());
        wall = new PhysWall(width, new Position(xBorder, yBorder), this.playerLevel);
        powerUp = new PhysNullPowerUp(new Position(0, 0));

        resetAnimation();
    }

    private Position getInitialPosition() {
        int x = xBorder + width / 2 - 2;
        int y = yBorder + height - 1;

        return new Position(x, y);
    }

    private boolean canPlayerMove(Position position) {
        if (position.getX() < xBorder) return false;
        if (position.getX() + physPlayer.getLength() > width + xBorder - 1) return false;

        return true;
    }

    private void movePlayer(Position pos) {
        if (canPlayerMove(pos))
            physPlayer.setPosition(pos);

        if (ball.isIdle())
            ball.setPosition(physPlayer.getCenteredPosition());
    }

    private boolean verifyBallCollision(Position pos) {

        //block collision
        for (PhysBlock block : this.wall.getBlocks()) {
            Position collision = block.getPosition();

            if (pos.equals(collision))
            {
                ball.processCollision(block);
                wall.collide(collision);

                if (powerUp.isNull())
                    this.powerUp = wall.getPowerUp();

                //only updates score when block dies
                if (block.getLevel() == 1)
                {
                    playerScore += block.getScore();
                }

                if (this.wall.getBlocks().size() == 0)
                {
                    nextLevel();
                    return false;
                }

                return true;
            }

        }

        //border collision
        for (Position position : borders) {

            if (pos.equals(position))
            {
                if (position.getX() == xBorder - 1 || position.getX() == xBorder + width) {
                    ball.processCollision(position, true);
                } else if (position.getY() == yBorder - 1) {
                    ball.processCollision(position, false);
                } else if (position.getY() == yBorder + height) {
                    if (this.physPlayer.getLives() <= 1) {
                        gameOver();
                    } else if (this.physPlayer.getLives() > 1) {
                        loseLife();
                    }
                }
                return true;
            }
        }

        //player collision
        if (pos.getY() == yBorder + height - 1) //ball is in the last row
        {
            int x = physPlayer.getPosition().getX();

            for (int i = x; i <= x + physPlayer.getLength(); i++) {
                if (pos.getX() == i) {
                    ball.processCollision(physPlayer.getAcceleration(), x + 1 - i);
                    return true;
                }
            }
        }

        return false;
    }

    private void moveBall(Position pos) {
        Position nextPos = pos;

        while (verifyBallCollision(nextPos)) {
            nextPos = ball.move();
        }

        ball.setPosition(nextPos);
    }

    private boolean verifyPowerUpCollision(Position pos)
    {
        if (pos.getY() == yBorder + height)
        {
            powerUp = new PhysNullPowerUp(new Position(0,0));
            return false;
        }

        if (pos.getY() == yBorder + height - 1)
        {
            int x = physPlayer.getPosition().getX();

            for (int i = x; i <= x + physPlayer.getLength(); i++)
            {
                if (pos.getX() == i)
                {
                    powerUp.affects(physPlayer);
                    powerUp = new PhysNullPowerUp(new Position(0,0));
                    return false;
                }
            }
        }

        return true;
    }

    private void resetAnimation() {

        this.animation = new Thread(() ->
        {
            {
                while (run) {
                    updateBall();
                    updatePowerUp();
                    try {
                        Thread.sleep(110 * ball.getSpeed());
                    } catch (InterruptedException e)
                    {}
                }
            }
        });
    }

    private void updatePowerUp()
    {
        Position pos = powerUp.move();
        if (verifyPowerUpCollision(pos))
            powerUp.setPosition(pos);
    }

    private void updateBall() {
        moveBall(ball.move());
    }

    public void processKey(String move) {
        if (move.equals("left"))
            movePlayer(physPlayer.moveLeft());
        else if (move.equals("right"))
            movePlayer(physPlayer.moveRight());
        else if (move.equals("shoot") && ball.isIdle()) {
            ball.switchIdle();
            this.run = true;
            animation.start();
        }
    }

    public Position getPlayer() {
        return physPlayer.getPosition();
    }

    public Position getBall() {
        return ball.getPosition();
    }

    public List<PhysBlock> getWall() {
        List<PhysBlock> copy = new ArrayList<>(wall.getBlocks());

        return copy;
    }

    public int getPlayerScore() {
        return this.playerScore;
    }

    public int getPlayerLevel() {
        return this.playerLevel;
    }

    public int getPlayerLives() {
        return this.physPlayer.getLives();
    }

    public Position getPowerUp(){
        return this.powerUp.getPosition();
    }

    public void stop() {
        this.run = false;
    }

    public List<Position> createBorders() {
        borders = new ArrayList<>();

        for (int c = 0; c < width; c++
        ) {
            borders.add(new Position(c + xBorder, yBorder - 1));
            borders.add(new Position(c + xBorder, yBorder + height));
        }

        for (int r = -1; r < height + 1; r++) {
            borders.add(new Position(xBorder - 1, yBorder + r));
            borders.add(new Position(xBorder + width, yBorder + r));
        }

        return borders;
    }

    public void nextLevel() {
        this.playerLevel++;
        this.wall = new PhysWall(this.width, new Position(xBorder, yBorder), this.playerLevel);

        resetObjects();
    }

    public void gameOver() {
        this.playerLevel = 1;
        this.playerScore = 0;
        this.physPlayer.resetLives();
        this.wall = new PhysWall(this.width, new Position(xBorder, yBorder), this.playerLevel);

        resetObjects();
    }

    public void loseLife()
    {
        physPlayer.decrementLives();
        resetObjects();
    }

    private void resetObjects() {
        this.physPlayer.setPosition(getInitialPosition());
        this.ball.reset(physPlayer.getCenteredPosition());
        this.run = false;
        animation.interrupt();
        this.resetAnimation();

    }
}