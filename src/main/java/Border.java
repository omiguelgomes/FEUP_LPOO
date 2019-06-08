import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Border extends PhysElement
{
    public Border (Position position)
    {
        super(position);
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString((new TerminalPosition(position.getX(), position.getY())), ":");
    }
}