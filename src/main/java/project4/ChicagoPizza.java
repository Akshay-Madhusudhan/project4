package project4;

public class ChicagoPizza implements PizzaFactory{

    private Pizza pizza;

    @Override
    public Pizza createDeluxe(){
        pizza = new Deluxe();
        pizza.setCrust("DEEPDISH");
        pizza.setType("Chicago");
        return pizza;
    }

    @Override
    public Pizza createBBQChicken(){
        pizza = new BBQChicken();
        pizza.setCrust("PAN");
        pizza.setType("Chicago");
        return pizza;
    }

    @Override
    public Pizza createMeatzza(){
        pizza = new Meatzza();
        pizza.setCrust("STUFFED");
        pizza.setType("Chicago");
        return pizza;
    }

    @Override
    public Pizza createBuildYourOwn(){
        pizza = new BuildYourOwn();
        pizza.setCrust("PAN");
        pizza.setType("Chicago");
        return pizza;
    }

}
