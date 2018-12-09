import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
  int astr1X = 50;
  int astr1Y = 30;
  private final static int OFFSET = 5;
  private Timer timer = null;
  private BufferedImage image;

  public GamePanel() {
    timer = new Timer(50, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        astr1Y += OFFSET; // add 5 t the y poistion

        repaint();
      }
    });

    timer.start();

    try {
      image = ImageIO.read(new File("bomb.png"));
    } catch (IOException ex) {
      // handle exception...
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(350, 600);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // loop through active vector and put this in the loop
    g.drawImage(image, astr1X, astr1Y, this);
    game.drop();

    /**
     * for ()
     * {
     *  update their loacation
     *  if game.doCllide() == 1 {
     * }
     *  
     *     
     * }
     * }
     */

  }
}