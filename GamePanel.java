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

/**
 * Game Panel
 * This class shows the game panel GUI where we will run our game, 
 * and connect front end GUI with backend method in this class.
 * 
 * @author Yiran Chen, Qihan Deng
 * @version 12/08/2018
 */
public class GamePanel extends JPanel {
    // instance variables
    private Timer timer = null;
    private BufferedImage bomb, tree, gift;// image that will be imported
    private Game game;
    private ChristmasTree ct;
    private int mouse;// the x location of the mouse
    private final int treeY = 440; // 450 + 25

    /**
     * This constructor create the game object
     * sets up the game with Tree, timer and icons
     *@param pass in the Game object for the constructor
     */
    public GamePanel(Game game) {
        this.game = game;
        this.ct = game.getTree();
        this.ct.setLocation(0, treeY);

        // creates a timer for checking status of the game
        // refresh the panel every tick for purpose of animation
        timer = new Timer(20, new ActionListener() { // timer with 150 millisecond delay
                public void actionPerformed(ActionEvent e) {
                    if (game.win() || game.lose()) game.endGame();// test out if game is still playing
                    repaint();// repaint the screen for annimation
                }
            });

        // start the timer
        timer.start();
        // read in images for bombs, tree, and present
        try {
            bomb = ImageIO.read(new File("bomb.png"));
            tree = ImageIO.read(new File("tree.png"));
            gift = ImageIO.read(new File("present.png"));
        } catch (IOException ex) {
            System.out.println(ex);
        }

        // mouse handler to connect mouse moving event with ChristmasTree
        MouseHandler mh = new MouseHandler();
        addMouseListener(mh);
        addMouseMotionListener(mh);
    }

    /**
     * Get the preferred size of the game panel itself (overriding the build-in method)
     */
    public Dimension getPreferredSize() {
        return new Dimension(500, 750); // 250 - 160, 375 - 160
    }

    /**
     * This method repaint the screen for every tick and check the game status
     * @param Graphics g in order to use the Graphics method
     */
    protected void paintComponent(Graphics g) {
        if (game.didEnd()) {// test out the game status
            try {// read in the gameOver or win icon
                BufferedImage image = ImageIO.read(new File("gameOver.png"));
                if (game.win()) image = ImageIO.read(new File("win.png"));
                g.drawImage(image, 90, 215, this);// draw the icon depends on the status of the game
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            super.paintComponent(g);
            game.drop();// drop the present and bomb from the top of the screen
            LinkedList<Item> active = game.getActive();

            ct.setLocation(mouse, treeY);
            g.drawImage(tree, mouse, treeY, this);// draw the tree

            // loops through the active linked list and display the image (bomb/gift)
            for (Item item : active) {
                // System.out.println("item at: " + item.getX() + ", " + item.getY());
                if (item.isGift()) {// if the gift is a gift
                    g.drawImage(gift, item.getX(), item.getY(), this);// draw gift
                } else {
                    g.drawImage(bomb, item.getX(), item.getY(), this);// draw bomb
                }
            }
        }
    }

    /**
     * MouseHandler class connects ChristmasTree with mouse moving
     */
    public class MouseHandler extends MouseAdapter {
        /**
         * This method updates/limits the location of the moyse to be horizontal
         * @param MouseEvent event, pass in an event for connection
         */
        public void mouseMoved(MouseEvent event) {
            int x = event.getX() - 150 > 500 ? 500 : event.getX() - 155;
            mouse = x; // sets mouse to the x coordinate
            //System.out.println("tree at: " + ct.getX() + ", " + ct.getY());
        }
    }
}
