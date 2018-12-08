// A program to simulate balls falling under the influence of
//   gravity.
//
// Notice the method breakdown of the program, and in particular
//   the use of return values.
import java.awt.*;

public class testAnimation {
   public static final double GRAVITY = 9.81;      // m / s^2

   public static void main(String[] args) {
      DrawingPanel panel = new DrawingPanel(600, 1000);
      panel.setBackground(Color.WHITE);
      Graphics g = panel.getGraphics();
      
      int initX1 = 200;
      int initY1 = 10;
      int initV1 = 24;
      
      int initX2 = 400;
      int initY2 = 10;
      int initV2 = 48;
      
      double oldDelta1 = 0;
      double oldDelta2 = 0;
      for (double t = 0; t < 10; t += 0.05) {
         oldDelta1 = updateBall(initX1, initY1, initV1, t, oldDelta1, Color.RED, g);
         oldDelta2 = updateBall(initX2, initY2, initV2, t, oldDelta2, Color.YELLOW, g);
         panel.sleep(50);
      }
   }
   
   // Updates the position of a falling ball based on the elapsed time by
   //   "erasing" the previous location of the ball and drawing the new location.
   //
   // int initX - the initial x-position of the ball
   // int initY - the initial y-position of the ball
   // int initV - the initial downward velocity of the ball
   // double time - the time elapsed since the simulation began, in seconds
   // double oldDelta - the previous tick's displacement
   // Color color - the color of the ball
   // Graphics g - the Graphics object to use for drawing
   public static double updateBall(int initX, int initY, int initV, double time, 
                                   double oldDelta, Color color, Graphics g) {
      double delta = displacement(initV, GRAVITY, time);
      double velo = velocity(initV, GRAVITY, time);   
      clearBall(initX, initY + (int)oldDelta, g);
      drawBall(initX, initY + (int)delta, velo, color, g);
      return delta;
   }

   // "Erases" a ball, replacing it with a white circle and drawing over
   //   the associated velocity tag with a rectangle the same color as
   //   the background.
   //
   // int x - the x-position of the ball to erase
   // int y - the y-position of the ball to erase
   // Graphics g - the Graphics object to use for drawing
   public static void clearBall(int x, int y, Graphics g) {
      g.setColor(Color.WHITE);
      g.fillOval(x, y, 20, 20);
      g.setColor(Color.WHITE);
      g.fillRect(x + 30, y - 10, 65, 10);
   }
   
   // Draws a ball and prints a tag with the current velocity.
   //
   // int x - the x-position of the ball to erase
   // int y - the y-position of the ball to erase
   // double velo - the current velocity of the ball
   // Color color - the color of the ball
   // Graphics g - the Graphics object to use for drawing   
   public static void drawBall(int x, int y, double velo, Color color, Graphics g) {
      g.setColor(color);
      g.fillOval(x, y, 20, 20);
      g.setColor(Color.BLACK);
      g.drawString("v = " + roundN(velo, 4), x + 30, y);
   }
   
   // Calculates the displacement of an object at a given time.
   //
   // double v0 - the object's initial velocity
   // double a - the object's acceleration
   // double t - the time elapsed since motion began
   public static double displacement(double v0, double a, double t) {
      return v0 * t + 0.5 * a * t * t;
   }
   
   // Calculates the velocity of an object at a given time.
   //
   // double v0 - the object's initial velocity
   // double a - the object's acceleration
   // double t - the time elapsed since motion began   
   public static double velocity(double v0, double a, double t) {
      return v0 + 0.5 * a * t;
   }
   
   // Rounds a number to a certain number of decimal places
   //
   // double num - the number to round
   // int places - the number of places after the decimal point
   //              to retain
   public static double roundN(double num, int places) {
      return Math.round(num * Math.pow(10, places)) / Math.pow(10, places);
   }
}