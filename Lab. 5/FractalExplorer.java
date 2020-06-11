import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileFilter;

public class FractalExplorer {
    private int displaySize;
    private JimageDisplay upDisplay;//Ссылка,для отображения в разных методах
    private FractalGenerator fractalF;//Ссылка на базовый класс
    private Rectangle2D.Double range;//Диапазон комплексной плоскости
    private JComboBox comboBox;
    private JButton saveBut;
    public FractalExplorer(int size)
    {
        displaySize = size;
        range = new Rectangle2D.Double();
        fractalF = new Mandelbrot();
        fractalF.getInitialRange(range);//Инициализация объекты диапазона и фрактального генератора
        upDisplay = new JimageDisplay(displaySize,displaySize);
    }
    public void createAndShowGUI()
    {
        JFrame frame = new JFrame("Fractal");
        frame.add(upDisplay,BorderLayout.CENTER);
        JButton button = new JButton("Reset");
        ResetDis reset = new ResetDis();
        button.addActionListener(reset);
        //frame.add(button,BorderLayout.SOUTH);

        MousePiPi mouse = new MousePiPi();
        upDisplay.addMouseListener(mouse);

        JComboBox comboBox = new JComboBox();
        FractalGenerator mandelbrotFrac = new Mandelbrot();
        comboBox.addItem(mandelbrotFrac);
        FractalGenerator tricornFrac = new Tricorn();
        comboBox.addItem(tricornFrac);
        FractalGenerator burningshipFrac = new BurningShip();
        comboBox.addItem(burningshipFrac);

        ResetDis fracCh = new ResetDis();
        comboBox.addActionListener(fracCh);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Fractal ");
        panel.add(label);
        panel.add(comboBox);
        frame.add(panel,BorderLayout.NORTH);//обавляем label в интерфейс перед списком

        saveBut = new JButton("Save");
        JPanel BotPan = new JPanel();
        BotPan.add(saveBut);
        BotPan.add(button);
        frame.add(BotPan,BorderLayout.SOUTH);

        ResetDis saveH = new ResetDis();
        saveBut.addActionListener(saveH);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        //Правильно разместят содержимое окна
    }
    private void drawFractal()
    {
        for (int i = 0; i<displaySize;i++){
            for (int j = 0; j<displaySize;j++){
                double xCoord = FractalGenerator.getCoord(range.x, range.x+range.width,displaySize, i);//Пиксельная и координата в пространстве
                double yCoord = FractalGenerator.getCoord(range.y, range.y+range.height,displaySize, j);
                int iter = fractalF.numIterations(xCoord,yCoord);
                if (iter == -1)
                {
                    upDisplay.drawPixel(i,j,0);
                }
                else
                {
                    float hue = 0.7f + (float) iter/200f;
                    int rgbColor = Color.HSBtoRGB(hue,1f,1f);
                    upDisplay.drawPixel(i,j,rgbColor);
                }
            }
        }
        upDisplay.repaint();//Обновление JimageDisplay в соответствии с цветом для каждого пикселя
    }

    private class ResetDis implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() instanceof JComboBox)
            {
                JComboBox cB = (JComboBox) e.getSource();
                fractalF = (FractalGenerator) cB.getSelectedItem();
                fractalF.getInitialRange(range);
                drawFractal();
            } else if (e.getActionCommand().equals("Reset"))
            {
                fractalF.getInitialRange(range);
                drawFractal();
            } else if (e.getActionCommand().equals("Save"))
            {
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter eFilter = new FileNameExtensionFilter("PNG IMAGES","png");
                fileChooser.setFileFilter(eFilter);
                
                fileChooser.setAcceptAllFileFilterUsed(false);

                int sel = fileChooser.showSaveDialog(upDisplay);

                if(sel == JFileChooser.APPROVE_OPTION)
                {
                    java.io.File file = fileChooser.getSelectedFile();
                    String filename = file.toString();
                    try {
                        BufferedImage bufferedImage = upDisplay.bufferedImage;
                        javax.imageio.ImageIO.write(bufferedImage,"png",file);
                    }
                    catch(Exception exception) {
                        JOptionPane.showMessageDialog(upDisplay,exception.getMessage(),"Can't save file",JOptionPane.ERROR_MESSAGE);
                }
                }
                else return;
            }
        }
    }
    private class MousePiPi extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            int x = e.getX();
            double xCoord = fractalF.getCoord(range.x, range.x+range.width,displaySize, x);
            int y = e.getY();
            double yCoord = fractalF.getCoord(range.y, range.y+range.height,displaySize, y);
            fractalF.recenterAndZoomRange(range, xCoord,yCoord,0.5);
            drawFractal();
        }
    }

    public static void main(String[] args)
    {
        FractalExplorer display1 = new FractalExplorer(500);
        display1.createAndShowGUI();
        display1.drawFractal();
    }
}
