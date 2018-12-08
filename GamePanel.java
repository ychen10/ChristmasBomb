
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
  //instance vars
  private JButton[][] buttons;
  private JButton quitButton, againButton;
  private JLabel statusLabel;
  private ImageIcon xImg, oImg, tieImg; //these images will be used in a couple
  // of diff methods,so make them instance vars, and create them only once.




  // Constructor. Notice how it takes an instance of the game as input!
  public GamePanel() {

    // xImg = createImageIcon("Tree.jpg", "a Tree image");
    // oImg = createImageIcon("Gift.jpg", "a Gift image");
    // tieImg = createImageIcon("Bomb.jpg", "a tie image");
    //
    // statusLabel = new JLabel("Player X goes first", xImg, JLabel.CENTER);

    setLayout(new BorderLayout(10, 10)); // hgap, vgap
    //setBackground(Color.white); // to match the background color of center grid panel

    try {
      // BufferedImage myPicture = ImageIO.read(new File("test.png"));
      // // Graphics2D g = myPicture.createGraphics();
      // // g.drawImage(myPicture, null, 100, 100);
      // JLabel picLabel = new JLabel();
      // picLabel.add(g);
      // picLabel.setOpaque(true);
      BufferedImage myPicture = ImageIO.read(new File("test.png"));
      JLabel picLabel = new JLabel(new ImageIcon(myPicture));
      add(picLabel);
    } catch(IOException e){
      System.out.println(e);
    }
    setLayout(null);

  }

  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = GamePanel.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
}
