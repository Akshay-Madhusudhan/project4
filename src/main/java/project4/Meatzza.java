package project4;

public class Meatzza extends Pizza{

    Meatzza(){
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.BEEF);
        addTopping(Topping.HAM);
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
                price = 17.99;
                break;
            case MEDIUM:
                price = 19.99;
                break;
            case LARGE:
                price = 21.99;
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
