package DetectorKenny;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Gauss
{
    int radius;
    BufferedImage bufferImageKenny;
    BufferedImage img;
    double sigma;
    double[][] kerG;
    double sum = 0.0;
    public Gauss(int radius, BufferedImage bufferImageKenny,BufferedImage img)
    {
        this.radius= radius;
        sigma = 5;
        kerG = new double[2*radius + 1][2*radius + 1];
        this.bufferImageKenny = bufferImageKenny;
        this.img =  img;

    }
    public void PictureGray(Graphics g)
    {
        for(int x = 1; x<img.getWidth()-1;x++)
        {
            for(int y = 1; y<img.getHeight()-1;y++)
            {
                int Gray = getGrayScale(img.getRGB(x,y));
                bufferImageKenny.setRGB(x, y,Gray);
            }
        }

//        g.drawImage(bufferImageKenny, 0, 0, null); // Переводит картинку в серый цвет
//        g.dispose();
    }

    public static int  getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        //int gray = (r + g + b) / 3;
        // Normalize and gamma correct:
        double rr = Math.pow(r / 255.0, 2.2);
        double gg = Math.pow(g / 255.0, 2.2);
        double bb = Math.pow(b / 255.0, 2.2);
        // Calculate luminance:
        double lum = 0.299 * rr + 0.587 * gg + 0.114 * bb;
        // Gamma compand and rescale to byte range:
        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
        return (grayLevel << 16) + (grayLevel << 8) + grayLevel;
    }
    public double[][] KerBlur()
    {
        for(int x = 0; x < kerG[0].length-1;x++)
        {
            for(int y = 0; y < kerG[1].length-1;y++)
            {
                kerG[x][y] =FuncGauss(x - ((double) radius / 2),sigma)*FuncGauss(y - ((double) radius / 2),sigma);
                sum += kerG[x][y];
            }
        }
        // Normalize the kernel
        for (int x = 0; x < kerG[0].length-1; ++x)
        {
            for (int y = 0; y < kerG[1].length-1; ++y)
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
    public BufferedImage DrawBlur(Graphics g, double[][] kerG)
    {
        PictureGray(g);
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
                        int[] PixelARGB =  printPixelARGB(bufferImageKenny.getRGB(x-kernelX,y-kernelY));
                        int red = PixelARGB[0];
                        int green = PixelARGB[1];
                        int blue = PixelARGB[2];
                        redValue +=  red * kernelValue;
                        greenValue +=  green * kernelValue;
                        blueValue += blue * kernelValue;

                    }
                }
                color = rgbToInt((int) redValue,(int) greenValue, (int)blueValue);
                bufferImageKenny.setRGB(x, y,color);

            }
        }
//        g.drawImage(bufferImageKenny, 0, 0, null);
//        g.dispose();
        return bufferImageKenny;


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
