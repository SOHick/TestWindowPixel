package GaussBlur;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainGraphicsPicture extends JFrame
{
    public MainGraphicsPicture() throws IOException {
        Dimension minSize = new Dimension(1024, 720);
        setSize(minSize);
        setMinimumSize(minSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GroupLayout gl = new GroupLayout(getContentPane()); // Наше окно без рамок
        setLayout(gl);
       PictureMassPanel mainPanel = new PictureMassPanel();
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
