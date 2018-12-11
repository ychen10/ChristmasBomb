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
  private Timer timer = null;
  private BufferedImage bomb, tree, gift;
  private Game game;
  private ChristmasTree ct;
  private int mouse;
  private final int treeY = 450;

  public GamePanel(Game game) {
    this.game = game;
    this.ct = game.getTree();
    this.ct.setLocation(0, treeY);
    timer = new Timer(500, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
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

    MouseHandler mh = new MouseHandler();
    addMouseListener(mh);
    addMouseMotionListener(mh);
  }

  public Dimension getPreferredSize() {
    return new Dimension(500, 750);
  }

  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    game.drop();
    Vector<Item> active = game.getActive();

    ct.setLocation(mouse, treeY);
    g.drawImage(tree, mouse, treeY, this);
    //treeLabel.setLocation(mouse, treeY);

    for (Item item : active) {
      if (item.isGift()) {
        g.drawImage(gift, item.getX(), item.getY(), this);
      } else {
        g.drawImage(bomb, item.getX(), item.getY(), this);
      }
    }
  }

  public class MouseHandler extends MouseAdapter {
    public void mouseMoved(MouseEvent event) {
      int x = event.getX() - 150 > 500 ? 500 : event.getX() - 155;
      mouse = x;
      //mouse = event.getX();
      System.out.println("Mouse: [" +event.getX() +", "+event.getY()+"] Tree: "+mouse);
      //System.out.println("mouse:" + event.getX() + "  " + "tree: " + mouse);
    }
  }
}
