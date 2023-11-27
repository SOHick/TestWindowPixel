package MeshPicture;

import javax.swing.*;
import java.awt.*;

public class MeshTest extends JPanel
{
    private final int[] P={500,250,750,250,700,50,350,100};

    int w1 = 50 , h1 =50;
    int w2 = 300, h2 = 300;
    int width = w2-w1;
    int height = h2-h1;
    double XYDen = 10;

    double xMin = -XYDen , yMin =-XYDen;
    double xMax = XYDen, yMax = XYDen;
    int mainWidth;
    int mainHeight;
    Convector a;

    public MeshTest(Graphics g, int width, int height)
    {
        this.mainWidth = width;
        this.mainHeight= height;
        a = new Convector(xMin,xMax,yMin,yMax,mainWidth,mainHeight);
        MeshPolygon(g);
    }
    public void MeshPolygon(Graphics g)
    {

        int u = 250; // x
        int v = 50; // y
        double x = a.xScr2Crt(u);
        double y = a.xScr2Crt(v);
        double ksi = F1(y,"y")+F2(y,"y")+F3(y,"y")+F4(y,"y");
        double eta = F1(x,"x")+F2(x,"x")+F3(x,"x")+F4(x,"x");

        System.out.println(a.yCrt2Scr(ksi));
        System.out.println(a.xCrt2Scr(eta));
        g.fillRect(a.xCrt2Scr(eta),a.yCrt2Scr(ksi),5,5);
        g.drawRect(w1,h1,width,height);
//        CoordinateAxes(g);
        TestMeshTwo(g);

    }
    public void TestMeshTwo(Graphics g)
    {
        int[] xPoints ={500,750,700,350};
        int[] yPoints = {250,250, 50, 100};
        g.drawPolygon(xPoints,yPoints,xPoints.length);
    }

    public void CoordinateAxes(Graphics g)
    {

        int dx = (w2)/(int)XYDen;
        int dy = (h2)/(int)XYDen;
        for(int i =w1/(int)XYDen; i<=dx;i++)
        {
            g.drawLine((int)XYDen*i,h1,(int)XYDen*i,h2);
        }
        for(int i =h1/(int)XYDen; i<=dy;i++)
        {
            g.drawLine(w1,(int)XYDen*i,w2,(int)XYDen*i);
        }
    }
    public double F1(double v, String coordinate)
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = (v-30)*(a.yScr2Crt(P[1])-5)/625;
                break;
            }
            case "y":
            {
                F =(a.xScr2Crt(P[0])-30)*(v-5)/625;
                break;
            }
        }
        return F;
    }
    public double F2(double v,String coordinate)
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = (v-5)*(a.yScr2Crt(P[3])-5)/625;
                break;
            }
            case "y":
            {
                F = (a.xScr2Crt(P[2])-5)*(v-5)/625;
                break;
            }
        }
        return F;
    }
    public double F3(double v,String coordinate)
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = (v-5)*(a.yScr2Crt(P[5])-30)/625;
                break;
            }
            case "y":
            {
                F =(a.xScr2Crt(P[4])-5)*(v-30)/625;
                break;
            }
        }
        return F;
    }
    public double F4(double v, String coordinate)
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = (v-30)*(a.yScr2Crt(P[7])-30)/625;
                break;
            }
            case "y":
            {
                F = (a.xScr2Crt(P[6])-30)*(v-30)/625;
                break;
            }
        }
        return F;
    }


}
