package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class BBQChicken extends Pizza{

    /**
     * Constructor with appropriate Toppings for BBQChicken Pizza instance
     */
    BBQChicken(){
        addTopping(Topping.BBQCHICKEN);
        addTopping(Topping.GREENPEPPER);
        addTopping(Topping.PROVOLONE);
        addTopping(Topping.CHEDDAR);
    }

    /**
     * @return String conversion of the BBQChicken object
     */
    @Override
    public String toString(){
        return super.toString();
    }

    /**
     * @return Double price to two decimal places, depending on size of the pizza
     */
    @Override
    public double price(){
        double price = 0;
        Size size = getSize();
        switch(size){
            case SMALL:
                price = 14.99;
                break;
            case MEDIUM:
                price = 16.99;
                break;
            case LARGE:
                price = 19.99;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return price;
    }

}
