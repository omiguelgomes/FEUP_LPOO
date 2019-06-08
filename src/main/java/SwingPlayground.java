import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class SwingPlayground extends JPanel
{
    private int width, height, sWidth, sHeight;
    private int xBorder, yBorder;
    private int playerScore, playerLevel, playerLives;

    private SwingPlayer player;
    private SwingPowerUp powerUp;
    private List<SwingBlock> wall;
    private SwingBall ball;
    private List<SwingBorder> borders;

    public SwingPlayground (int w, int h, Position position, int sW, int sH)
    {
        this.width = w;
        this.height = h;
        this.sWidth = sW;
        this.sHeight = sH;
        this.xBorder = position.getX();
        this.yBorder = position.getY();
        this.playerScore = 0;
        this.playerLevel = 1;
        this.playerLives = 3;

        this.wall = new ArrayList<>();
        this.borders = new ArrayList<>();
    }

    private void drawScore(Graphics g)
    {
    }

    private void drawLevel(Graphics g)
    {
    }

    private void drawLives(Graphics g)
    {
    }

    @Override
    public void paint(Graphics g)
    {
        drawScore(g);
        drawLevel(g);
        drawLives(g);

        g.drawRect(xBorder, yBorder, width * sWidth / 10, height * sHeight / 10);

        for (SwingBorder border : borders)
            border.paintComponent(g);

        Iterator<SwingBlock> it = wall.iterator();
        while (it.hasNext())
        {
            SwingBlock next = it.next();
            next.paintComponent(g);
        }

        this.setPlayer(new Position(this.player.getX(), this.player.getY()));
        player.paintComponent(g);

        this.setBall(new Position(this.ball.getX(), this.ball.getY()));
        ball.paintComponent(g);

    }

    public void setPlayer(Position pos)
    {
        this.player = new SwingPlayer(pos, "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\green.png");
    }

    public void setBall(Position pos)
    {
        this.ball = new SwingBall(pos, "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\white.png");
    }

    public void setPowerUp(Position pos){this.powerUp = new SwingPowerUp(pos, "");}

    public void setBorders(List<Position> borders) {
        this.borders = borderParser(borders);
    }

    public void setWall(List<PhysBlock> blocks)
    {
        this.wall = wallParser(blocks);
    }

    private List<SwingBorder> borderParser (List<Position> borders)
    {
        List<SwingBorder> lista = new ArrayList<>();

        for (Position position : borders)
        {
            lista.add(new SwingBorder(position, "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\black.png"));//wall file
        }

        return lista;
    }

    private List<SwingBlock> wallParser(List<PhysBlock> blocks)
    {
        List<SwingBlock> lista = new ArrayList<>();

        for (PhysBlock block : blocks)
        {
            if(block.getTag().equals("B"))
            {
                lista.add(new SwingNormalBlock(block.getPosition(), "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\green.png"));//blocks files
            }

            if(block.getTag().equals("C"))
            {
                lista.add(new SwingSuperBlock(block.getPosition(), "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\blue.png"));
            }

            if(block.getTag().equals("D"))
            {
                lista.add(new SwingMegaBlock(block.getPosition(), "C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\red.png"));
            }
        }

        return lista;
    }

    public void setPlayerScore(int score)
    {
        this.playerScore = score;
    }

    public void setPlayerLives(int lives)
    {
        this.playerLives = lives;
    }

    public void setPlayerLevel(int level){this.playerLevel = level;}

    }

