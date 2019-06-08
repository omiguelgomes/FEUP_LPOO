import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class SuperBlock extends Block
{
    public SuperBlock(Position position)
    {
        super(position);
        this.tag = "C";
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA500"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), tag);
    }
}