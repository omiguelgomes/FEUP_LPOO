public class PhysPlayer extends PhysElement
{
    private int length, playerLives;
    private Position priorPosition;

    public PhysPlayer (Position pos)
    {
        super(pos);
        priorPosition = pos;
        length = 2;
        resetLives();
    }

    public Position moveLeft()
    {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight()
    {
        return new Position(position.getX() + 1, position.getY());
    }

    public int getLength()
    {
        return length;
    }

    public Position getCenteredPosition()
    {
        int center = length / 2;

        Position newPosition = new Position(position.getX() + center, position.getY() - 1);

        return newPosition;
    }

    @Override
    public void setPosition(Position position)
    {
        priorPosition = this.position;
        super.setPosition(position);
    }

    public int getAcceleration()
    {
        int ac = priorPosition.getX() - this.position.getX();

        return ac;
    }

    public int getLives()
    {
        return this.playerLives;
    }

    public void decrementLives()
    {
        playerLives--;
    }

    public void incrementLives()
    {
        playerLives++;
    }

    public void resetLives()
    {
        playerLives = 3;
    }
}