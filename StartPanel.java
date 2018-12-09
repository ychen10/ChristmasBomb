
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;

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
      
    } catch (IOException e) {
      System.out.println(e);
    }

      // JButton
      start = new JButton("START");
      StartListener listener = new StartListener();
      start.addActionListener(listener);

      label.add(start);
    
      label.setPreferredSize(new Dimension(800, 900));

      add(label);
    
  }

  public void addScore() {
    JLabel score = new JLabel("<html><center><h1>  Score:<br>" + game.getScore() + " / 1000  </h1></center></html>");
      score.setBorder(new EmptyBorder(5,15,5,15));

      score.setBackground(Color.WHITE);
      score.setOpaque(true);
      //score.setPreferredSize(new Dimension(200, 100));
      label.add(score);
  }

  private class StartListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      start.setEnabled(false);
      start.setVisible(false);
      gamePanel.setVisible(true);
      label.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 50));
      addScore();
      game.start();
      // start the game
    }
  }
}
