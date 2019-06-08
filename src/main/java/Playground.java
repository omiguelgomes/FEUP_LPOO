import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.*;

public class Playground
{
    private int width, height;
    private int xBorder, yBorder;
    private int playerScore, playerLevel, playerLives;

    private Player player;
    private List<Block> wall;
    private Ball ball;
    private PowerUp powerUp;
    private List<Border> borders;

    public Playground (int w, int h, Position position)
    {
        this.width = w;
        this.height = h;
        this.xBorder = position.getX();
        this.yBorder = position.getY();
        this.playerScore = 0;
        this.playerLevel = 1;
        this.playerLives = 3;

        this.wall = new ArrayList<>();
        this.borders = new ArrayList<>();
    }

    private void drawScore(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 0), "Score:");
        graphics.putString(new TerminalPosition(xBorder + width + 11, yBorder + 0), "" + playerScore);
    }

    private void drawControls(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 3), "Left & Right arrow keys" +
                ": move left and right.");
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 4), "[Space]: shoot the ball.");
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 5), "'A': increment level (for testing purposes.");
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 6), "'Q': quit the game (all progress will be lost).");
    }

    private void drawLevel(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder - 1), "Level:");
        graphics.putString(new TerminalPosition(xBorder + width + 11, yBorder - 1),  "" + playerLevel);
    }

    private void drawLives(TextGraphics graphics)
    {
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(xBorder + width + 5, yBorder + 1 ), "Lives:");
        graphics.putString(new TerminalPosition(xBorder + width + 11, yBorder + 1 ),  "" + playerLives);
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(xBorder, yBorder), new TerminalSize(width, height), ' ');

        drawScore(graphics);
        drawLevel(graphics);
        drawLives(graphics);
        drawControls(graphics);

        for (Border border : borders)
            border.draw(graphics);

        Iterator<Block> it = wall.iterator();
        while (it.hasNext())
        {
            Block next = it.next();
            next.draw(graphics);
        }

        powerUp.draw(graphics);

        player.draw(graphics);

        ball.draw(graphics);
    }

    public void setPlayer(Position pos)
    {
        this.player = new Player(pos);
    }

    public void setBall(Position pos)
    {
        this.ball = new Ball(pos);
    }

    public void setPowerUp(Position pos)
    {
        Position nill = new Position(0,0);

        if(nill.equals(pos))
            this.powerUp = new NullPowerUp(pos);
        else {
            this.powerUp = new PowerUpLevel(pos);
        }
    }

    public void setBorders(List<Position> borders) {

        this.borders = borderParser(borders);
    }

    public void setWall(List<PhysBlock> blocks)
    {
        this.wall = wallParser(blocks);
    }

    private List<Border> borderParser (List<Position> borders)
    {
        List<Border> lista = new ArrayList<>();

        for (Position position : borders)
        {
            lista.add(new Border(position));
        }

        return lista;
    }

    private List<Block> wallParser(List<PhysBlock> blocks)
    {
        List<Block> lista = new ArrayList<>();

        for (PhysBlock block : blocks)
        {
            if(block.getTag().equals("B"))
            {
               lista.add(new NormalBlock(block.getPosition()));
            }

            if(block.getTag().equals("C"))
            {
                lista.add(new SuperBlock(block.getPosition()));
            }

            if(block.getTag().equals("D"))
            {
                lista.add(new MegaBlock(block.getPosition()));
            }
        }

        return lista;
    }

    public void setPlayerScore(int score)
    {
        this.playerScore = score;
    }

    public void setPlayerLives(int lives)
    {
        this.playerLives = lives;
    }

    public void setPlayerLevel(int level){this.playerLevel = level;}

}