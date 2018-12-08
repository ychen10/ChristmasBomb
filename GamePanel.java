
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
  // Constructor. Notice how it takes an instance of the game as input!
  public GamePanel() {
    JPanel above = new JPanel();
    above.setBackground(new Color(0, 0, 0, 100));
    add(above);
  }
}
