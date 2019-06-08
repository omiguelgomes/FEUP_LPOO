import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class MegaBlock extends Block
{
    public MegaBlock(Position position)
    {
        super(position);
        this.tag = "D";
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), tag);
    }
}