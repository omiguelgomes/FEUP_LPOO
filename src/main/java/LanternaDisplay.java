import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class LanternaDisplay implements Display {
    private Screen screen;
    private PhysPlayground physPlayground;
    private Playground playground;
    private boolean run;

    public LanternaDisplay (PhysPlayground physPlayground)
    {
        run = true;
        this.physPlayground = physPlayground;

        Position position = new Position(5,2);
        playground = new Playground(20,20, position);
        playground.setBorders(physPlayground.createBorders());
    }

    @Override
    public void start()
    {
        try
        {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void keyStrokeListener()
    {
        while(run)
        {
            try {
                KeyStroke key = screen.readInput();

                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
                    this.run = false;
                    physPlayground.stop();
                    screen.close();
                } else if (key.getKeyType() == KeyType.EOF) break;

                processKey(key);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update()
    {
        playground.setPlayer(physPlayground.getPlayer());
        playground.setBall(physPlayground.getBall());
        playground.setWall(physPlayground.getWall());
        playground.setPlayerScore(physPlayground.getPlayerScore());
        playground.setPlayerLevel(physPlayground.getPlayerLevel());
        playground.setPlayerLives(physPlayground.getPlayerLives());
        playground.setPowerUp(physPlayground.getPowerUp());
    }

    public void draw() throws IOException
    {
        screen.clear();
        playground.draw(this.screen.newTextGraphics());
        screen.refresh();
    }

    @Override
    public boolean getRun()
    {
        return run;
    }

    public void processKey(KeyStroke key)
    {
        if (key.getKeyType() == KeyType.ArrowLeft) physPlayground.processKey("left");
        if (key.getKeyType() == KeyType.ArrowRight) physPlayground.processKey("right");
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == ' ') physPlayground.processKey("shoot");
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'a') physPlayground.nextLevel();

    }
}
