
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import javax.swing.Timer;
import sun.audio.*;

public class StartPanel extends JPanel {
  private JButton start;
  private JPanel gamePanel;
  private Game game;
  private JLabel label, time, score;
  private Timer timer, scoreLabel;
  private int timeLeft = 120;

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

      add(label);
    
  }

  public void addScoreTime() {
    JLabel background = new JLabel();
    background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
    background.setPreferredSize(new Dimension(150, 400));
    //background.setOpaque(true);

    score = new JLabel("<html><center><h1>Score:<br>" + game.getScore() + "/ 1000</h1></center></html>");
    score.setBorder(new EmptyBorder(5,15,5,15));
    score.setOpaque(true);
    background.add(score);

    background.add(Box.createRigidArea (new Dimension(0, 100)));

    time = new JLabel("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>", SwingConstants.CENTER);
    time.setBorder(new EmptyBorder(5,15,5,15));
    time.setBackground(Color.WHITE);
    time.setOpaque(true);
    background.add(time);

    label.add(background);
  }

  // takes care of score too
  public void startCountdown() {
    timer = new Timer(1000, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        if (game.didEnd()) {
          timer.stop();
          return;
        }
        if (timeLeft <= 0) {
          game.setLose();
          timer.stop();
          return;
        }

        timeLeft--;

        if (timeLeft <= 15) {
          time.setForeground(Color.red);
          time.setText("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>");
        } else {
          time.setText("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>");
        }
      }
    });

    scoreLabel = new Timer(20, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        score.setText("<html><center><h1>Score:<br>" + game.getScore() + "/ 1000</h1></center></html>");
      }
    });

    scoreLabel.start();
    timer.start();
  }
  private class StartListener implements ActionListener {
    public void actionPerformed(ActionEvent event) {
      start.setEnabled(false);
      start.setVisible(false);
      gamePanel.setVisible(true);
      label.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 50));
      addScoreTime();
      startCountdown();
      game.start();
      music();
    }
  }

  public static void music() {
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;
    ContinuousAudioDataStream loop = null;
    try {
      BGM = new AudioStream(new FileInputStream("westernBeat.wav"));
      // System.out.println("playing music rn");
      MD = BGM.getData();
      loop = new ContinuousAudioDataStream(MD);
    } catch (IOException error) {
      System.out.print("file not found");
    }
    MGP.start(loop);
  }
}
  

  

