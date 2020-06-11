import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JimageDisplay extends JComponent {
    public BufferedImage bufferedImage;

    public JimageDisplay(int width, int height)
    {
        bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Dimension dimension = new Dimension(width,height);
        super.setPreferredSize(dimension);//Отоброзит на экране все изображения
    }
    public void paintComponent (Graphics g)//Отрисовка
    {
        super.paintComponent(g);
        g.drawImage(bufferedImage,0,0, bufferedImage.getWidth(), bufferedImage.getHeight(),null);
    }
    public void clearImage()
    {
        for (int i = 0; i < bufferedImage.getWidth();i++)
        {
            for (int j = 0; j < bufferedImage.getHeight();j++)
                bufferedImage.setRGB(i,j,0);
        }
    }
    public void drawPixel(int x, int y, int rgbColor)
    {
        bufferedImage.setRGB(x,y,rgbColor);
    }
}
