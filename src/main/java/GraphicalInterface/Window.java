package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tymek on 01.03.2017.
 */
public class Window {

    private JFrame jFrame;

    public JFrame getjFrame() {
        return this.jFrame;
    }

    public void createWindow(String name, JPanel jPanel, int width, int height){
        JFrame frame = new JFrame(name);
        frame.setContentPane(jPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(width, height));
        frame.pack();
        frame.setVisible(true);
        this.jFrame = frame;
    }
}
