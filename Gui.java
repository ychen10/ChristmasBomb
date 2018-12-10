import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;


public class Gui extends JFrame{
    private JPanel mousepanel;
    private Point initialClick;
    private JLabel jLabel1;

    public Gui(){
        super("Moving Tree");

        mousepanel = new JPanel();
        add(mousepanel, BorderLayout.CENTER);

        
        
        jLabel1 = new JLabel();
        jLabel1.setIcon(new ImageIcon("Tree.png"));
        add(jLabel1);
        
        Handlerclass handler = new Handlerclass();
        mousepanel.addMouseMotionListener(handler);
    }
    
     private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {                                     
          initialClick = evt.getPoint();
    } 
    
    
    
    

    private class Handlerclass implements MouseMotionListener {
        public void mouseDragged(MouseEvent evt){
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

        public void mouseMoved(MouseEvent event){
            System.out.println("You moved the mouse");
        }
    }
    
    public static void main(String[] args){
        Gui go = new Gui();
        go.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        go.setSize(1000, 1000);
        go.setVisible(true);
    }

}