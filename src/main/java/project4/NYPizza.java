package project4;

public class NYPizza implements PizzaFactory{

    private Pizza pizza;

    @Override
    public Pizza createDeluxe(){
        pizza = new Deluxe();
        pizza.setCrust("BROOKLYN");
        return pizza;
    }

    @Override
    public Pizza createBBQChicken(){
        pizza = new BBQChicken();
        pizza.setCrust("THIN");
        return pizza;
    }

    @Override
    public Pizza createMeatzza(){
        pizza = new Meatzza();
        pizza.setCrust("HANDTOSSED");
        return pizza;
    }

    @Override
    public Pizza createBuildYourOwn(){
        pizza = new BuildYourOwn();
        pizza.setCrust("HANDTOSSED");
        return pizza;
    }

}
