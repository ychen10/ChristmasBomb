import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

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
    ChristmasTree tree = new ChristmasTree();
    Game game = new Game(tree);

    StartPanel start = new StartPanel(game);
    //GameOverPanel over = new GameOverPanel(game);
 
    frame.getContentPane().add(start);
    //frame.getContentPane().add(over);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setResizable(true);
  }
}
