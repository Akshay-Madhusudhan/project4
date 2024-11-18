package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class BuildYourOwn extends Pizza{
    private int numToppings;
    final static int MIN = 1;
    final static int MAX = 7;
    final static double EXTRA = 1.69;

    /**
     * Adds a topping to the Build Your Own pizza
     * @param obj The Topping to add
     * @return True if the topping was successfully added, false otherwise
     */
    public boolean add(Object obj){
        if(!(obj instanceof Topping)){
            return false;
        }
        this.numToppings = getToppings().size();
        Topping newTopping = (Topping) obj;
        if(numToppings==MAX){
            return false;
        }
        this.addTopping(newTopping);
        this.numToppings++;
        return true;
    }

    /**
     * Removes a topping from the Build Your Own pizza
     * @param obj The Topping to remove
     * @return True if the topping was successfully removed, false otherwise
     */
    public boolean remove(Object obj){
        if(!(obj instanceof Topping)){
            return false;
        }
        this.numToppings = getToppings().size();
        if(numToppings<=MIN){
            return false;
        }
        Topping toRemove = (Topping) obj;
        this.removeTopping(toRemove);
        this.numToppings--;
        return true;
    }

    /**
     * @return Double price to two decimal places, depending on size of the pizza and number of toppings
     */
    @Override
    public double price(){
        double price = 0;
        Size size = getSize();
        switch(size){
            case SMALL:
                price = 8.99;
                break;
            case MEDIUM:
                price = 10.99;
                break;
            case LARGE:
                price = 12.99;
                break;
            default:
                throw new IllegalArgumentException();
        }
        if(numToppings>0){
            price += (numToppings*EXTRA);
        }
        return price;
    }

}
