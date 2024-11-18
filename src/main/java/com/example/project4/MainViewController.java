package com.example.project4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    @FXML private ImageView pizzaImageView;

    @FXML private ListView<String> toppingsListView;

    @FXML
    public void initialize() {
        typeBox.getItems().addAll("Deluxe", "BBQ Chicken", "Meatzza");
        styleBox.getItems().addAll("New York", "Chicago");
        sizeBox.getItems().addAll("Small", "Medium", "Large");
        sizeBox.setVisible(false);
        sizeLabel.setVisible(false);
        buildPizzaButton.setDisable(true);

        Image pizzaImage = new Image(getClass().getResource("/images/deluxechi.jpg").toString(), true);
        pizzaImageView.setImage(pizzaImage);
        pizzaImageView.setVisible(false);

        typeBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
        styleBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
        sizeBox.valueProperty().addListener((obs, oldVal, newVal) -> checkBoxSelections());
    }

    private void checkBoxSelections() {
        if (typeBox.getValue() != null && styleBox.getValue() != null) {
            sizeBox.setVisible(true);
            sizeLabel.setVisible(true);
            displayToppings();
            displayPizzaImage();
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
        pizzaImageView.setImage(null);

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

    private void displayPizzaImage(){
        if(typeBox.getValue().equals("Deluxe")) {
            if(styleBox.getValue().equals("New York")) {
                Image pizzaImage = new Image(getClass().getResource("/images/deluxeny.jpg").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
            else if (styleBox.getValue().equals("Chicago")) {
                Image pizzaImage = new Image(getClass().getResource("/images/deluxechi.jpg").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
        }
        else if(typeBox.getValue().equals("BBQ Chicken")) {
            if(styleBox.getValue().equals("New York")) {
                Image pizzaImage = new Image(getClass().getResource("/images/bbqchickenny.png").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
            else if (styleBox.getValue().equals("Chicago")) {
                Image pizzaImage = new Image(getClass().getResource("/images/bbqchickenchi.jpg").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
        }
        else if(typeBox.getValue().equals("Meatzza")) {
            if(styleBox.getValue().equals("New York")) {
                Image pizzaImage = new Image(getClass().getResource("/images/meatzzany.jpg").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
            else if (styleBox.getValue().equals("Chicago")) {
                Image pizzaImage = new Image(getClass().getResource("/images/meatzzachi.jpg").toString(), true);
                pizzaImageView.setImage(pizzaImage);
                pizzaImageView.setVisible(true);
            }
        }
    }
}
