package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import project4.ChicagoPizza;
import project4.NYPizza;
import project4.Pizza;
import project4.PizzaFactory;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainViewController {
    ArrayList<Pizza> pizzas = new ArrayList<>();

    DecimalFormat df = new DecimalFormat("#,###.00");

    @FXML private ComboBox<String> typeBox;
    @FXML private ComboBox<String> styleBox;
    @FXML private ComboBox<String> sizeBox;

    @FXML private Button buildYourOwnButton;
    @FXML private Button buildPizzaButton;

    @FXML private Label sizeLabel;
    @FXML private Label subtotalLabel;
    @FXML private Label totalLabel;

    @FXML private ListView<String> toppingsListView;

    @FXML
    public void initialize() {
        typeBox.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza");
        styleBox.getItems().addAll("New York", "Chicago");
        sizeBox.getItems().addAll("Small", "Medium", "Large");
        sizeBox.setVisible(false);
        sizeLabel.setVisible(false);
        buildPizzaButton.setDisable(true);

        typeBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
        styleBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
        sizeBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
    }

    private void checkBoxSelections() {
        if (typeBox.getValue() != null && styleBox.getValue() != null) {
            sizeBox.setVisible(true);
            sizeLabel.setVisible(true);
            displayToppings();
        } else {
            sizeBox.setVisible(false);
            sizeLabel.setVisible(false);
        }

        if (sizeBox.getValue() != null) {
            buildPizzaButton.setDisable(false);
        } else {
            buildPizzaButton.setDisable(true);
        }
    }

    @FXML
    private void buildPizza(){
        PizzaFactory pizzaFactory;
        Pizza pizza;

        if(styleBox.getValue().equals("New York")) {
            pizzaFactory = new NYPizza();
        } else {
            pizzaFactory = new ChicagoPizza();
        }

        if(typeBox.getValue().equals("Deluxe")) {
            pizza = pizzaFactory.createDeluxe();
        } else if (typeBox.getValue().equals("BBQ Chicken")) {
            pizza = pizzaFactory.createBBQChicken();
        } else {
            pizza = pizzaFactory.createMeatzza();
        }

        if(sizeBox.getValue().equals("Small")) {
            pizza.setSize("Small");
        } else if(sizeBox.getValue().equals("Medium")) {
            pizza.setSize("Medium");
        } else {
            pizza.setSize("Large");
        }

        styleBox.setValue(null);
        typeBox.setValue(null);
        sizeBox.setValue(null);
        buildPizzaButton.setDisable(true);
        toppingsListView.getItems().clear();

        pizzas.add(pizza);

        displayTotal();
    }

    private void displayTotal(){
        double subtotal = 0;
        double total = 0;
        double taxFactor = 1.06625;
        for(int i = 0; i < pizzas.size(); i++){
            subtotal += pizzas.get(i).price();
        }
        total = subtotal * taxFactor;
        subtotalLabel.setText("$" + df.format(subtotal));
        totalLabel.setText("$" + df.format(total));
    }

    private void displayToppings(){
        toppingsListView.getItems().clear();
        if(typeBox.getValue().equals("Deluxe")) {
            toppingsListView.getItems().addAll("Sausage", "Pepperoni", "Green pepper", "Onion", "Mushroom");
        } else if (typeBox.getValue().equals("BBQ Chicken")) {
            toppingsListView.getItems().addAll("BBQ Chicken", "Green Pepper", "Provolone", "Cheddar");
        } else if (typeBox.getValue().equals("Meatzza")) {
            toppingsListView.getItems().addAll("Sausage", "Pepperoni", "Beef", "Ham");
        }
    }
}
