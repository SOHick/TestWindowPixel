package GaussBlur;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main2 {
    public static void main(String[] args) {
        MainGraphicsPicture graphics = null;
        try {
            graphics = new MainGraphicsPicture();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        graphics.setVisible(true);
    }
}