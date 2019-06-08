public abstract class PhysPowerUp extends PhysElement
{
    protected String tag;
    protected boolean isNull;
    protected int j;

    public PhysPowerUp(Position position)
    {
        super(position);
        this.j = 1;
    }

    public String getTag(){return tag;}

    public abstract boolean isNull();

    public abstract void affects(PhysPlayer player);

    public abstract Position move();
}
