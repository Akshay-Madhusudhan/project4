package project4;

import java.util.ArrayList;

public abstract class Pizza{
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Size size;
    public abstract double price();

    public void preSetToppings(String pizzaType){
        if(pizzaType.equalsIgnoreCase("BBQChicken")){
            this.toppings.add(Topping.BBQCHICKEN);
            this.toppings.add(Topping.CHEDDAR);
            this.toppings.add(Topping.GREENPEPPER);
            this.toppings.add(Topping.PROVOLONE);
        } else if(pizzaType.equalsIgnoreCase("Deluxe")){
            this.toppings.add(Topping.GREENPEPPER);
            this.toppings.add(Topping.MUSHROOM);
            this.toppings.add(Topping.ONION);
            this.toppings.add(Topping.PEPPERONI);
            this.toppings.add(Topping.SAUSAGE);
        } else if(pizzaType.equalsIgnoreCase("Meatzza")){
            this.toppings.add(Topping.BEEF);
            this.toppings.add(Topping.HAM);
            this.toppings.add(Topping.PEPPERONI);
            this.toppings.add(Topping.SAUSAGE);
        }
    }

    public void addTopping(Topping newTopping){
        this.toppings.add(newTopping);
    }

    public void removeTopping(Topping toRemove){
        this.toppings.remove(toRemove);
    }

    public void setCrust(String crust){
        this.crust = Crust.valueOf(crust.toUpperCase());
    }

    public Crust getCrust(){
        return this.crust;
    }

    public void setSize(String size){
        this.size = Size.valueOf(size.toUpperCase());
    }

    public Size getSize(){
        return this.size;
    }

    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    public String toppingToString(){
        String toppingStr = "";
        for(Topping topping : this.toppings){
            toppingStr = toppingStr + "|" + topping;
        }
        return "Topping: " + toppingStr;
    }

    @Override
    public String toString(){
        if(this instanceof BBQChicken){
            return "BBQChicken Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else if(this instanceof BuildYourOwn){
            return "BuildYourOwn Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else if(this instanceof Deluxe){
            return "Deluxe Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else {
            return "Meatzza Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        }
    }


}
