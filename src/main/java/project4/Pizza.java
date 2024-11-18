package project4;

import java.util.ArrayList;

public abstract class Pizza{
    private ArrayList<Topping> toppings = new ArrayList<>();
    private Crust crust;
    private Size size;
    private String type;
    public abstract double price();

    public void addTopping(Topping newTopping){
        this.toppings.add(newTopping);
    }

    public void removeTopping(Topping toRemove){
        this.toppings.remove(toRemove);
    }

    public void setType(String t){
        this.type = t;
    }

    public String getType(){
        return this.type;
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

    public double getSubTotal(){
        double price = this.price();
        return price;
    }

    public String toppingToString(){
        StringBuilder toppingStr = new StringBuilder();
        for(Topping topping : this.toppings){
            toppingStr.append(topping).append(", ");
        }
        toppingStr.replace(toppingStr.lastIndexOf(","), toppingStr.lastIndexOf(" "), "");
        return "Toppings: " + toppingStr;
    }

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
