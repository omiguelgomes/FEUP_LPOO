import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class PowerUp extends PhysElement {
    protected String tag;

    public PowerUp(Position position) {
        super(position);
    }

    public abstract void draw(TextGraphics graphics);

    public abstract boolean isNull();
}