package Triangle;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel
{
    int splittingNx = 10;
    int splittingNy = 10;
    int coeF = 2;
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int dx = this.getWidth()/splittingNx;
        int dy = this.getHeight()/splittingNy;
        for(int i =0; i<=dx;i++)
        {
            g.drawLine(splittingNx*i,0,splittingNx*i,this.getHeight());
        }
        for(int i =0; i<=dy;i++)
        {
            g.drawLine(0,splittingNy*i,this.getWidth(),splittingNy*i);
        }

        //drawSquare(dx,dy,splittingNx,splittingNy,g);
        //drawLine(dx,dy,splittingNx,splittingNy,g); //Рисование линии (в каждой точке своё значение)

        //drawTriangle(splittingNx,splittingNy,g); // Закрашывание треугольника
        LineBresenham lineBresenham = new LineBresenham();
        lineBresenham.Bresenham2(0,20,40,0,splittingNx,splittingNy,g);
        g.setColor(Color.cyan);
        g.drawLine(0,210,420,0);
        g.setColor(Color.black);

//        int x1=coeF;
//        int y1=coeF;
//        int x2=10*coeF;
//        int y2=6*coeF;
//        int x3=7*coeF;
//        int y3=11*coeF; //1
//        drawTriangle2(x1,x2,x3,y1,y2,y3,splittingNx,splittingNy,g); // Лараби

    }
    public void drawSquare(int dx,int dy,int splittingNx ,int splittingNy, Graphics g)
    {
        for(int i =0; i<=dx;i++)
        {
            for(int j =0; j<=dy;j++)
            {
                if(i>=1 && i<=10 && j>=1 && j<=5 )
                {
                    g.fillRect(splittingNx*i,splittingNy*j,splittingNx,splittingNy);
                }
            }
        }

    }
    public void drawLine(int dx,int dy,int splittingNx ,int splittingNy, Graphics g)
    {
        int A=1;
        int B=-2;
        int C=0;

        if(A >= B)
        {
            for(int j =0; j<=dx;j++) // x
            {
                int i = (-C-A*j)/(B); // y
                g.fillRect(splittingNx*j,splittingNy*i,splittingNx,splittingNy);
            }
        }
        else if(A < B)
        {
            for(int i =0; i<=dy;i++) // y
            {
                int j = (-C-B*i)/(A); // x
                g.fillRect(splittingNx*j,splittingNy*i,splittingNx,splittingNy);
            }
        }
    }
    public void drawTriangle(int splittingNx ,int splittingNy, Graphics g)
    {
        int x1=coeF;
        int y1=coeF;
        int x2=10*coeF;
        int y2=6*coeF;
        int x3=7*coeF;
        int y3=1*coeF; // 1
        g.setColor(Color.PINK);
        FindPoint2(x1,x3,x2,y1,y3,y2,splittingNx,splittingNy,g);
        FindPoint3(x3,x2,x1,y3,y2,y1,splittingNx,splittingNy,g);
        g.setColor(Color.black);
    }
    public void FindPoint2(int x1,int x2,int x3,int y1,int y2,int y3,int splittingNx ,int splittingNy, Graphics g)
    {
        for(int i =x1; i<=x2-1; i++)
        {
            int j1 = ((i -x1)*(y2-y1))/(x2-x1)+y1;
            int j2 = ((i -x1)*(y3-y1))/(x3-x1)+y1;
            int J = Math.min(j1,j2);
            int I = Math.max(j1,j2);
            for(int k=J; k<=I;k++)
            {
                g.fillRect(splittingNx*i,splittingNy*k,splittingNx,splittingNy);
            }
        }
    } // Ещё нужен один метод
    public void FindPoint3(int x1,int x2,int x3,int y1,int y2,int y3,int splittingNx ,int splittingNy, Graphics g)
    {
        for(int i =x1; i<=x2-1; i++)
        {
            int j3 = ((i -x3)*(y2-y3))/(x2-x3)+y3;
            int j2 = ((i -x1)*(y2-y1))/(x2-x1)+y1;
            int J = Math.min(j3,j2);
            int I = Math.max(j3,j2);
            for(int k=J; k<=I;k++)
            {
                g.fillRect(splittingNx*i,splittingNy*k,splittingNx,splittingNy);
            }
        }
    }
    public void drawTriangle2(int x1,int x2,int x3,int y1,int y2,int y3,int splittingNx ,int splittingNy, Graphics g)
    {

        int Xmax = Math.max(Math.max(x1,x2),x3);
        int Ymax= Math.max(Math.max(y1,y2),y3);
        int Xmin = Math.min(Math.min(x1,x2),x3);
        int Ymin= Math.min(Math.min(y1,y2),y3);
        g.setColor(Color.BLUE);
        g.drawRect(Xmin*splittingNx,Ymin*splittingNy,(Xmax-Xmin)*splittingNx,(Ymax-Ymin)*splittingNy);
        g.setColor(Color.BLACK);
        int delX = (Xmax-Xmin)/2;
        int delY = (Ymax-Ymin)/2;
        g.setColor(Color.cyan);
        g.drawLine(Xmin*splittingNx,(delY+Ymin)*splittingNy,Xmax*splittingNx,(delY+Ymin)*splittingNy);
        g.drawLine((delX+Xmin)*splittingNx,Ymin*splittingNy,(delX+Xmin)*splittingNx,Ymax*splittingNy);
        g.setColor(Color.BLACK);
        int RX = (Xmax+Xmin)*splittingNx/2 + ((Xmax+Xmin)*splittingNx/2)%splittingNx;
        int RY = (Ymax+Ymin)*splittingNy/2 +((Ymax+Ymin)*splittingNy/2)%splittingNy;
        RectangleCorner(x1*splittingNx,x2*splittingNx,x3*splittingNx,y1*splittingNy,y2*splittingNy,y3*splittingNy,Xmin*splittingNx,Ymin*splittingNy,RX,Ymin*splittingNy,RX,RY,Xmin*splittingNx,RY,splittingNx,splittingNy,g);
        RectangleCorner(x1*splittingNx,x2*splittingNx,x3*splittingNx,y1*splittingNy,y2*splittingNy,y3*splittingNy,RX,Ymin*splittingNy,Xmax*splittingNx,Ymin*splittingNy,Xmax*splittingNx,RY,RX,RY,splittingNx,splittingNy,g);
        RectangleCorner(x1*splittingNx,x2*splittingNx,x3*splittingNx,y1*splittingNy,y2*splittingNy,y3*splittingNy,RX,RY,Xmax*splittingNx,RY,Xmax*splittingNx,Ymax*splittingNy,RX,Ymax*splittingNy,splittingNx,splittingNy,g);
        RectangleCorner(x1*splittingNx,x2*splittingNx,x3*splittingNx,y1*splittingNy,y2*splittingNy,y3*splittingNy,Xmin*splittingNx,RY,RX,RY,RX,Ymax*splittingNy,Xmin*splittingNx,Ymax*splittingNy,splittingNx,splittingNy,g);

        //См голосовое Алисы и разбить прямоугольник и сделать через рекурсию

        // 29.09.23 С помощью булеевской функции Point проверять точки прямоугольников на принадлежность
        // треугольнику и потом уже делать рекурсию
    }
    public void RectangleCorner(int x1,int x2,int x3,int y1,int y2,int y3,int rx1,int ry1, int rx2,int ry2,int rx3,int ry3,int rx4,int ry4,int splittingNx ,int splittingNy,Graphics g)
    {
        boolean b1 = Point(x1,x2,x3,y1,y2,y3,rx1,ry1);
        boolean b2 = Point(x1,x2,x3,y1,y2,y3,rx2,ry2);
        boolean b3 = Point(x1,x2,x3,y1,y2,y3,rx3,ry3);
        boolean b4 = Point(x1,x2,x3,y1,y2,y3,rx4,ry4);
        if((Math.abs((rx2-rx1)) > splittingNx && Math.abs((ry4-ry1)) >= splittingNy) || (Math.abs((rx2-rx1)) >= splittingNx && Math.abs((ry4-ry1)) > splittingNy))
        {

            if(b1 && b2 && b3 && b4) // Полностью надо закрасить треугольник
            {
                g.setColor(Color.black);
                // Нумерация прямоугольника A1A2A3A4
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
                g.setColor(Color.black);
            }
            else if (!b1 && !b2 && !b3 && !b4)
            {
                System.out.println("Not");
            }
            else
            {
                // 4 раза вызвать метод RectangleCorner
                int kx1=(rx1+rx2)/2+ (((rx1+rx2)/2)%splittingNx);
                int ky1=(ry1+ry4)/2+ (((ry1+ry4)/2)%splittingNy);
                g.setColor(Color.yellow);
                g.drawLine(kx1,ry1,kx1,ry4);// вертикаль
                g.drawLine(rx1,ky1,rx2,ky1); // горизонт
                g.setColor(Color.black);
                RectangleCorner(x1,x2,x3,y1,y2,y3,rx1,ry1,kx1,ry2,kx1,ky1,rx4,ky1,splittingNx,splittingNy,g);
                RectangleCorner(x1,x2,x3,y1,y2,y3,kx1,ry1,rx2,ry2,rx3,ky1,kx1,ky1,splittingNx,splittingNy,g);
                RectangleCorner(x1,x2,x3,y1,y2,y3,kx1,ky1,rx2,ky1,rx3,ry3,kx1,ry4,splittingNx,splittingNy,g);
                RectangleCorner(x1,x2,x3,y1,y2,y3,rx1,ky1,kx1,ky1,kx1,ry3,rx4,ry4,splittingNx,splittingNy,g);
            }
        }
        else if(Math.abs((rx2-rx1)) == splittingNx && Math.abs((ry4-ry1)) == splittingNy)
        {
            g.setColor(Color.green);
            if(b1 && b2 && b3 && b4) //0000
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && b2 && b3 && b4) //1000
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && !b2 && b3 && b4) //01000
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && b2 && !b3 && b4) //0010
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && b2 && b3 && !b4) //0001
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && !b2 && b3 && b4) // 1100
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && b2 && !b3 && b4) //1010
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && b2 && b3 && !b4) //1001
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && !b2 && !b3 && b4) //1110
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && !b2 && b3 && !b4) //1101
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(!b1 && b2 && !b3 && !b4) //1011
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && !b2 && !b3 && !b4) //0111
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && !b2 && b3 && !b4) //0101
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && b2 && !b3 && !b4) //0011
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            if(b1 && !b2 && !b3 && b4)
            {
                g.fillRect(rx1,ry1,(rx2-rx1),(ry4-ry1));
            }
            // Дописать все случаи которые возможны
            g.setColor(Color.MAGENTA);
            g.drawLine(x1,y1,x2,y2);
            g.drawLine(x2,y2,x3,y3);
            g.drawLine(x1,y1,x3,y3);
            g.setColor(Color.black);
        }
        else {
            return;
        }

    }
    public Boolean Point(int x1,int x2,int x3,int y1,int y2,int y3,int x0,int y0)
    {
        int A= ((x1-x0)*(y2-y1)-(x2-x1)*(y1-y0));
        int B= ((x2-x0)*(y3-y2)-(x3-x2)*(y2-y0));
        int C= ((x3-x0)*(y1-y3)-(x1-x3)*(y3-y0));
        return (A >= 0 && B >= 0 && C >= 0) || (A <= 0 && B <= 0 && C <= 0);
    }
}
