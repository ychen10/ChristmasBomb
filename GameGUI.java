import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class GameGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameGUI {
  public static void main(String[] args) {
    JFrame frame = new JFrame ("Christams Bomb");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1000, 1000));

    GamePanel panel = new GamePanel();
    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);


    frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setBackground(new Color(0, 0, 0, 0));
        frame.setVisible(true);

  }
}
