import java.io.IOException;

public class Game {

    private Display display;

    private PhysPlayground physPlayground;

    public Game(String type) {
        Position position = new Position(5, 2);
        physPlayground = new PhysPlayground(20, 20, position);

        if (type.equals("lanterna") || type.equals(""))
            display = new LanternaDisplay(physPlayground);
        else if (type.equals("swing"))
            display = new SwingDisplay((physPlayground));
        else
        {
            System.out.println("Error: unknown parameter. Starting with default Lanterna build.");
            display = new LanternaDisplay(physPlayground);
        }


        display.start();
    }

    public void run() {
        new Thread(() -> {
            try {
                while (display.getRun()) {
                    display.update();
                    display.draw();
                    Thread.sleep(10);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() ->
                display.keyStrokeListener()).start();
    }
}
