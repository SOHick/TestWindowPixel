import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    public void paintComponent(Graphics g)
    {
        int splittingNx = 10;
        int splittingNy = 10;

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
        //drawLine(dx,dy,splittingNx,splittingNy,g);
        drawTriangle(splittingNx,splittingNy,g);

    }
    public void drawSquare(int dx,int dy,int splittingNx ,int splittingNy, Graphics g)
    {
        for(int i =0; i<=dx;i++)
        {
            for(int j =0; j<=dy;j++)
            {
                if(i>=1 && i<=5 && j>=1 && j<=5 )
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
            System.out.println("22222");
            for(int j =0; j<=dx;j++) // x
            {
                int i = (-C-A*j)/(B); // y
                g.fillRect(splittingNx*j,splittingNy*i,splittingNx,splittingNy);
            }
        }
        else if(A < B)
        {
            System.out.println("33333");
            for(int i =0; i<=dy;i++) // y
            {
                int j = (-C-B*i)/(A); // x
                g.fillRect(splittingNx*j,splittingNy*i,splittingNx,splittingNy);
            }
        }
    }
    // y = Ax+B
    // y = Cx+D
    // y = Mx+N
    public void drawTriangle(int splittingNx ,int splittingNy, Graphics g)
    {
        int x1=1;
        int y1=1;
        int x2=5;
        int y2=3;
        int x3=9;
        int y3=1;
        FindPoint(x1,x2,y1,y2,splittingNx,splittingNy,g);
        FindPoint(x2,x3,y2,y3,splittingNx,splittingNy,g);

    }
    public void FindPoint(int x1,int x2,int y1,int y2,int splittingNx ,int splittingNy, Graphics g)
    {
        // Для 1 и 2
        for(int i =x1; i<=x2; i++)
        {
            int j = ((i -x1)*(y2-y1))/(x2-x1)+y1;
           if(y1<y2)
           {
               for(int k=y1; k<=j;k++)
               {
                   g.fillRect(splittingNx*i,splittingNy*k,splittingNx,splittingNy);
               }
           }
           else
           {
               for(int k=y2; k<=j;k++)
               {
                   g.fillRect(splittingNx*i,splittingNy*k,splittingNx,splittingNy);
               }
           }
        }
    }
}
