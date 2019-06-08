import com.googlecode.lanterna.graphics.TextGraphics;

public class NullPowerUp extends PowerUp
{

    public NullPowerUp(Position position) {
        super(position);
    }

    @Override
    public void draw(TextGraphics graphics) {}

    public boolean isNull()
    {
        return true;
    }
}
