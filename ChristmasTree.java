
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
    
    /**
     * Constructor of the ChristmasTree class
     */
    public ChristmasTree()
    {
        // (0,0) by default
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * Returns the x-coordinate of the tree object
     * 
     * @return x coordinate of the tree
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Returns the y-coordinate of the tree object
     * 
     * @return y coordinate of the tree
     */
    public int getY(){
        return this.y;
    }

    /**
     * Modifies the location of the ChristmasTree object 
     * based on the input
     * 
     * @param desired x, y coordinate
     */
    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Returns the width of the ChristmasTree object
     * 
     * @return width of the ChristmasTree object
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Returns the height of the ChristmasTree object
     * 
     * @return height of the ChristmasTree object
     */
    public int getHeight(){
        return height;
    }
}
