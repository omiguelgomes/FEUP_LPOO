import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Player
{
    private Position position;
    private int length;

    public Player (Position position)
    {
        this.position = position;
        length = 3;
    }

    public void draw(TextGraphics graphics)
    {
        String paddle = "";

        for (int i = 0; i < length; i++)
            paddle += "A";

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), paddle);
    }
}
