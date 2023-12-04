package MeshPictureRing;

import MeshPictureRectangle.Mesh;
import MeshPictureRectangle.dataGet;

import javax.swing.*;
import java.awt.*;

public class PictureMeshPanelRing extends JPanel
{
    public void paintComponent(Graphics g)
    {
        MeshRing mesh = new MeshRing(g,getWidth(),getHeight());
    }


}
