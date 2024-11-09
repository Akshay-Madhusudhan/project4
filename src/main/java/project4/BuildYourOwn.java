package project4;

public class BuildYourOwn extends Pizza{
    private int numToppings;
    private int additionalToppings;
    final static int MIN = 1;
    final static int MAX = 7;
    final static double EXTRA = 1.69;

    public boolean add(Object obj){
        this.numToppings = getToppings().size();
        Topping newTopping = (Topping) obj;
        if(numToppings==MAX){
            return false;
        }
        this.addTopping(newTopping);
        this.additionalToppings++;
        return true;
    }

    public boolean remove(Object obj){
        this.numToppings = getToppings().size();
        if(numToppings==MIN){
            return false;
        }
        Topping toRemove = (Topping) obj;
        this.removeTopping(toRemove);
        this.additionalToppings--;
        return true;
    }

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
        if(additionalToppings>0){
            price += (additionalToppings*EXTRA);
        }
        return price;
    }

}
