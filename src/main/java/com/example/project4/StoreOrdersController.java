package com.example.project4;
import com.almasb.fxgl.entity.action.Action;
import javafx.beans.property.SimpleStringProperty;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class StoreOrdersController {
    @FXML
    private ComboBox<String> ordersBox;
    @FXML
    private Button exportOrdersButton;
    @FXML
    private Button cancelOrderButton;
    @FXML
    private Button goBackButton;
    @FXML
    private TableView<String[]> orderTable;
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
    private Label warningLabel;
    @FXML
    private Label warningLabel2;
    @FXML
    private Label orderBoxTotal;

    private Stage stage;
    private Scene scene;
    private Parent root;

    StoreOrders allOrders = MainViewController.allOrders;
    Order currOrder = MainViewController.pizzasOrdered;
    DecimalFormat df = new DecimalFormat("#,###.00");

    @FXML
    private void initialize(){
        cancelOrderButton.setDisable(true);
        warningLabel.setVisible(false);
        warningLabel2.setVisible(false);
        orderBoxTotal.setVisible(false);
        ordersBox.getItems().clear();
        for(Order o: allOrders.getAllOrders()){
            ordersBox.getItems().add("Order #" + o.getNumber());
        }
        pizzaType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        pizzaStyle.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        pizzaSize.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
        pizzaCrust.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[3]));
        pizzaToppings.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[4]));
        pizzaSubtotal.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[5]));
    }

    @FXML
    private void onOrdersBoxAction(){
        cancelOrderButton.setDisable(false);
        orderTable.getItems().clear();
        int orderIdx = ordersBox.getSelectionModel().getSelectedIndex();
        if(orderIdx>=0){
            Order selectedOrder = allOrders.getAllOrders().get(orderIdx);
            for(Pizza p : selectedOrder.getPizzas()){
                String[] pizzaData = new String[6];
                pizzaData[0] = p.getType();
                pizzaData[1] = p.getStyle();
                pizzaData[2] = p.getSize().toString();
                pizzaData[3] = p.getCrust().toString();
                pizzaData[4] = p.toppingToString().replaceAll("Toppings: ", "");
                pizzaData[5] = df.format(p.getSubTotal());
                orderTable.getItems().add(pizzaData);
            }
            orderBoxTotal.setText("Order #" + selectedOrder.getNumber() + " Total: $" + df.format(selectedOrder.getTotal()));
            orderBoxTotal.setVisible(true);
        }
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
    private void onCancelOrderButtonClick(){
        if(allOrders.getAllOrders().isEmpty()){
            warningLabel.setText("No orders in database!");
            warningLabel.setTextFill(Color.RED);
            warningLabel.setVisible(true);
            return;
        }
        int orderIdx = ordersBox.getSelectionModel().getSelectedIndex();
        allOrders.getAllOrders().remove(orderIdx);
        warningLabel.setText("Order Removed!");
        warningLabel.setTextFill(Color.GREEN);
        warningLabel.setVisible(true);
        ordersBox.getItems().clear();
        for(Order o: allOrders.getAllOrders()){
            ordersBox.getItems().add("Order #" + o.getNumber());
        }
        orderBoxTotal.setVisible(false);
        cancelOrderButton.setDisable(true);
    }

    @FXML
    private void onExportOrdersButtonClick(){
        if(allOrders.getAllOrders().isEmpty()){
            warningLabel2.setText("No orders to export!");
            warningLabel2.setTextFill(Color.RED);
            warningLabel2.setVisible(true);
            return;
        }
        String fp = "orders_export.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fp))){
            writer.write("All Orders\n");
            writer.write("-----------------------------------\n\n");
            for(Order o : allOrders.getAllOrders()){
                writer.write("*********Order #" + o.getNumber() + "*********\n");
                for(Pizza p : o.getPizzas()){
                    writer.write(p.toString() + "\n");
                }
                writer.write("Order Total: $" + df.format(o.getTotal()) + "\n");
                writer.write("*********end of order*********\n\n");
            }
            warningLabel2.setText("Orders Exported and Cleared!");
            warningLabel2.setTextFill(Color.GREEN);
            warningLabel2.setVisible(true);
            allOrders = new StoreOrders();
            MainViewController.allOrders = allOrders;
            ordersBox.getItems().clear();
            for(Order o: allOrders.getAllOrders()){
                ordersBox.getItems().add("Order #" + o.getNumber());
            }
            orderBoxTotal.setVisible(false);
            cancelOrderButton.setDisable(true);
        } catch (IOException e){
            warningLabel2.setText("Error exporting orders: " + e.getMessage());
            warningLabel2.setTextFill(Color.RED);
            warningLabel2.setVisible(true);
        }
    }
}
