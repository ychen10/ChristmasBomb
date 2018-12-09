import javafx.util.Pair;// for pair class

/**
 * Represents a Christmas tree icon displayed at the bottom of the screen
 *
 * @author Chloe Moon
 * @version 12/08/2018
 */
public class ChristmasTree
{
    // instance variables 
    Pair<Integer, Integer> location;
    // change this later accordingly
    final int width=10 ; final int height=10;
    
    // constructor
    public ChristmasTree()
    {
        // ********* change the values later
        location = new Pair<Integer, Integer>(0,0); 
    }
    
    //returns the location variable of the ChristmasTree object
    public Pair<Integer, Integer> getLocation(){
        return location; // .getKey() or .getValue()
    };
    
    //modifies the location variable of the ChristmasTree object 
    // based on the user input
    public void setLocation(int x, int y){
        this.location = new Pair<Integer, Integer>(x,y);
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
