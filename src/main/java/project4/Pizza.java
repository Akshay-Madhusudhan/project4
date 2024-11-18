package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
import java.util.ArrayList;

public abstract class Pizza{
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Size size;
    private String type;
    public abstract double price();

    /**
     * Adds a Topping to the pizza
     * @param newTopping The Topping object to be added
     */
    public void addTopping(Topping newTopping){
        this.toppings.add(newTopping);
    }

    /**
     * Removes a Topping from the pizza
     * @param toRemove The Topping object to be removed
     */
    public void removeTopping(Topping toRemove){
        this.toppings.remove(toRemove);
    }

    /**
     * Sets the type of the pizza
     * @param t A String containing the type to set the pizza to
     */
    public void setType(String t){
        this.type = t;
    }

    /**
     * @return A String containing the Pizza's type
     */
    public String getType(){
        return this.type;
    }

    /**
     * Sets the crust of the pizza
     * @param crust A String containing the crust to set the pizza to
     */
    public void setCrust(String crust){
        this.crust = Crust.valueOf(crust.toUpperCase());
    }

    /**
     * @return A Crust object containing the Pizza's crust
     */
    public Crust getCrust(){
        return this.crust;
    }

    /**
     * Sets the size of the pizza
     * @param size A String containing the size to set the pizza to
     */
    public void setSize(String size){
        this.size = Size.valueOf(size.toUpperCase());
    }

    /**
     * @return A Size object containing the Pizza's size
     */
    public Size getSize(){
        return this.size;
    }

    /**
     * @return An ArrayList of type Topping, containing all Toppings belonging to the Pizza
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * @return A double price, containing the price of the Pizza
     */
    public double getSubTotal(){
        double price = this.price();
        return price;
    }

    /**
     * @return A String conversion of the Pizza's Topping list
     */
    public String toppingToString(){
        StringBuilder toppingStr = new StringBuilder();
        for(Topping topping : this.toppings){
            toppingStr.append(topping).append(", ");
        }
        toppingStr.replace(toppingStr.lastIndexOf(","), toppingStr.lastIndexOf(" "), "");
        return "Toppings: " + toppingStr;
    }

    /**
     * @return A String conversion of the Pizza's style
     */
    public String getStyle(){
        if(this instanceof BBQChicken){
            return "BBQChicken";
        } else if(this instanceof BuildYourOwn){
            return "BuildYourOwn Pizza";
        } else if(this instanceof Deluxe){
            return "Deluxe Pizza";
        } else if(this instanceof Meatzza){
            return "Meatzza Pizza";
        }
        return null;
    }

    /**
     * @return A String conversion of the Pizza object
     */
    @Override
    public String toString(){
        if(this instanceof BBQChicken){
            return "BBQChicken Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else if(this instanceof BuildYourOwn){
            return "BuildYourOwn Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else if(this instanceof Deluxe){
            return "Deluxe Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        } else if(this instanceof Meatzza){
            return "Meatzza Pizza: " + this.size + ", " + this.crust + ", " + toppingToString();
        }
        return null;
    }


}
