
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class StartPanel extends JPanel {
  JButton start;
  JPanel gamePanel;
  Game game;
  JLabel label;

  public StartPanel(Game game) {
    this.game = game;
    setLayout(new FlowLayout(FlowLayout.CENTER));
    try {
      label = new JLabel(); // background of the label
      label.setLayout(new FlowLayout(FlowLayout.CENTER));
      BufferedImage background = ImageIO.read(new File("background.png"));
      label.setIcon(new ImageIcon(background));

      gamePanel = new GamePanel(this.game);
       
      label.add(gamePanel);
      gamePanel.setVisible(false);

      // JButton
      start = new JButton("START");
      StartListener listener = new StartListener();
      start.addActionListener(listener);

      label.add(start);
      
      label.setPreferredSize(new Dimension(800, 900));

      add(label);

    } catch (IOException e) {
      System.out.println(e);
    }
    

    
  }

  private class StartListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      start.setEnabled(false);
      start.setVisible(false);
      gamePanel.setVisible(true);
      label.setLayout(new FlowLayout(FlowLayout.LEFT));
      game.start();
      // start the game
    }
  }
}
