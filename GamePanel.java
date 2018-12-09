import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.geom.Area;

public class GamePanel extends JPanel {
  int astr1X = 50;
  int astr1Y = 30;
  private final static int OFFSET = 5;
  private Timer timer = null;
  private BufferedImage bomb, tree, gift;
  private Game game;

  public GamePanel(Game game) {
    this.game = game;
    timer = new Timer(50, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        astr1Y += OFFSET; // add 5 t the y poistion
        repaint();
      }
    });

    timer.start();

    try {
      bomb = ImageIO.read(new File("bomb.png"));
      tree = ImageIO.read(new File("tree.png"));
      gift = ImageIO.read(new File("present.png"));
    } catch (IOException ex) {
      System.out.println(ex);
    }

  }

  public Dimension getPreferredSize() {
    return new Dimension(500, 750);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    game.drop();
    Vector<Item> active = game.getActive();
    for (Item item : active) {
      if (item.isGift()) {
        g.drawImage(gift, item.getX(), item.getY(), this);
      } else {
        g.drawImage(bomb, item.getX(), item.getY(), this);
      }
    }
  }
}