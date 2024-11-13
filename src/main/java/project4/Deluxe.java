package project4;

public class Deluxe extends Pizza{

    Deluxe(){
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.GREENPEPPER);
        addTopping(Topping.ONION);
        addTopping(Topping.MUSHROOM);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public double price(){
        double price = 0;
        Size size = getSize();
        switch(size){
            case SMALL:
                price = 16.99;
                break;
            case MEDIUM:
                price = 18.99;
                break;
            case LARGE:
                price = 20.99;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return price;
    }

    public boolean add(Topping topping){
        return false;
    }

    public boolean remove(Topping topping){
        return false;
    }

}
