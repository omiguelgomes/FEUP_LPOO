public class PhysNullPowerUp extends PhysPowerUp
{
    public PhysNullPowerUp(Position position)
    {
        super(position);
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public void affects(PhysPlayer player) {}

    @Override
    public Position move()
    {
        return new Position(0,0);
    }
}
