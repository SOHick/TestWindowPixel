package MeshPictureRing;
import MeshPictureRectangle.Convector;
import java.awt.*;

public class MeshRing
{
    int th = 2;
    double DN = 0.2;
    String Ox;
    String Oy;
    double scala = Math.pow(10,1);
    int xCenter, yCenter;
    int DXY = 10;
    double xMin = -DXY , yMin =-DXY;
    double xMax = DXY, yMax = DXY;
    int mainWidth;
    int mainHeight;
    double [][] PointXY;
    Convector a;

    public MeshRing(Graphics g, int width, int height)
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
        MeshPictureRing2(1,4,12,g);

    }

    public void MeshPictureRing2(double r,double R,int N,Graphics g)
    {
        double teta = (2*Math.PI)/N; // Угол поворота
        int[][] connectivity = new int[N][4];
        double [][] PointXYR = PointRing(N,R,teta,g);
        double [][] PointXYr = PointRing(N,r,teta,g);
        int p = N*N;
        PointXY = new double[p][2]; // all Points
        PointXY[0][0] = PointXYR[0][0]; // 1
        PointXY[0][1] = PointXYR[0][1]; // 1
        for(int j =0; j <=N-1 ; j++)
        {
            PointXY[2*j][0]  =  PointXYR[j][0];
            PointXY[2*j][1]  =  PointXYR[j][1];
            PointXY[2*j+1][0]  =  PointXYr[j][0];
            PointXY[2*j+1][1]  =  PointXYr[j][1];
        }
        g.setColor(Color.blue);
        for(int j =0; j<= N-1; j++)
        {
            g.drawLine(a.xCrt2Scr(PointXYr[j][0]),a.yCrt2Scr(PointXYr[j][1]),a.xCrt2Scr(PointXYR[j][0]),a.yCrt2Scr(PointXYR[j][1]));
        }
        g.setColor(Color.black);
        for(int j =0; j<= N-2; j++)
        {
            connectivity[j][0] = 2*j +1;
            connectivity[j][1] = 2*j +2;
            connectivity[j][2] = 2*j +4;
            connectivity[j][3] = 2*j +3;

        }
        connectivity[N-1][0] = 2*(N-2) +4;
        connectivity[N-1][1] = 2*(N-2) +3;
        connectivity[N-1][2] = 2;
        connectivity[N-1][3] = 1;
        MeshTwoRing(connectivity,PointXY,N,g);

    }
    public void MeshTwoRing(int[][] connectivity,double[][] PointXY,int N,Graphics g)
    {
        for(int j =0; j<=N-1; j++)
        {
            int p1x = connectivity[j][0] - 1;
            int p2x = connectivity[j][1] - 1;
            int p4x = connectivity[j][2] - 1;
            int p3x = connectivity[j][3] - 1;
            int[] P;
            if(j<N-1) {
                P = new int[]{
                        a.xCrt2Scr(PointXY[p1x][0]), a.yCrt2Scr(PointXY[p1x][1]),
                        a.xCrt2Scr(PointXY[p2x][0]), a.yCrt2Scr(PointXY[p2x][1]),
                        a.xCrt2Scr(PointXY[p4x][0]), a.yCrt2Scr(PointXY[p4x][1]),
                        a.xCrt2Scr(PointXY[p3x][0]), a.yCrt2Scr(PointXY[p3x][1]),
                };
            }
          else {
                P = new int[]{
                        a.xCrt2Scr(PointXY[p3x][0]), a.yCrt2Scr(PointXY[p3x][1]),
                        a.xCrt2Scr(PointXY[p4x][0]), a.yCrt2Scr(PointXY[p4x][1]),
                        a.xCrt2Scr(PointXY[p1x][0]), a.yCrt2Scr(PointXY[p1x][1]),
                        a.xCrt2Scr(PointXY[p2x][0]), a.yCrt2Scr(PointXY[p2x][1]),
                };
            }
            NewRectangleMesh(g, P);
        }
    }
    public double[][] PointRing(int N,double R,double teta,Graphics g)
    {
        double[][] PointXY = new double[N][2]; // X and Y
        int[] PointX = new int[N]; // X
        int[] PointY = new int[N]; //  Y
        PointX[0] = a.xCrt2Scr(-R);
        PointY[0]= a.yCrt2Scr(0);
        PointXY[0][0] = -R;
        PointXY[0][1] = 0;

        for(int j =1; j<= N-1; j++)
        {
            //double k = Math.round(j * scala) / scala;
            PointXY[j][0] = PointXY[j-1][0]*Math.cos(teta) - PointXY[j-1][1]*Math.sin(teta);
            PointXY[j][1] = PointXY[j-1][0]*Math.sin(teta) + PointXY[j-1][1]*Math.cos(teta);
            PointX[j] = a.xCrt2Scr(PointXY[j][0]);
            PointY[j]= a.yCrt2Scr(PointXY[j][1]);

        }
        g.drawPolygon(PointX,PointY,PointX.length);

        return PointXY;
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
    public void NewRectangleMesh(Graphics g,int[] P)
    {
        int[] xPoints ={P[0],P[2],P[4],P[6]};
        int[] yPoints = {P[1],P[3], P[5], P[7]};
        g.drawPolygon(xPoints,yPoints,xPoints.length);
        for(double i = DN-1; i < 1;i+=DN)
        {
            double j = Math.ceil(i * scala) / scala;
            double y=-1;
            double OYksi1 = F1(j,y,a.xScr2Crt(P[0]))+F2(j,y,a.xScr2Crt(P[2]))+F3(j,y,a.xScr2Crt(P[4]))+F4(j,y,a.xScr2Crt(P[6]));
            double OYeta1 = F1(j,y,a.xScr2Crt(P[1]))+F2(j,y,a.xScr2Crt(P[3]))+F3(j,y,a.xScr2Crt(P[5]))+F4(j,y,a.xScr2Crt(P[7]));
            double OYksi2 = F1(j,-y,a.xScr2Crt(P[0]))+F2(j,-y,a.xScr2Crt(P[2]))+F3(j,-y,a.xScr2Crt(P[4]))+F4(j,-y,a.xScr2Crt(P[6]));
            double OYeta2 = F1(j,-y,a.xScr2Crt(P[1]))+F2(j,-y,a.xScr2Crt(P[3]))+F3(j,-y,a.xScr2Crt(P[5]))+F4(j,-y,a.xScr2Crt(P[7]));

            double OXksi1 = F1(y,j,a.xScr2Crt(P[0]))+F2(y,j,a.xScr2Crt(P[2]))+F3(y,j,a.xScr2Crt(P[4]))+F4(y,j,a.xScr2Crt(P[6]));
            double OXeta1 = F1(y,j,a.xScr2Crt(P[1]))+F2(y,j,a.xScr2Crt(P[3]))+F3(y,j,a.xScr2Crt(P[5]))+F4(y,j,a.xScr2Crt(P[7]));
            double OXksi2 = F1(-y,j,a.xScr2Crt(P[0]))+F2(-y,j,a.xScr2Crt(P[2]))+F3(-y,j,a.xScr2Crt(P[4]))+F4(-y,j,a.xScr2Crt(P[6]));
            double OXeta2 = F1(-y,j,a.xScr2Crt(P[1]))+F2(-y,j,a.xScr2Crt(P[3]))+F3(-y,j,a.xScr2Crt(P[5]))+F4(-y,j,a.xScr2Crt(P[7]));
            g.drawLine(a.xCrt2Scr(OYksi1),a.xCrt2Scr(OYeta1),a.xCrt2Scr(OYksi2),a.xCrt2Scr(OYeta2));
            g.drawLine(a.xCrt2Scr(OXksi1),a.xCrt2Scr(OXeta1),a.xCrt2Scr(OXksi2),a.xCrt2Scr(OXeta2));

        }
    }
    public double F1(double x,double y,double P)
    {
        return (x-1)*(y-1)*P/4;
    }
    public double F2(double x,double y,double P)
    {
        return -(x+1)*(y-1)*P/4;
    }
    public double F3(double x,double y,double P)
    {
        return (x+1)*(y+1)*P/4;
    }
    public double F4(double x, double y,double P)
    {
        return -(x-1)*(y+1)*P/4;
    }
}
