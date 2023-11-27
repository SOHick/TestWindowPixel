package GrayScale;

import BoundaryDetector.BoundaryDetectorPanel;

import javax.swing.*;
import java.awt.*;

public class MainGraphicsGray extends JFrame
{
    public MainGraphicsGray()
    {

        Dimension minSize = new Dimension(1280, 720);
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout gl = new GroupLayout(getContentPane()); // Наше окно без рамок
        setLayout(gl);
        PictureGrayPanel mainPanel = new PictureGrayPanel();
        mainPanel.setBackground(Color.lightGray);
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE)
                )
                .addGap(8)
        );
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createSequentialGroup()
                        .addComponent(mainPanel,GroupLayout.DEFAULT_SIZE,400,GroupLayout.DEFAULT_SIZE)


                )
                .addGap(8)
        );
    }
}
