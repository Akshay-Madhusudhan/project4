package project4;

import java.util.ArrayList;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class StoreOrders {
    private ArrayList<Order> allOrders;
    private static StoreOrders instance;

    /**
     * A Constructor for building a StoreOrder instance with an initialized allOrders list
     */
    public StoreOrders(){
        allOrders = new ArrayList<>();
    }

    /**
     * @return An ArrayList of type Order, containing all orders belonging to the StoreOrders instance
     */
    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    /**
     * @return The instance of the StoreOrders this method is being called from
     */
    public static synchronized StoreOrders getInstance(){
        if(instance==null){
            instance = new StoreOrders();
        }
        return instance;
    }

}
