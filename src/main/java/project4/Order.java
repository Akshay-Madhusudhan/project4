package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
import java.util.ArrayList;

public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;
    private static Order instance;
    public static final double TAX = 1.06625;
    private static int pizzaNumber = 0;

    /**
     * A constructor for building Order instances, automatically tracks increasing order number
     */
    public Order(){
        this.number = ++pizzaNumber;
        this.pizzas = new ArrayList<>();
    }

    /**
     * @return int order number
     */
    public int getNumber(){
        return this.number;
    }

    /**
     * @return The instance of the order this method is being called from
     */
    public static synchronized Order getInstance(){
        if(instance==null){
            instance = new Order();
        }
        return instance;
    }

    /**
     * @return ArrayList of type Pizza, the list of Pizzas in this Order
     */
    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    /**
     * Finds a Pizza object in the list of Pizzas and returns it
     * @param pizza The Pizza object to find
     * @return The Pizza object if found, null otherwise
     */
    public Pizza findPizza(Pizza pizza){
        for(Pizza p : pizzas){
            if(p.equals(pizza)){
                return p;
            }
        }
        return null;
    }

    /**
     * @return Double price, which is the sum of all prices of pizzas in the Pizza list
     */
    public double getSubTotal(){
        double price = 0;
        for(Pizza p : pizzas){
            price += p.price();
        }
        return price;
    }

    /**
     * @return double getSubTotal()*TAX, where TAX is the predefined tax rate
     */
    public double getTotal(){
        return getSubTotal()*TAX;
    }

}
