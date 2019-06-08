import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingElement extends JComponent
{
    protected Position position;
    protected BufferedImage image;
    protected Color color;

    public SwingElement(Position position, String pathName)
    {
        this.position = position;

        try {
            image = ImageIO.read(new File(pathName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        /*super.paintComponent(g);
        g.setColor(color);
        JLabel label = new JLabel("");
        Image image = new ImageIcon(this.getClass().getResource("C:\\Users\\migue\\OneDrive\\Ambiente de Trabalho\\MIEIC\\LPOO\\Projeto\\projecto-lpoo-2019-lpoo_18\\projecto-lpoo-2019-lpoo_18\\src\\main\\java\\images\\green.png")).getImage();
        label.setIcon(new ImageIcon(image));
        label.setBounds(0,0,50,50);
*/
        g.drawImage(image, 0, 0, 50, 50, null);
     }
}
