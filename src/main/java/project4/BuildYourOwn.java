package project4;

public class BuildYourOwn extends Pizza{
    private int numToppings;
    final static int MIN = 1;
    final static int MAX = 7;
    final static double EXTRA = 1.69;

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
