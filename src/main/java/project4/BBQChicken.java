package project4;

public class BBQChicken extends Pizza{

    BBQChicken(){
        addTopping(Topping.BBQCHICKEN);
        addTopping(Topping.GREENPEPPER);
        addTopping(Topping.PROVOLONE);
        addTopping(Topping.CHEDDAR);
    }

    @Override
    public String toString(){
        return super.toString();
    }

    public boolean add(Topping topping){
        return false;
    }

    public boolean remove(Topping topping){
        return false;
    }

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
