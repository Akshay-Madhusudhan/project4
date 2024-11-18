package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project4.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public class BuildYourOwnController {
    @FXML
    private ComboBox<String> type;
    @FXML
    private ComboBox<String> size;
    @FXML
    private CheckBox BBQchicken;
    @FXML
    private CheckBox Beef;
    @FXML
    private CheckBox Cheddar;
    @FXML
    private CheckBox GreenPepper;
    @FXML
    private CheckBox Ham;
    @FXML
    private CheckBox Mushroom;
    @FXML
    private CheckBox Olives;
    @FXML
    private CheckBox Onion;
    @FXML
    private CheckBox Pepperoni;
    @FXML
    private CheckBox Pineapple;
    @FXML
    private CheckBox Provolone;
    @FXML
    private CheckBox Sausage;
    @FXML
    private CheckBox Spinach;
    @FXML
    private Button addToppingButton;
    @FXML
    private Button removeToppingButton;
    @FXML
    private Button buildPizzaButton;
    @FXML
    private Button goBackButton;
    @FXML
    private ListView<Topping> currToppings = new ListView<>();
    @FXML
    private Label warningLabel;

    private ObservableList<Topping> toppings = FXCollections.observableArrayList();
    private ArrayList<CheckBox> chkBoxes = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Called on controller startup, sets all view elements to starting configuration
     */
    @FXML
    protected void initialize(){
        toppings.addListener((ListChangeListener<Topping>)change->updateBuildPizzaButton(buildPizzaButton));
        chkBoxes.addAll(Arrays.asList(BBQchicken, Beef, Cheddar, GreenPepper, Ham, Mushroom, Olives, Onion, Pepperoni, Pineapple, Provolone, Sausage, Spinach));
        size.setDisable(true);
        addToppingButton.setDisable(true);
        removeToppingButton.setDisable(true);
        for(CheckBox chk : chkBoxes){
            chk.setDisable(true);
        }
        warningLabel.setVisible(false);
        initializeType();
        initializeSize();
        updateBuildPizzaButton(buildPizzaButton);
        currToppings.setItems(toppings);
    }

    /**
     * @param button The button to be updated
     * Method to update the "build pizza" button when an incorrect number of toppings is selected
     */
    private void updateBuildPizzaButton(Button button){
        if(toppings.isEmpty() || toppings.size()>7){
            button.setDisable(true);
        } else {
            button.setDisable(false);
        }
    }

    /**
     * Method to initialize the type ComboBox
     */
    @FXML
    protected void initializeType(){
        type.getItems().add("Chicago Pizza");
        type.getItems().add("NY Pizza");
    }

    /**
     * Method to re-enable the size ComboBox when an option from the type ComboBox is selected
     */
    @FXML
    protected void onTypeAction(){
        size.setDisable(false);
    }

    /**
     * Method to enable the toppings CheckBoxes when an option from the size ComboBox is selected
     */
    @FXML
    protected void onSizeAction(){
        for(CheckBox chk : chkBoxes){
            chk.setDisable(false);
        }
        addToppingButton.setDisable(false);
        removeToppingButton.setDisable(false);
    }

    /**
     * Method to add size options to the size ComboBox
     */
    @FXML
    protected void initializeSize(){
        for(Size s : Size.values()){
            size.getItems().add(s.toString());
        }
    }

    /**
     * Method to add toppings to pizza on corresponding button click
     */
    @FXML
    protected void onAddToppingButtonClick(){
        int count = 0;
        for(CheckBox chk : chkBoxes){
            if(chk.isSelected() && !toppings.contains(Topping.valueOf(chk.getId().toUpperCase()))){
                count++;
            }
        }
        if(count>7 || count<1 || (count+toppings.size())>7){
            warningLabel.setText("Max toppings allowed is 7, and must add at least 1.");
            warningLabel.setTextFill(Color.RED);
            warningLabel.setVisible(true);
        } else {
            for(CheckBox chk : chkBoxes){
                if(chk.isSelected()){
                    if(toppings.contains(Topping.valueOf(chk.getId().toUpperCase()))){
                        continue;
                    }
                    toppings.add(Topping.valueOf(chk.getId().toUpperCase()));
                }
            }
            warningLabel.setText("Toppings Added!");
            warningLabel.setTextFill(Color.GREEN);
            warningLabel.setVisible(true);
        }
    }

    /**
     * Method to remove toppings from pizza on corresponding button click
     */
    @FXML
    protected void onRemoveToppingButtonClick(){
        int count = 0;
        for(CheckBox chk : chkBoxes){
            if(chk.isSelected()){
                count++;
            }
        }
        if(toppings.isEmpty() || count>toppings.size()){
            warningLabel.setText("No toppings to remove or attempting to remove more toppings than available.");
            warningLabel.setTextFill(Color.RED);
            warningLabel.setVisible(true);
        } else {
            for(CheckBox chk : chkBoxes){
                if(chk.isSelected()){
                    toppings.remove(Topping.valueOf(chk.getId().toUpperCase()));
                }
            }
            warningLabel.setText("Toppings Removed!");
            warningLabel.setTextFill(Color.GREEN);
            warningLabel.setVisible(true);
        }
    }

    /**
     * @param event The event that triggered this method
     * Method to build pizza and add to current order's pizza list on corresponding button click
     */
    @FXML
    protected void onBuildPizzaButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        MainViewController mainViewController = loader.getController();
        PizzaFactory pizzaFactory;
        Pizza pizza;
        if(type.getValue().equalsIgnoreCase("Chicago Pizza")){
            pizzaFactory = new ChicagoPizza();
        } else {
            pizzaFactory = new NYPizza();
        }
        pizza = pizzaFactory.createBuildYourOwn();
        if(size.getValue().equalsIgnoreCase("SMALL")){
            pizza.setSize("SMALL");
        } else if(size.getValue().equalsIgnoreCase("MEDIUM")){
            pizza.setSize("MEDIUM");
        } else {
            pizza.setSize("LARGE");
        }
        for(Topping t : toppings){
            ((BuildYourOwn)pizza).add(t);
        }

        MainViewController.pizzasOrdered.getPizzas().add(pizza);

        type.setValue(null);
        size.setValue(null);
        buildPizzaButton.setDisable(true);
        toppings.clear();
        currToppings.getItems().clear();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        mainViewController.displayTotal();
        stage.show();
    }

    /**
     * @param event The event that triggered this method
     * Method to switch back to the main view
     */
    @FXML
    protected void onGoBackButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
