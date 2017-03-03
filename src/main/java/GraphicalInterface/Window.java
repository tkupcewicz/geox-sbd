package GraphicalInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tymek on 01.03.2017.
 */
public class Window {

    public void createWindow(String name, JPanel jPanel, int minSize, int maxSize){
        JFrame frame = new JFrame(name);
        frame.setContentPane(jPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(minSize, maxSize));
        frame.pack();
        frame.setVisible(true);
    }
}
