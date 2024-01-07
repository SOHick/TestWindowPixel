package GrayScale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PictureGrayPanel extends JPanel
{
    // Оператор Соболя
    BufferedImage img;

    {
        try {
            img = ImageIO.read(new File("C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\Pictures\\3d.jpg"));
            //Чтобы поменять на обычный оператор Соболя поставить картинку 123.png
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    int[][] NormGradient = new int[img.getWidth()][img.getWidth()];
    int[][] NormGradientClone = new int[img.getWidth()][img.getWidth()];
    BufferedImage bufferImageGray=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    BufferedImage bufferImageGray2=new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    public PictureGrayPanel()  {

    }
    public void paintComponent(Graphics g)
    {
        PictureGray(g);
        KernelGray(g);
        NormMatrix(g);
    }
    public void PictureGray(Graphics g)
    {
        for(int x = 1; x<img.getWidth()-1;x++)
        {
            for(int y = 1; y<img.getHeight()-1;y++)
            {
                int Gray = getGrayScaleBlue(img.getRGB(x,y));
                bufferImageGray.setRGB(x, y,Gray);
            }
        }
//        g.drawImage(bufferImageGray, 0, 0, null); // Переводит картинку в серый цвет
//        g.dispose();
    }
    public void KernelGray(Graphics g)
    {

        int[][] kerSX = {
                {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };
        int[][] kerSY = {
                {1,2,1},
                {0,0,0},
                {-1,-2,-1}
        };
        int[][] h = new int[3][3];
        for(int x = 1; x<bufferImageGray.getWidth()-1;x++)
        {
            for(int y = 1; y<bufferImageGray.getHeight()-1;y++)
            {
                for (int m = 0; m < h[0].length; m++)
                {
                    for (int n = 0; n < h[1].length; n++) {

                        h[m][n]= bufferImageGray.getRGB(x +(m-1), y + (n-1));
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
                NormGradient[x][y]= (int) gval;
                NormGradientClone[x][y]= (int) gval;
            }
        }

    }
    public void NormMatrix(Graphics g)
    {
        int[][] kerSX = {
                {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };
        int[][] kerSY = {
                {1,2,1},
                {0,0,0},
                {-1,-2,-1}
        };
        int[][] h = new int[3][3];
        for(int x = 1; x<bufferImageGray.getWidth()-1;x++)
        {
            for(int y = 1; y<bufferImageGray.getHeight()-1;y++)
            {
                for (int m = 0; m < h[0].length; m++)
                {
                    for (int n = 0; n < h[1].length; n++) {

                        h[m][n]= bufferImageGray.getRGB(x +(m-1), y + (n-1));
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
                double arc = Math.atan(Gx/Gy);
                if(Float.isNaN((float) arc))
                {
                    double max = Math.max(NormGradientClone[x][y],Math.max(NormGradientClone[x][y-1],NormGradientClone[x][y+1]));
                    if(max == NormGradientClone[x][y])
                    {
                        NormGradientClone[x][y-1]=0;
                        NormGradientClone[x][y+1]=0;
                    }
                    else if(max==NormGradientClone[x][y-1]){
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x][y+1]=0;

                    }
                    else {
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x][y-1]=0;

                    }
                }
                else if(Math.PI / 8 <= arc  &&  arc < 5*Math.PI/8)
                {
                    double max = Math.max(NormGradientClone[x][y],Math.max(NormGradientClone[x+1][y-1],NormGradientClone[x-1][y+1]));
                    if(max == NormGradientClone[x][y])
                    {
                        NormGradientClone[x+1][y-1]=0;
                        NormGradientClone[x-1][y+1]=0;
                    }
                    else if(max==NormGradientClone[x+1][y-1]){
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x-1][y+1]=0;

                    }
                    else {
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x+1][y-1]=0;

                    }
                }
                else if(Math.abs(arc) < Math.PI/8)
                {
                    double max = Math.max(NormGradientClone[x][y],Math.max(NormGradientClone[x+1][y],NormGradientClone[x-1][y]));
                    if(max == NormGradientClone[x][y])
                    {
                        NormGradientClone[x+1][y]=0;
                        NormGradientClone[x-1][y]=0;
                    }
                    else if(max==NormGradientClone[x+1][y]){
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x-1][y]=0;

                    }
                    else {
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x+1][y]=0;

                    }
                }
                else if( ((-5*Math.PI / 8) <= arc ) &&  (arc < (-Math.PI/8)))
                {
                    double max = Math.max(NormGradientClone[x][y],Math.max(NormGradientClone[x+1][y+1],NormGradientClone[x-1][y-1]));
                    if(max == NormGradientClone[x][y])
                    {
                        NormGradientClone[x+1][y+1]=0;
                        NormGradientClone[x-1][y-1]=0;
                    }
                    else if(max==NormGradientClone[x+1][y+1]){
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x-1][y-1]=0;

                    }
                    else {
                        NormGradientClone[x][y]=0;
                        NormGradientClone[x+1][y+1]=0;

                    }
                }
                //bufferImageGray2.setRGB(x, y, getGrayScale(NormGradientClone[x][y])); // Серая картинка
                bufferImageGray2.setRGB(x, y, NormGradientClone[x][y]);


            }
        }
        g.drawImage(bufferImageGray2, 0, 0, null);
        g.dispose();

    }
    public static int  getGrayScaleBlue(int rgb) {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = (rgb) & 0xff;
        return (int)(0.2126 * r + 0.7152 * g + 0.0722 * b);
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
        double lum = 0.2126 * rr + 0.7152 * gg + 0.0722 * bb;
        // Gamma compand and rescale to byte range:
        int grayLevel = (int) (255.0 * Math.pow(lum, 1.0 / 2.2));
        return (grayLevel << 16) + (grayLevel << 8) + grayLevel;
    }

}
