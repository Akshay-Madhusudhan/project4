package project4;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class ChicagoPizza implements PizzaFactory{

    private Pizza pizza;

    /**
     * Method to create a Deluxe Pizza instance with appropriate configuration
     * @return A Deluxe Pizza instance with Chicago-style crust and type
     */
    @Override
    public Pizza createDeluxe(){
        pizza = new Deluxe();
        pizza.setCrust("DEEPDISH");
        pizza.setType("Chicago");
        return pizza;
    }

    /**
     * Method to create a BBQChicken Pizza instance with appropriate configuration
     * @return A BBQChicken Pizza instance with Chicago-style crust and type
     */
    @Override
    public Pizza createBBQChicken(){
        pizza = new BBQChicken();
        pizza.setCrust("PAN");
        pizza.setType("Chicago");
        return pizza;
    }

    /**
     * Method to create a Meatzza Pizza instance with appropriate configuration
     * @return A Meatzza Pizza instance with Chicago-style crust and type
     */
    @Override
    public Pizza createMeatzza(){
        pizza = new Meatzza();
        pizza.setCrust("STUFFED");
        pizza.setType("Chicago");
        return pizza;
    }

    /**
     * Method to create a BuildYourOwn Pizza instance with appropriate configuration
     * @return A BuildYourOwn Pizza instance with Chicago-style crust and type
     */
    @Override
    public Pizza createBuildYourOwn(){
        pizza = new BuildYourOwn();
        pizza.setCrust("PAN");
        pizza.setType("Chicago");
        return pizza;
    }

}
