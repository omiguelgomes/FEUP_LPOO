import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Ball
{
    Position position;

    public Ball (Position pos)
    {
        this.position = pos;
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "O");
    }
}
