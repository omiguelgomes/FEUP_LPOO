import java.io.IOException;

public interface Display
{
    void start();

    void keyStrokeListener();

    void update();

    boolean getRun();

    void draw() throws IOException;
}
