package DetectorKenny;

import GaussBlur.GaussBlur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DetectorKenny extends JPanel
{
    BufferedImage img;
    {
        try {
            img = ImageIO.read(new File("C:\\Users\\nikit\\IdeaProjects\\TestWindowPixel\\src\\Pictures\\123.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    int radius = 6; // Основная зависимость

    BufferedImage bufferImageKenny =new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    BufferedImage bufferImageKennyGauss =new BufferedImage(img.getWidth(), img.getHeight(),BufferedImage.TYPE_INT_RGB);
    GaussBlur gaussBlur;

    {
        try {
            gaussBlur = new GaussBlur();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public DetectorKenny()
    {

    }
    @Override
    public void paintComponent(Graphics g)
    {
        setDetectorKenny(g);
    }
    public void setDetectorKenny(Graphics g)
    {
        // https://habr.com/ru/articles/114589/

        Gauss gauss = new Gauss(radius, bufferImageKenny, img);
        double[][] kerG = gauss.KerBlur();
        gauss.DrawBlur(g,kerG);
        bufferImageKennyGauss =  gauss.DrawBlur(g,kerG);
          Sobol sobol = new Sobol(bufferImageKennyGauss,g);
          sobol.paintSobol2(g);



    }



}
