import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class PowerUpLevel extends PowerUp{

    public PowerUpLevel(Position position){
        super(position);
        this.tag = "L";
    }

    public void draw(TextGraphics graphics)
    {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF00FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), tag);
    }

    @Override
    public boolean isNull() {
        return false;
    }
}
