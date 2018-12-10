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
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Asteroids extends JPanel {
    int astr1X = 50;
    int astr1Y = 30;
    
    private final static int OFFSET = 5;
    private final static int WIDTH = 20;
    private final static int HEIGHT = 20;
    private Timer timer = null;
    private BufferedImage image;
    private JPanel mousepanel;
    private Point initialClick;
    private JLabel jLabel1;
    
    


    public Asteroids() {
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon("Tree.png"));
        add(jLabel1);
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
    
     private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
          initialClick = evt.getPoint();
    }  
    
     private void jLabel1MouseDragged(java.awt.event.MouseEvent evt) {                                     

        int thisX = jLabel1.getLocation().x;
        int thisY = jLabel1.getLocation().y;

        // Determine how much the mouse moved since the initial click
        int xMoved = (thisX + evt.getX()) - (thisX + initialClick.x);
        int yMoved = (thisY + evt.getY()) - (thisY + initialClick.y);

        // Move picture to this position
        int X = thisX + xMoved;
        int Y = thisY + yMoved;

        jLabel1.setLocation(X, Y);
        jLabel1.repaint();


    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }
}