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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import project4.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
    private ListView<Topping> currToppings = new ListView<>();

    private ObservableList<Topping> toppings = FXCollections.observableArrayList();
    private ArrayList<CheckBox> chkBoxes = new ArrayList<>();

    private Stage stage;
    private Scene scene;
    private Parent root;


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
        initializeType();
        initializeSize();
        updateBuildPizzaButton(buildPizzaButton);
        currToppings.setItems(toppings);
    }

    private void updateBuildPizzaButton(Button button){
        if(toppings.isEmpty() || toppings.size()>7){
            button.setDisable(true);
        } else {
            button.setDisable(false);
        }
    }

    @FXML
    protected void initializeType(){
        type.getItems().add("Chicago Pizza");
        type.getItems().add("NY Pizza");
    }

    @FXML
    protected void onTypeAction(){
        size.setDisable(false);
    }

    @FXML
    protected void onSizeAction(){
        for(CheckBox chk : chkBoxes){
            chk.setDisable(false);
        }
        addToppingButton.setDisable(false);
        removeToppingButton.setDisable(false);
    }

    @FXML
    protected void initializeSize(){
        for(Size s : Size.values()){
            size.getItems().add(s.toString());
        }
    }

    @FXML
    protected void onAddToppingButtonClick(){
        int count = 0;
        for(CheckBox chk : chkBoxes){
            if(chk.isSelected() && !toppings.contains(Topping.valueOf(chk.getId().toUpperCase()))){
                count++;
            }
        }
        if(count>7 || count<1 || (count+toppings.size())>7){
            System.out.println("Min is 1 and Max is 7");
        } else {
            for(CheckBox chk : chkBoxes){
                if(chk.isSelected()){
                    if(toppings.contains(Topping.valueOf(chk.getId().toUpperCase()))){
                        continue;
                    }
                    toppings.add(Topping.valueOf(chk.getId().toUpperCase()));
                }
            }
        }
    }

    @FXML
    protected void onRemoveToppingButtonClick(){
        int count = 0;
        for(CheckBox chk : chkBoxes){
            if(chk.isSelected()){
                count++;
            }
        }
        if(toppings.isEmpty() || count>toppings.size()){
            System.out.println("Selected check boxes are greater than # of toppings.");
        } else {
            for(CheckBox chk : chkBoxes){
                if(chk.isSelected()){
                    toppings.remove(Topping.valueOf(chk.getId().toUpperCase()));
                }
            }
        }
    }

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

        mainViewController.pizzasOrdered.getPizzas().add(pizza);

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
}
