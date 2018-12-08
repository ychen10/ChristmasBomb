import javafoundations.*;
import java.util.*;
import javafx.util.Pair;// for pair class

/**
 * Implements the details for playing the game, using ChristmasTree
 * and Item class
 *
 * @author Chloe Moon
 * @version 12/08/2018
 */
public class Game
{
    // instance variables
    ChristmasTree tree;
    ArrayQueue<Item> dormant;  // stores the dorman Item objects
    Vector<Item> active; // add Item objects from "dormant" to this vector
    int score; 
    boolean isStart; // indicates whether the game has started or not
    int collision; 
    // change later *******
    final int screenWidth = 100; final int screenHeight=100;
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        // tree
        dormant = new ArrayQueue<Item>();
        active = new Vector<Item>();
        isStart = false;
        score=0;
        collision=0; 
    }

    /**
     * Checks if tree collides with gift or bomb and sets collision var to
     * the respective value (1 if collides with present, 0 if no collision,
     * -1 if collides with bomb)
     * 
     * 
     * @param dropped item, christmas tree
     */
    public void doCollide(Item drop){
        if(active.contains(drop)){
            // get location of the item
            Pair<Integer, Integer> itemloc = drop.getLocation();
            int itemX = itemloc.getKey();
            int itemY = itemloc.getValue();
            // get location of the tree 
            Pair<Integer, Integer> treeloc = tree.getLocation();
            int treeX = treeloc.getKey();
            int treeY = treeloc.getValue();
            // calculate the distance between the two objects
            int treeW = tree.getWidth();
            int treeH = tree.getHeight();
            int itemW = drop.getWidth();
            int itemH = drop.getHeight();
            // range
            double xRange = (treeW+itemW)/2.0;
            double yRange = (treeH+treeW)/2.0;
            
            // if within a certain range, they collide
            if(Math.abs(itemX-treeX)<=xRange && Math.abs(itemY-treeY)<=yRange){
                if(drop.isGift()){ // if it's a gift
                    // does setting the drop directly makes sense?
                    // change the itemCollided status of the item to true
                    active.get(active.indexOf(drop)).setItemCollided();
                    // update the game-wide indicator
                    collision=1;
                } else if(!drop.isGift()){ // if it's a bomb
                    // change the itemCollided status of the item to true
                    active.get(active.indexOf(drop)).setItemCollided();
                    collision=-1;
                }
            } else {
                collision=0;
            }
        } else { // if active vector (currently dropping) doesn't have this item
            System.out.println("Item not being dropped (not in 'Active')");
        }

    }
    
    /**
     * Ends the game if the score reches 1000 or if the score <0.
     */
    public void endGame(){
        if(score==1000|score<0){
            isStart=false;
        }
    }
    
    /**
     * Retrieve Item objects from the dormant queue and adds them to 
     * the active Vector if the size of the active vector is less than 6.
     */
    public void addItem(){
        if(active.size()<6){
            Item toAdd = dormant.dequeue(); // gets the item from the dormant queue
            active.add(toAdd);
        }
    }
    
    /**
     * Initialize Item objects to be added to the dormant Queue.
     * Using the Random class, the method will create gifts and bombs
     * in a 1:2 ratio.
     */
    public void prepareItem(){
        Item toAdd; 
        Random rand = new Random();
        int n = 1+ rand.nextInt(3); // gives 1+(0~2)
        int cwidth = tree.getWidth();
        // Random class to generate the x-location of the object
        // y-location: same as the height of the screen
        int x = rand.nextInt(screenWidth+1); //0~screenwidth
        int y = screenHeight; 
        if(n==3){ // when 3, add a present to the dormant queue
            toAdd = new Item(x,y,true); // is a gift
        }else{ // so if n=1 or 2
            toAdd = new Item(x,y,false); // is a bomb
        }
        dormant.enqueue(toAdd); // adds to the dormant queue
    }
    
    // updates the location variable of each item 
    // and check if anything collies with the tree
    // if collide: remove the item from the active vector
    // & updateScore()
    public void drop(){
        // iterate through the active vector
        for(int i=0; i<active.size(); i++){
            // 1) upate location
            // 2) checks if anything collides with the tree
            if(collision!=0){ // if collides
                // update score
                updateScore();
                // remove the item from the active vector
                active.remove(i);
            }
        }
    }
    
    public void updateScore(){
        if(collision==1){ // if collides with a present
            score=score+50;
        } else if(collision==-1){
            score=score-100;
        }
    }
}
