package MeshPictureRectangle;

import javax.swing.*;
import java.awt.*;

public class PictureMeshPanel extends JPanel
{

    public void paintComponent(Graphics g)
    {
        //MeshTriangle(g);
        Mesh mesh = new Mesh(g,getWidth(),getHeight());

    }
    public void MeshTriangle(Graphics g)
    {
        dataGet dataGet = new dataGet();
        dataGet.setInfo(4);
//        int[][] elems = dataGet.getElems();
//        double[][] nodes = dataGet.getNodes();
//        for(int i =0; i<dataGet.nElems(); i++)
//        {
//            //paintTriangle(elems,nodes,i,g);
//            paintPolygon(elems,nodes,i,g);
//        }

    }

    public void paintTriangle(int[][] elems,double[][] nodes,int i,Graphics g)
    {
        int p1 = elems[i][0];
        int p2 = elems[i][1];
        int p3 = elems[i][2];
        double x1 = nodes[p1][0];
        double y1 = nodes[p1][1];
        double x2 = nodes[p2][0];
        double y2 = nodes[p2][1];
        double x3 = nodes[p3][0];
        double y3 = nodes[p3][1];
        System.out.println("x1=" + x1 + "," + "x2=" + x2 + "," + "x3=" + x3);
        g.drawLine((int) x1,(int)y1,(int)x2,(int)y2);
        g.drawLine((int) x3,(int)y3,(int)x2,(int)y2);
        g.drawLine((int) x1,(int)y1,(int)x3,(int)y3);
    }
    public void paintPolygon(int[][] elems,double[][] nodes,int i,Graphics g)
    {
        int l = elems[0].length;
        int[] xPoints = new int[l];
        int[] yPoints = new int[l];
        for (int j = 0; j < l; j++)
        {
            xPoints[j] = (int) nodes[elems[i][j]][0];
            yPoints[j] = (int) nodes[elems[i][j]][1];
        }
        g.drawPolygon(xPoints,yPoints,l);

    }



}
