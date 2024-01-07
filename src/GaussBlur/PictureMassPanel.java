package GaussBlur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class PictureMassPanel extends JPanel
{
    BufferedImage img = ImageIO.read(new File("C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\Pictures\\max.jpg"));
    BufferedImage bufferImage2=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    BufferedImage bufferImage3=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    GaussBlur gaussBlur = new GaussBlur();
    public PictureMassPanel() throws IOException {

    }
    public void paintComponent(Graphics g)
    {
//        Matrix3(g);
//        g.drawImage(img, 0, 0, null);
      double[][] kerG = gaussBlur.KerBlur();
      gaussBlur.DrawBlur(g,kerG);
    }
    public void Matrix3(Graphics g)
    {
         g.drawImage(img, 0, 0, null);
        int[][] a = new int[img.getWidth()][img.getHeight()];
        int[][] b = new int[img.getWidth()][img.getHeight()];
        int[][] c = new int[img.getWidth()][img.getHeight()];
        int[][] ker = new int[3][3];
        for(int x = 0; x<ker[0].length;x++)
        {
            for(int y = 0; y<ker[1].length;y++)
            {
                ker[x][y] =1;
            }
        }
        // Так же попробовать другие ядра. Например:
        //ker[1][1]=1
        //ker[2][2]=1
        //ker[3][3]=1
        // Посмотреть скрины Камилы

        Random rnd=new Random();
        for(int x = 0; x<img.getWidth();x++)
        {
            for(int y = 0; y<img.getHeight();y++)
            {
                //a[x][y] = img.getRGB(x,y);
                //b[x][y]=rnd.nextInt(2);
                //c[x][y] = a[x][y]*b[x][y];
                //bufferImage2.setRGB(x, y,c[x][y]);
                int color =0;
                for(int i = 0; i<ker[0].length;i++)
                {
                    for(int j = 0; j<ker[1].length;j++)
                    {
                        color += ker[i][j]* img.getRGB(x,y);
                    }
                }
                bufferImage3.setRGB(x, y,color);
            }
        }
        g.drawImage(bufferImage3, 0, 0, null);
        g.dispose();
    }
}
