import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Block extends PhysElement
{
    protected String tag;

    public Block(Position position)
    {
        super(position);
    }

    public abstract void draw(TextGraphics graphics);
}
