import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingDisplay implements Display, KeyListener
{
    private SwingPlayground playground;
    private PhysPlayground physPlayground;
    private JFrame myFrame;
    private boolean run, stop, left, right, shoot, quit;
    private int sWidth, sHeight;

    public SwingDisplay(PhysPlayground physPlayground)
    {
        sWidth = 800;
        sHeight = 600;

        Position position = new Position(5,2);
        playground = new SwingPlayground(20,20, position, sWidth, sHeight);
        playground.setBorders(physPlayground.createBorders());

        myFrame = new JFrame();

        this.physPlayground = physPlayground;
        this.run = true;
        stop = false;
        left = false;
        right = false;
        shoot = false;
        quit = false;
    }

    @Override
    public void start()
    {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setUndecorated(false);
        myFrame.setSize(sWidth, sHeight);
        myFrame.setResizable(false);
        myFrame.addKeyListener(this);
        myFrame.setVisible(true);
        myFrame.setLocationRelativeTo(null);
        myFrame.add(playground);
    }

    @Override
    public void keyStrokeListener()
    {
       while(run)
        {
            if (quit)
            {
                this.run = false;
                physPlayground.stop();
            }

            if (left) {physPlayground.processKey("left");}
            if (right) {physPlayground.processKey("right");}
            if (shoot) {physPlayground.processKey("shoot");}
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

    public void draw()
    {
        myFrame.repaint();
    }

    @Override
    public boolean getRun() {
        return this.run;
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
            {
                this.right = true;
                break;
            }
            case KeyEvent.VK_LEFT:
            {
                this.left = true;
                break;
            }
            case KeyEvent.VK_SPACE: {
                this.shoot = true;
                break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_RIGHT:
            {
                this.right = false;
                break;
            }
            case KeyEvent.VK_LEFT:
            {
                this.left = false;
                break;
            }
            case KeyEvent.VK_SPACE:
            {
                this.shoot = false;
                break;
            }
        }
    }
}
