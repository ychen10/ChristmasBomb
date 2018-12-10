import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.border.*;
import javax.swing.Timer;
/**
 * Write a description of class GameOver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GameOverPanel extends JPanel
{   

    private Game game;
    private BufferedImage image;
    private JLabel label;

    public GameOverPanel(Game game){
        this.game = game;
        setLayout(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel();

        if (game.endGame()){
            if (game.isWin()){
                try {
                    image = ImageIO.read(new File("win.png"));
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }else{// lose
                try {
                    image = ImageIO.read(new File("gameOver.png"));
                } catch (IOException ex) {
                    System.out.println(ex);
                } 
            }
            
            label.setIcon(new ImageIcon(image));
            add(label);
        }
    }

}
    

