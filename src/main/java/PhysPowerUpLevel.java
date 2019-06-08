public class PhysPowerUpLevel extends PhysPowerUp
{
    private int speed;

    public PhysPowerUpLevel(Position position){
        super(position);
        this.j = 0;
        this.speed = 1;
    }


    public boolean isNull(){
        return false;
    }

    @Override
    public void affects(PhysPlayer player)
    {
        player.incrementLives();
    }

    @Override
    public Position move() {
        return new Position(position.getX(), position.getY() + (1*j));
    }
}
