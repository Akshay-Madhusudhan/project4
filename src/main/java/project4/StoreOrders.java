package project4;

import java.util.ArrayList;

public class StoreOrders {
    private ArrayList<Order> allOrders;
    private static StoreOrders instance;

    public StoreOrders(){
        allOrders = new ArrayList<>();
    }

    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    public static synchronized StoreOrders getInstance(){
        if(instance==null){
            instance = new StoreOrders();
        }
        return instance;
    }

}
