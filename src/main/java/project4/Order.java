package project4;

import java.util.ArrayList;

public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;
    private static Order instance;
    public static final double TAX = 1.06625;
    private static int pizzaNumber = 0;

    public Order(){
        this.number = ++pizzaNumber;
        this.pizzas = new ArrayList<>();
    }

    public int getNumber(){
        return this.number;
    }

    public static Order getInstance(){
        if(instance==null){
            instance = new Order();
        }
        return instance;
    }

    public ArrayList<Pizza> getPizzas(){
        return this.pizzas;
    }

    public Pizza findPizza(Pizza pizza){
        for(Pizza p : pizzas){
            if(p.equals(pizza)){
                return p;
            }
        }
        return null;
    }

    public double getSubTotal(){
        double price = 0;
        for(Pizza p : pizzas){
            price += p.price();
        }
        return price;
    }

    public double getTotal(){
        return getSubTotal()*TAX;
    }

}
