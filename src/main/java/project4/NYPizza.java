package project4;

public class NYPizza implements PizzaFactory{

    private Pizza pizza;

    @Override
    public Pizza createDeluxe(){
        pizza = new Deluxe();
        pizza.setCrust("BROOKLYN");
        pizza.setType("New York");
        return pizza;
    }

    @Override
    public Pizza createBBQChicken(){
        pizza = new BBQChicken();
        pizza.setCrust("THIN");
        pizza.setType("New York");
        return pizza;
    }

    @Override
    public Pizza createMeatzza(){
        pizza = new Meatzza();
        pizza.setCrust("HANDTOSSED");
        pizza.setType("New York");
        return pizza;
    }

    @Override
    public Pizza createBuildYourOwn(){
        pizza = new BuildYourOwn();
        pizza.setCrust("HANDTOSSED");
        pizza.setType("New York");
        return pizza;
    }

}
