package com.example.project4;

import com.sun.tools.javac.Main;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project4.*;

import java.io.IOException;
import java.text.DecimalFormat;

public class OrdersController {
    @FXML
    private Button goBackButton;
    @FXML
    private ComboBox<String> ordersBox;
    @FXML
    private Button removePizzaButton;
    @FXML
    private Button placeOrderButton;
    @FXML
    private TableView<String[]> pizzaTable;
    @FXML
    private TableColumn<String[], String> pizzaType;
    @FXML
    private TableColumn<String[], String> pizzaStyle;
    @FXML
    private TableColumn<String[], String> pizzaSize;
    @FXML
    private TableColumn<String[], String> pizzaCrust;
    @FXML
    private TableColumn<String[], String> pizzaToppings;
    @FXML
    private TableColumn<String[], String> pizzaSubtotal;
    @FXML
    private Label warnLabel;
    @FXML
    private Label warnLabel2;

    private Stage stage;
    private Scene scene;
    private Parent root;

    StoreOrders allPlacedOrders = MainViewController.allOrders;
    Order pizzasOrdered = MainViewController.pizzasOrdered;
    DecimalFormat df = new DecimalFormat("#,###.00");

    @FXML
    private void initialize(){
        warnLabel.setVisible(false);
        warnLabel2.setVisible(false);
        removePizzaButton.setDisable(true);
        ordersBox.getItems().clear();
        for (Pizza p : pizzasOrdered.getPizzas()) {
            ordersBox.getItems().add(p.toString());
        }
        pizzaType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        pizzaStyle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        pizzaSize.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
        pizzaCrust.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));
        pizzaToppings.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[4]));
        pizzaSubtotal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[5]));
    }

    @FXML
    private void onGoBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onOrdersBoxAction(){
        pizzaTable.getItems().clear();
        removePizzaButton.setDisable(false);
        int pizzaIdx = ordersBox.getSelectionModel().getSelectedIndex();
        if(pizzaIdx>=0){
            Pizza selectedPizza = pizzasOrdered.getPizzas().get(pizzaIdx);
            String[] pizzaData = new String[6];
            pizzaData[0] = selectedPizza.getType();
            pizzaData[1] = selectedPizza.getStyle();
            pizzaData[2] = selectedPizza.getSize().toString();
            pizzaData[3] = selectedPizza.getCrust().toString();
            pizzaData[4] = selectedPizza.toppingToString().replaceAll("Toppings: ", "");
            pizzaData[5] = df.format(selectedPizza.getSubTotal());
            pizzaTable.getItems().add(pizzaData);
        }
    }

    @FXML
    private void onPlaceOrderButtonClick(ActionEvent event) throws IOException {
        if(pizzasOrdered.getPizzas().isEmpty()){
            warnLabel.setText("No pizzas in order!");
            warnLabel.setTextFill(Color.RED);
            warnLabel.setVisible(true);
            return;
        }
        allPlacedOrders.getAllOrders().add(pizzasOrdered);
        pizzasOrdered = new Order();
        MainViewController.pizzasOrdered = pizzasOrdered;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onRemovePizzaButtonClick(){
        if(pizzasOrdered.getPizzas().isEmpty()){
            warnLabel2.setText("No pizzas in order!");
            warnLabel2.setTextFill(Color.RED);
            warnLabel2.setVisible(true);
            return;
        }
        int pizzaIdx = ordersBox.getSelectionModel().getSelectedIndex();
        pizzasOrdered.getPizzas().remove(pizzaIdx);
        ordersBox.getItems().clear();
        for(Pizza p: pizzasOrdered.getPizzas()){
            ordersBox.getItems().add(p.toString());
        }
        removePizzaButton.setDisable(true);
    }
}
