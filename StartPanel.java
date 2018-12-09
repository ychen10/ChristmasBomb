
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class StartPanel extends JPanel {
  JButton start;
  JPanel game;

  public StartPanel() {
    try {
      JLabel label = new JLabel(); // background of the label
      label.setLayout(new FlowLayout(FlowLayout.CENTER));
      BufferedImage background = ImageIO.read(new File("background.png"));
      label.setIcon(new ImageIcon(background));

      game = new GamePanel();
      label.add(game);
      game.setVisible(false);
      game.setOpaque(false);;

      // JButton
      start = new JButton("START");
      StartListener listener = new StartListener();
      start.addActionListener(listener);

      label.add(start);
      add(label);
    
    } catch (IOException e) {
      System.out.println(e);
    }
    
  }

  private class StartListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      start.setEnabled(false);
      start.setVisible(false);
      game.setVisible(true);
      //game.start();
      // start the game
    }
  }
}
