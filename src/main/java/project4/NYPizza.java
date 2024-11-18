package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class NYPizza implements PizzaFactory{

    private Pizza pizza;

    /**
     * Method to create a Deluxe Pizza instance with appropriate configuration
     * @return A Deluxe Pizza instance with NY-style crust and type
     */
    @Override
    public Pizza createDeluxe(){
        pizza = new Deluxe();
        pizza.setCrust("BROOKLYN");
        pizza.setType("New York");
        return pizza;
    }

    /**
     * Method to create a BBQChicken Pizza instance with appropriate configuration
     * @return A BBQChicken Pizza instance with NY-style crust and type
     */
    @Override
    public Pizza createBBQChicken(){
        pizza = new BBQChicken();
        pizza.setCrust("THIN");
        pizza.setType("New York");
        return pizza;
    }

    /**
     * Method to create a Meatzza Pizza instance with appropriate configuration
     * @return A Meatzza Pizza instance with NY-style crust and type
     */
    @Override
    public Pizza createMeatzza(){
        pizza = new Meatzza();
        pizza.setCrust("HANDTOSSED");
        pizza.setType("New York");
        return pizza;
    }

    /**
     * Method to create a BuildYourOwn Pizza instance with appropriate configuration
     * @return A BuildYourOwn Pizza instance with NY-style crust and type
     */
    @Override
    public Pizza createBuildYourOwn(){
        pizza = new BuildYourOwn();
        pizza.setCrust("HANDTOSSED");
        pizza.setType("New York");
        return pizza;
    }

}
