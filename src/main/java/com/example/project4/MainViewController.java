package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import project4.*;

import java.io.IOException;
import java.text.DecimalFormat;

public class MainViewController {
    public static Order pizzasOrdered = Order.getInstance();
    public static StoreOrders allOrders = StoreOrders.getInstance();

    DecimalFormat df = new DecimalFormat("#,###.00");

    private Stage stage;
    private Scene scene;
    private Parent root;

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
    @FXML private Button viewCurrOrderButton;
    @FXML private Button viewOrdersButton;


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
        displayTotal();
    }

    @FXML
    protected void onBuildYourOwnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("build-your-own-view.fxml"));
        root = loader.load();
        BuildYourOwnController buildYourOwnController = loader.getController();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onViewOrdersButtonClick(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("store-orders-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onViewCurrOrderButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("orders-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
    protected void buildPizza(){
        PizzaFactory pizzaFactory;
        Pizza pizza;

        if(styleBox.getValue().equals("New York")) {
            pizzaFactory = new NYPizza();
        } else {
            pizzaFactory = new ChicagoPizza();
        }

        if(typeBox.getValue().equalsIgnoreCase("Deluxe")) {
            pizza = pizzaFactory.createDeluxe();
        } else if (typeBox.getValue().equalsIgnoreCase("BBQ Chicken")) {
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

        pizzasOrdered.getPizzas().add(pizza);

        displayTotal();
    }

    protected void displayTotal(){
        double subtotal = pizzasOrdered.getSubTotal();
        double total = pizzasOrdered.getTotal();
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
