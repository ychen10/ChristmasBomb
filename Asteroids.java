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

public class Asteroids extends JPanel {
    int astr1X = 50;
    int astr1Y = 30;
    
    private final static int OFFSET = 5;
    private final static int WIDTH = 20;
    private final static int HEIGHT = 20;
    private Timer timer = null;
    private BufferedImage image;


    public Asteroids() {
        timer = new Timer(50, new ActionListener() {     // timer with 150 millisecond delay
            public void actionPerformed(ActionEvent e) {
                astr1Y += OFFSET;                         // add 5 t the y poistion
                

                repaint();
            }
        });
        timer.start();
        
        try {                
          image = ImageIO.read(new File("bomb.png"));
       } catch (IOException ex) {
            // handle exception...
       }

        Action downAction = new AbstractAction() {        // slows down the timer
            public void actionPerformed(ActionEvent e) {
                int delay = timer.getDelay();
                if (delay < 1000) {
                    delay += 100;
                    timer.setDelay(delay);
                }
            }
        };

        Action upAction = new AbstractAction() {         // speeds up the timer
            public void actionPerformed(ActionEvent e) {
                int delay = timer.getDelay();
                if (delay > 100) {
                    delay -= 100;
                    timer.setDelay(delay);
                }
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("UP"), "upAction");  // up key binding
        getActionMap().put("upAction", upAction);
        getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "downAction");  // down key binding
        getActionMap().put("downAction", downAction);
    }
    
    

    


    private static void createAndShowGui() {
        JFrame frame = new JFrame();
        frame.add(new Asteroids());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public Dimension getPreferredSize() {
        return new Dimension(350, 600);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // loop through active vector and put this in the loop
        g.drawImage(image, astr1X, astr1Y, this); 


        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}