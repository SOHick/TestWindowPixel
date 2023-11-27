package MeshPicture;

import javax.swing.*;
import java.awt.*;

public class Mesh extends JPanel
{
    private final int[] P={500,250,750,250,700,50,350,100};

    int th = 2;
    String Ox;
    String Oy;
    double scala = Math.pow(10,1);
    int xCenter, yCenter;

    double xMin = -10 , yMin =-10;
    double xMax = 10, yMax = 10;
    int mainWidth;
    int mainHeight;
    Convector a;

    public Mesh(Graphics g, int width, int height)
    {
        this.mainWidth = width;
        this.mainHeight= height;
        a = new Convector(xMin,xMax,yMin,yMax,mainWidth,mainHeight);
        MeshPolygon(g);
    }
    public void MeshPolygon(Graphics g)
    {
        DrawXAxis(mainHeight, g);
        DrawYAxis(mainWidth, g);
        SingleRectangle(g);
        NewRectangle(g);
    }
    public void SingleRectangle(Graphics g)
    {
        int[] xPoints ={a.xCrt2Scr(-1),a.xCrt2Scr(1),a.xCrt2Scr(1),a.xCrt2Scr(-1)};
        int[] yPoints = {a.yCrt2Scr(-1),a.yCrt2Scr(-1), a.yCrt2Scr(1), a.yCrt2Scr(1)};
        g.drawPolygon(xPoints,yPoints,xPoints.length);
    }
    public void NewRectangle(Graphics g)
    {
        int[] xPoints ={P[0],P[2],P[4],P[6]};
        int[] yPoints = {P[1],P[3], P[5], P[7]};
        g.drawPolygon(xPoints,yPoints,xPoints.length);
    }

