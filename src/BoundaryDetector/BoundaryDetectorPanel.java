package BoundaryDetector;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BoundaryDetectorPanel extends JPanel
{
    BufferedImage img;
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\Pictures\\fig.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    BufferedImage bufferImageSobol=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    int maxGradient = -1;
    double scale = 255.0 / maxGradient;

    public BoundaryDetectorPanel()  {

    }
    public void paintComponent(Graphics g)
    {
        KernelSobol(g);
    }
    public void KernelSobol(Graphics g)
    {
        int[][] kerSX = {
                {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };
        int[][] kerSY = {
                {-1,-2,-1},
                {0,0,0},
                {1,2,1}
        };
        int[][] h = new int[3][3];
        for(int x = 1; x<img.getWidth()-1;x++)
        {
            for(int y = 1; y<img.getHeight()-1;y++)
            {
                for (int m = 0; m < h[0].length; m++)
                {
                    for (int n = 0; n < h[1].length; n++) {

                        h[m][n]= getGrayScale(img.getRGB(x +(m-1), y + (n-1)));
                    }
                }
                double Gx =0;
                double Gy =0;
                for(int i = 0; i<kerSX[0].length;i++)
                {
                    for(int j = 0; j<kerSX[1].length;j++)
                    {
                        Gx += kerSX[i][j] * h[i][j];
                        Gy += kerSY[i][j] * h[i][j];
                    }

                }
                double gval = Math.sqrt((Gx * Gx) + (Gy * Gy));
                int edgeColor = (int) gval;

                if(maxGradient < edgeColor) {
                    maxGradient = edgeColor;
                }
                edgeColor = (int)(edgeColor * scale);
                edgeColor = 0xff000000 | (edgeColor << 16) | (edgeColor << 8) | edgeColor;
                bufferImageSobol.setRGB(x, y, edgeColor);
            }
        }
        g.drawImage(bufferImageSobol, 0, 0, null);
        g.dispose();

    }
    public static int  getGrayScale(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        //from https://en.wikipedia.org/wiki/Grayscale, calculating luminance
        //int gray = (r + g + b) / 3;
        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
    }
}
