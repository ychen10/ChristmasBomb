

/**
 * Represents the objects being dropped (either present or bomb)
 *
 * @author Chloe Moon
 * @version 12/08/2018
 */
public class Item
{
    // instance variables
    boolean typeGift; // true: gift, false: bomb
    int x;
    int y;
    final int width = 10 ; final int height = 10; // can change later if desired
    boolean itemCollided; 

    /**
     * Constructor for objects of class Item
     */
    public Item(int x, int y, boolean isGift)
    {
        // initialise instance variables
        typeGift = isGift; 
        this.x = x;
        this.y = y;
        itemCollided = false; 
    }

    /**
     * Returns the x location of the item
     * 
     * @return x location (int)
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y location of the item
     * 
     * @return y location (int)
     */
    public int getY() {
        return y;
    }
    
    /**
     * Sets the x location given the input
     * 
     * @param desired x coordinate for the item
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Sets the y location given the input
     * 
     * @param desired y coordinate for the item
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Sets the itemCollided variable to true when the Item object
     * collides with the tree
     */
    public void setItemCollided(){
        itemCollided = true;
    } 
    
    /**
     * Returns the width of the item
     * 
     * @return width (int) of the Item
     */
    public int getWidth(){
        return width;
    }
    
    /**
     * Returns the hegiht of the item
     * 
     * @return height (int) of the Item
     */
    public int getHeight(){
        return height;
    }
    
    /**
     * Return whether the Item is a gift or not
     * 
     * @return true iff the Item is a gift, false if it's a bomb
     */
    public boolean isGift(){
        return typeGift;
    }
}
