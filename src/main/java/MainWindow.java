import sun.rmi.rmic.Main;

import javax.swing.*;

/**
 * Created by Tymek on 16.01.2017.
 */
public class MainWindow {
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel loginPanel;
    private JButton button1;
    private JButton button2;
    private JTree tree1;
    private JTable table1;

    public static void main(String[] args){
        new MainWindow();
        System.out.println("Hello World");
        JFrame frame = new JFrame("Sklep muzyczny");
        frame.setContentPane(new MainWindow().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
