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

/**
 * Start Panel is displayed when the user starts the game and
 * plays the audio file. Displays the game panel as the user
 * presses the start button
 *
 * @author Yiran Chen
 * @version 12/08/2018
 */
public class StartPanel extends JPanel {
  private JButton start;
  private JPanel gamePanel;
  private Game game;
  private JLabel label, time, score;
  private Timer timer, scoreLabel;
  private int timeLeft = 120;

  /**
   * Constructor of the StartPanel Class
   *
   * @param a Game object
   */
  public StartPanel(Game game) {
    this.game = game;
    // setLayout(new FlowLayout(FlowLayout.CENTER));
    try {
      // dispaly background
      label = new JLabel(); // background of the label
      // label.setLayout(new FlowLayout(FlowLayout.CENTER));
      BufferedImage background = ImageIO.read(new File("background.png"));
      label.setIcon(new ImageIcon(background));

      gamePanel = new GamePanel(this.game);

      // add it to game panel
      label.add(gamePanel);
      gamePanel.setVisible(false);

    } catch (IOException e) {
      System.out.println(e);
    }

    // Create the start button displayed on the start screen
    start = new JButton("START");
    // Create a Startlistener and add it to the button just created
    StartListener listener = new StartListener();
    start.addActionListener(listener);
    start.setSize(80, 50);
    start.setLocation(350, 400);

    label.add(start);
    // add label
    add(label);
  }

  /**
   * Adds score and time to the panel
   */
  public void addScoreTime() {
    // create a JLabel "background" for score and time
    JLabel background = new JLabel();
    background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
    background.setPreferredSize(new Dimension(150, 400));
    //background.setOpaque(true);

    // set the score label
    score = new JLabel("<html><center><h1>Score:<br>" + game.getScore() + "/ 1000</h1></center></html>");
    score.setBorder(new EmptyBorder(5,15,5,15));
    score.setOpaque(true);
    background.add(score);

    background.add(Box.createRigidArea (new Dimension(0, 100)));

    // time label
    time = new JLabel("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>", SwingConstants.CENTER);
    time.setBorder(new EmptyBorder(5,15,5,15));
    time.setBackground(Color.WHITE);
    time.setOpaque(true);
    background.add(time);

    // add the labels to the background
    label.add(background);
  }

  /**
   * Uses the timer to start counting down the time (1000 sec)
   * and changes the score and time accordingly
   */
  public void startCountdown() {
    // timer
    timer = new Timer(1000, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        // end the program if the game ends
        if (game.didEnd()) {
          timer.stop();
          return;
        }
        // if time runs out, game ends
        if (timeLeft <= 0) {
          game.setLose();
          timer.stop();
          return;
        }
        // decrements time
        timeLeft--;
        // changes color of the letters when below 15 seconds
        if (timeLeft <= 15) {
          time.setForeground(Color.red);
          time.setText("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>");
        } else {
          time.setText("<html><center><h1>Time Left:<br>" + timeLeft + "s</h1></center></html>");
        }
      }
    });
    
    // score label
    scoreLabel = new Timer(20, new ActionListener() { // timer with 150 millisecond delay
      public void actionPerformed(ActionEvent e) {
        score.setText("<html><center><h1>Score:<br>" + game.getScore() + "/ 1000</h1></center></html>");
      }
    });
    
    // adding score label and timer
    scoreLabel.start();
    timer.start();
  }

  /**
   * StartListener class defines what happens when the start button is clicked.
   * When the start button is clicked, the game displays the game panel and
   * start the game accordingly by adding score, starting the timer, and playing the music.
   */
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


  /**
   * Plays music using AudioPlayer and the designated music file
   */
  public static void music() {
    // using AudioPlayer
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
