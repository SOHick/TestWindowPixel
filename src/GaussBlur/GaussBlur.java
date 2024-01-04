package GaussBlur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GaussBlur extends JPanel
{
    BufferedImage img = ImageIO.read(new File("C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\Pictures\\max.jpg"));
    BufferedImage bufferImageGauss=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    int radius = 6; // Основная зависимость
    double sigma = (double) radius / 2;
    double[][] kerG = new double[2*radius + 1][2*radius + 1];
    double sum = 0.0;
    public GaussBlur() throws IOException
    {

    }
    public double[][] KerBlur()
    {
        for(int x = 0; x < kerG[0].length;x++)
        {
            for(int y = 0; y < kerG[1].length;y++)
            {
                kerG[x][y] =FuncGauss(x - radius,sigma)*FuncGauss(y -radius,sigma);
                sum += kerG[x][y];
            }
        }
        // Normalize the kernel
        for (int x = 0; x < kerG[0].length; ++x)
        {
            for (int y = 0; y < kerG[1].length; ++y)
            {
                kerG[x][y] /= sum;
            }
        }
        return kerG;
    }
    public static double FuncGauss(double x, double sigma)
    {
        double a =  x / sigma;
        return (1/(Math.pow(2*(Math.PI)*sigma*sigma,0.5)))*(Math.exp( -0.5 * a * a ));
    }
    public void DrawBlur(Graphics g,double[][] kerG)
    {

        for(int x = radius; x<img.getWidth() - radius;x++)
        {
            for(int y = radius; y<img.getHeight() - radius;y++)
            {

                double redValue = 0.0;
                double greenValue = 0.0;
                double blueValue = 0.0;
                int color;
                for(int kernelX  = -radius; kernelX  <= radius; kernelX++)
                {
                    for(int kernelY  = -radius; kernelY <= radius; kernelY++)
                    {

                        double kernelValue = kerG[kernelX + radius][kernelY + radius];
                        int[] PixelARGB =  printPixelARGB(img.getRGB(x-kernelX,y-kernelY));
                        int red = PixelARGB[0];
                        int green = PixelARGB[1];
                        int blue = PixelARGB[2];
                        redValue +=  red * kernelValue;
                        greenValue +=  green * kernelValue;
                        blueValue += blue * kernelValue;

                    }
                }
                color = rgbToInt((int) redValue,(int) greenValue, (int)blueValue);
                bufferImageGauss.setRGB(x, y,color);
                /*Чтобы избежать черных лиинй перерисовать картинку чтобы черный цвет вышел за края  */
            }
        }
        g.drawImage(bufferImageGauss, 0, 0, null);
        g.dispose();

    }

    public int[] printPixelARGB(int pixel)
    {
        int[] PixelARGB = new int[3];
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = (pixel) & 0xff;
        PixelARGB[0]= red;
        PixelARGB[1]= green;
        PixelARGB[2]= blue;
        return PixelARGB;
    }
    public int rgbToInt(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }

}
