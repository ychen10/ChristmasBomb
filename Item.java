import javafx.util.Pair;// for pair class


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
    // change later
    final int width = 10 ; final int height = 10;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void setItemCollided(){
        itemCollided = true;
    } 
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    
    // true for gift, false for bomb 
    public boolean isGift(){
        return typeGift;
    }
}
