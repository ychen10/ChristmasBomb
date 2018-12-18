import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Main GUI class of the game, which contains the JFrame, game and tree.
 * In charge of putting together all the components of the GUI and
 * connecting it with the game object.
 *
 * @author Yiran Chen
 * @version 12/08/2018
 */
public class GameGUI {
  public static void main(String[] args) {
    // creating the main frame
    JFrame frame = new JFrame ("Christams Bomb");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    // size
    frame.setPreferredSize(new Dimension(800, 900));
    ChristmasTree tree = new ChristmasTree();
    Game game = new Game(tree);
    
    // start panel
    StartPanel start = new StartPanel(game);
    
    // adding & packing
    frame.getContentPane().add(start);
    frame.pack();
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setResizable(true);
  }
}
