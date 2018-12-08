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
    frame.setPreferredSize(new Dimension(800, 900));

    StartPanel start = new StartPanel();
 
    frame.getContentPane().add(start);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
  }
}
