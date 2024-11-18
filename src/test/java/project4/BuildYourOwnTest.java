package project4;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {

    @Test
    void addTest() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        PizzaFactory pizzaFactory2 = new NYPizza();
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        Pizza pizza2 = pizzaFactory2.createBuildYourOwn();
        Crust dummy = Crust.THIN;
        assertFalse(((BuildYourOwn)pizza).add(dummy));
        assertTrue(((BuildYourOwn)pizza).add(Topping.BBQCHICKEN));
        assertFalse(((BuildYourOwn)pizza2).add(dummy));
        assertTrue(((BuildYourOwn)pizza2).add(Topping.BBQCHICKEN));
    }

    @Test
    void removeTest() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        Crust dummy = Crust.THIN;
        assertFalse(((BuildYourOwn)pizza).remove(Topping.BBQCHICKEN));
        ((BuildYourOwn)pizza).add(Topping.BBQCHICKEN);
        assertFalse(((BuildYourOwn)pizza).remove(Topping.BBQCHICKEN));
        ((BuildYourOwn)pizza).add(Topping.CHEDDAR);
        assertTrue(((BuildYourOwn)pizza).remove(Topping.BBQCHICKEN));
        assertFalse(((BuildYourOwn) pizza).remove(dummy));
    }

    @Test
    void priceTest() {
        PizzaFactory pizzaFactory = new ChicagoPizza();
        PizzaFactory pizzaFactory2 = new NYPizza();
        Pizza pizza = pizzaFactory.createBuildYourOwn();
        Pizza pizza2 = pizzaFactory2.createBuildYourOwn();
        Pizza pizza3 = pizzaFactory.createBuildYourOwn();
        pizza.setSize("small");
        pizza2.setSize("medium");
        pizza3.setSize("large");
        assertEquals(8.99, pizza.price());
        assertEquals(10.99, pizza2.price());
        assertEquals(12.99, pizza3.price());
        ((BuildYourOwn)pizza).add(Topping.BBQCHICKEN);
        ((BuildYourOwn)pizza).add(Topping.CHEDDAR);
        assertEquals(12.37, Math.floor(pizza.price()*100)/100);
        ((BuildYourOwn)pizza).remove(Topping.CHEDDAR);
        assertEquals(10.68, Math.floor(pizza.price()*100)/100);
    }
}