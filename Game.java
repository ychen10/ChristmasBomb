import javafoundations.*;
import java.util.*;


/**
 * Implements the details for playing the game, using ChristmasTree
 * and Item class
 *
 * @author Chloe Moon
 * @version 12/08/2018
 */
public class Game {
    // instance variables
    private ChristmasTree tree;
    private ArrayQueue<Item> dormant;  // stores the dorman Item objects
    private LinkedList<Item> active; // add Item objects from "dormant" to this linkedlist
    private int score; 
    private boolean isStart; // indicates whether the game has started or not
    private Boolean win;
    // change later *******    
    /**
     * Constructor for objects of class Game
     */
    public Game(ChristmasTree tree) {
        // tree
        dormant = new ArrayQueue<Item>();
        active = new LinkedList<Item>();
        isStart = false;
        score = 0;
        this.tree = tree;
        win = null;
    }

    // if it doesn't work maybe put a tree as a parameter
    /**
     * Checks if tree collides with gift or bomb and sets collision var to
     * the respective value (1 if collides with present, 0 if no collision,
     * -1 if collides with bomb)
     * 
     * 
     * @param dropped item, christmas tree
     */
    public void doCollide(Item drop){
        if (active.contains(drop)) {
            // get location of the item
            int itemX = drop.getX() + 36;// actual dropping object
            int itemY = drop.getY() + 36;
            // get location of the tree
            int treeX = tree.getX();
            // int treeY = tree.getY();
            int starX = treeX + 160; // 25+36
            int starY = 475;
            double distance = Math.sqrt(Math.pow(itemX - starX, 2) + Math.pow(itemY - starY, 2));
            if (distance < 50) { // do collide
                if (drop.isGift()) {
                    //active.get(active.indexOf(drop)).setItemCollided();
                    // update the game-wide indicator
                    score = score + 50; // update score
                    active.remove(drop);
                    //System.out.println("COLLIDED WITH A GIFT");
                } else { // if it's a bomb
                    // change the itemCollided status of the item to true
                    //active.get(active.indexOf(drop)).setItemCollided();
                    score = score - 100; // update score
                    active.remove(drop);
                    //System.out.println("COLLIDED WITH A BOMB");
                }
            }
        }
    }
    
    
    /**
     * Ends the game if the score reches 1000 or if the score <0.
     */
    public void endGame(){
        isStart = false;
    }
    
    /**
     * Retrieve Item objects from the dormant queue and adds them to 
     * the active linkedlist if the size of the active linkedlist is less than 6.
     */
    public void addItem(){
        Random rand = new Random();
        if (active.size() < 5){
            if (dormant.isEmpty()) prepareItem();
            if (rand.nextInt(6) == 0) {
                Item toAdd = dormant.dequeue(); // gets the item from the dormant queue
                active.add(toAdd);
            }
        }
    }
    
    /**
     * Initialize Item objects to be added to the dormant Queue.
     * Using the Random class, the method will create gifts and bombs
     * in a 1:1 ratio.
     */
    public void prepareItem() {
        Item toAdd; 
        Random rand = new Random();
        int n = rand.nextInt(2); 
        // Random class to generate the x-location of the object
        // y-location: same as the height of the screen
        int x = rand.nextInt(450) - 5; //0~screenwidth
        // int x = 50; 
        int y = -60;
        //int y = screenHeight; 
        if (n == 0){ // when 3, add a present to the dormant queue
            toAdd = new Item(x,y,true); // is a gift
        } else { // so if n=1 or 2
            toAdd = new Item(x,y,false); // is a bomb
        }
        dormant.enqueue(toAdd); // adds to the dormant queue
    }
    

    public int getScore(){
        return score;
    }

    public LinkedList<Item> getActive() {
        return this.active;
    }

    public void drop() {
        Random rand = new Random();
        if (rand.nextInt(7) == 0) addItem();

        if (this.isStart) {
            for (int i = 0; i < active.size(); i ++) {
                Item item = active.get(i);
                item.setY(item.getY() + 5);
                if (item.getY() > 740) active.remove(item);
                doCollide(item);
            }
        }
    }

    public void start() {
        this.isStart = true;
        for (int i = 0; i < 6; i ++) addItem();
        drop();
    }

    public ChristmasTree getTree() {
        return this.tree;
    }

    public boolean win() {
        return (score >= 1000);
    }

    public boolean lose() {
        return score < 0 || (win != null && win.equals(false));
    }

    public void setLose() {
        win = false;
        isStart = false;
    }

    public boolean didEnd() {
        return !isStart;
    }
    
}