    public void DrawXAxis (int height,Graphics g)
    {
        xCenter = a.xCrt2Scr(0);
        yCenter = a.yCrt2Scr(0);
        g.setColor(Color.black);
        g.drawLine(xCenter, 0, xCenter, mainHeight);
        g.drawLine(0, yCenter, mainWidth, yCenter);
        g.setColor(Color.black);
        Color oldColor = g.getColor();
        Color newColor = new Color(200,0,0);
        if (yMax * yMin < 0)
        {
            //рисуем штрихи на оси х
            for(double i = xMin; i<xMax;i+=0.1)
            {
                double j = Math.round(i * scala) / scala;
                Ox = Double.toString(j);
                double X2 = g.getFontMetrics().getStringBounds(Ox,g).getWidth(); //x-w/2
                double Y2 = g.getFontMetrics().getStringBounds(Ox,g).getHeight(); //y+th+1+h
                if((Math.round(10*j)%10 == 0))
                {
                    if(j<0)
                    {
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2) , yCenter +th+1+(int)Y2);

                    }
                    else if(j>0)
                    {
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2), yCenter +th+1+(int)Y2);
                    }
                    else
                    {
                        g.drawString("0.0",a.xCrt2Scr(j)-(int)(1.5*X2), yCenter +th+1+(int)Y2); //22
                    }
                    th+=4;
                }
                else if(Math.round(10*j)%5 == 0)
                {
                    th+=2;
                }

                g.setColor(newColor);
                g.drawLine(a.xCrt2Scr(j), yCenter -th, a.xCrt2Scr(j), yCenter +th);
                g.setColor(oldColor);

                th = 2;
            }
        }
        else {
            //рисуем штрихи сверху и снизу
            for(double i = xMin; i<xMax;i+=0.1)
            {
                double j = Math.round(i * scala) / scala;
                Ox = Double.toString(j);
                double X2 = g.getFontMetrics().getStringBounds(Ox,g).getWidth(); //x-w/2
                double Y2 = g.getFontMetrics().getStringBounds(Ox,g).getHeight(); //y+th+1+h
                if((Math.round(10*i)%10 == 0))
                {
                    th+=4;
                    if (j<0)
                    {
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2) ,th+(int)Y2+1);
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2), height-th-1);

                    }
                    else if(j>0)
                    {
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2) ,th+(int)Y2+1);
                        g.drawString(Ox,a.xCrt2Scr(j)-(int)(X2/2), height-th-2);

                    }
                    else
                    {
                        g.drawString("0.0",a.xCrt2Scr(j)-(int)(X2)-th ,th+(int)Y2+1);
                        g.drawString("0.0",a.xCrt2Scr(j)-(int)(X2)-th, height-th-2);
                    }
                }
                else if(Math.round(10*i)%5==0)
                {
                    th+=2;
                }

                g.setColor(newColor);
                g.drawLine(a.xCrt2Scr(j), 0, a.xCrt2Scr(j), th);
                g.drawLine(a.xCrt2Scr(j), height-th-1, a.xCrt2Scr(j), height);
                g.setColor(oldColor);
                th = 2;
            }
        }
    }
    public void DrawYAxis (int width,Graphics g)
    {
        g.setColor(Color.black);
        Color oldColor = g.getColor();
        Color newColor = new Color(200,0,0);
        if (xMax * xMin < 0)
        {
            //рисуем штрихи на оси у
            for(double i = yMin; i < yMax; i+=0.1)
            {
                double j = Math.round(i * scala) / scala;
                Oy = Double.toString(j);
                if((10*j)%10 == 0 && (j != 0))
                {
                    th+=4;
                    double X1 = g.getFontMetrics().getStringBounds(Oy,g).getWidth(); //x-w/2
                    double Y1 = g.getFontMetrics().getStringBounds(Oy,g).getHeight(); //y+th+1+h
                    if(j<0)
                    {

                        g.drawString(Oy,xCenter-1-th-(int)X1 , a.yCrt2Scr(j)+(int)(Y1*0.25));
                    }
                    else if(j>0)
                    {
                        g.drawString(Oy,xCenter-1-th-(int)X1, a.yCrt2Scr(j)+(int)(Y1*0.25));
                    }
                }
                else if(Math.round(10*j)%5==0)
                {
                    th+=2;
                }
                // g.drawLine(0, a.yCrt2Scr(j), width, a.yCrt2Scr(j));
                g.setColor(newColor);
                g.drawLine(xCenter-th, a.yCrt2Scr(j), xCenter+th, a.yCrt2Scr(j));
                th = 2;
                g.setColor(oldColor);
            }
        }
        else {
            //рисуем штрихи по бокам
            for(double i = yMin; i<yMax;i+=0.1)
            {
                double j = Math.round(i * scala) / scala;
                double X1 = g.getFontMetrics().getStringBounds(Oy,g).getWidth(); //x-w/2
                double Y1 = g.getFontMetrics().getStringBounds(Oy,g).getHeight(); //y+th+1+h
                Oy = Double.toString(j);

                if(Math.round(10*j)%10 == 0)
                {
                    th+=4;
                    if(j<0)
                    {
                        g.drawString(Oy,th+2, a.yCrt2Scr(j)+(int)(Y1/2));
                        g.drawString(Oy,width-th-(int)X1, a.yCrt2Scr(j)+(int)(Y1/2));
                    }
                    else if(j>0)
                    {
                        g.drawString(Oy,th+2, a.yCrt2Scr(j)+(int)(Y1/2));
                        g.drawString(Oy,width-th-(int)X1, a.yCrt2Scr(j)+(int)(Y1/2));
                    }
                }
                else if(Math.round(10*j)%5==0)
                {
                    th+=2;
                }
                g.setColor(newColor);
                g.drawLine(0, a.yCrt2Scr(j), th, a.yCrt2Scr(j));
                g.drawLine(width-th, a.yCrt2Scr(j), width, a.yCrt2Scr(j));
                g.setColor(oldColor);
                th = 2;
            }
        }
    }
    public double F1(double v, String coordinate)
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = ((v-1)*(a.yScr2Crt(P[1])-1))/4;
                break;
            }
            case "y":
            {
                F =(a.xScr2Crt(P[0])-1)*(v-1)/4;
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
                F = -(v+1)*(a.yScr2Crt(P[3])-1)/4;
                break;
            }
            case "y":
            {
                F = -(a.xScr2Crt(P[2])+1)*(v-1)/4;
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
                F = (v+1)*(a.yScr2Crt(P[5])+1)/4;
                break;
            }
            case "y":
            {
                F =(a.xScr2Crt(P[4])+1)*(v+1)/4;
                break;
            }
        }
        return F;
    }
    public double F4(double v, String coordinate) // feature
    {
        double F = 0;
        switch (coordinate)
        {
            case "x":
            {
                F = -(v-1)*(a.yScr2Crt(P[7])+1)/4;
                break;
            }
            case "y":
            {
                F = -(a.xScr2Crt(P[6])-1)*(v+1)/4;
                break;
            }
        }
        return F;
    }

}
