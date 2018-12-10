
/**
 * Represents a Christmas tree icon displayed at the bottom of the screen
 *
 * @author Chloe Moon
 * @version 12/08/2018
 */
public class ChristmasTree
{
    // instance variables 
    private int x;
    private int y;
    // change this later accordingly
    final int width=10 ; final int height=10;
    
    // constructor
    public ChristmasTree()
    {
        // ********* change the values later
        this.x = 0;
        this.y = 0;
    }
    
    //returns the location variable of the ChristmasTree object
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    //modifies the location variable of the ChristmasTree object 
    // based on the user input
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
