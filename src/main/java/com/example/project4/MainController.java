package com.example.project4;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a controller class for the main-view fxml file.
 * It initializes different arraylists and orders that contain information which are used in all the different controllers.
 * @author apurva desai, yehun kim
 */
public class MainController {
    private ArrayList<String> items = new ArrayList<>();
    private ArrayList<String> storeItems = new ArrayList<>();
    private ArrayList<Double> costOfCoffee = new ArrayList<>();
    private ArrayList<Double> storeCost = new ArrayList<>();
    private ArrayList<Integer> listForOrders = new ArrayList<>();

    /**
     * This method returns the array list that contains the order numbers
     * @return an Arraylist with order numbers.
     * */
    public ArrayList getListForOrders(){return listForOrders;}

    /**
     * This method copies all the elements from arraylists items and costOfCoffee into storeItems and storeCost
     * */
    public void copy(){
        for(String name : items){
            storeItems.add(name);
        }
        for(double num : costOfCoffee){
            storeCost.add(num);
        }
    }

    /**
     * This is a getter method for the items Arraylist which contains menuitems
     * @return  returns an Arraylist called items
     * */
    public ArrayList getList(){
        return items;
    }

    /**
     * This is a getter method for the costOfCoffee Arraylist
     * @return Arraylist returns and Arraylist
     * */
    public ArrayList getMoreList(){return costOfCoffee;}
    Order order = new Order(1, new ArrayList<MenuItem>());

    /**
     * This is a getter method that returns the current order
     * @return order, the current order
     * */
    public Order getOrder(){
        return order;
    }

    /**
     * This is an actionevent that is executed every time, the image of order coffee is selected
     * @param event the event that occurs when the image is clicked
     * */
    @FXML
    protected void switchToCoffeePage(ActionEvent event) {
        Stage view1 = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingCoffeeView.fxml"));
            root = (VBox) loader.load();
            Scene scene = new Scene(root, 600, 600);
            view1.setScene(scene);
            view1.show();
            CoffeeController coffeeController = loader.getController();
            coffeeController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingCoffee.fxml.");
            alert.setContentText("Couldn't load OrderingCoffee.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * This is an actionevent that is executed every time, the image of order donuts is selected
     * @param event the event that occurs when the image is clicked
     * */
    @FXML
    protected void switchToDonutPage(ActionEvent event) {
        Stage view1 = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingDonutsView.fxml"));
            root = (VBox) loader.load();
            Scene scene = new Scene(root, 600, 600);
            view1.setScene(scene);
            view1.show();
            DonutController donutController = loader.getController();
            donutController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingDonuts.fxml.");
            alert.setContentText("Couldn't load OrderingDonuts.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * This is an actionevent that is executed every time, the image of your order is selected
     * @param event the event that occurs when the image is clicked
     * */
    @FXML
    protected void switchToBasketPage(ActionEvent event) {
        Stage view1 = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderingBasketView.fxml"));
            root = (VBox) loader.load();
            Scene scene = new Scene(root, 600, 600);
            view1.setScene(scene);
            view1.show();
            BasketController basketController = loader.getController();
            basketController.displayName(items, costOfCoffee);
            basketController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading OrderingBasket.fxml.");
            alert.setContentText("Couldn't load OrderingBasket.fxml.");
            alert.showAndWait();
        }
    }

    /**
     * This is an actionevent that is executed every time, the image of store orders is selected
     * @param event the event that occurs when the image is clicked
     * */
    @FXML
    protected void switchToStoreOrderPage(ActionEvent event) {
        Stage view1 = new Stage();
        VBox root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("StoreOrderView.fxml"));
            root = (VBox) loader.load();
            Scene scene = new Scene(root, 600, 600);
            view1.setScene(scene);
            view1.show();
            StoreOrderController storeOrderController = loader.getController();
            storeOrderController.initialize(listForOrders);
            storeOrderController.newDisplay(storeItems, storeCost);
            storeOrderController.setMainController(this);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading StoreOrder.fxml.");
            alert.setContentText("Couldn't load StoreOrder.fxml.");
            alert.showAndWait();
        }
    }


    /**
     * This method clears the used Arraylists every time an order is confirmed
     * */
    public void clear(){
        items.clear();
        items.trimToSize();
        costOfCoffee.clear();
        costOfCoffee.trimToSize();
    }

    /**
     * This method clears the storeCost and storeItems arraylist after an order is cancelled from Store Orders
     * */
    public void storeClear(){
        storeCost.clear();
        storeCost.trimToSize();
        storeItems.clear();
        storeItems.trimToSize();
    }

    /**
     * This method computes the total of all the menuitems selected
     * */
    public double getTotal(){
        double a = 0;
        for(double num : costOfCoffee){
            a = a + num;
        }
        return a;
    }

    /**
     * This is a helper method for the original export method in StoreOrderController
     * It writes all the current menuitems that have been ordered into an external file called file.txt
     * */
    public void internalExport() {
        File file = new File("file.txt");
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int i = 0; i < storeItems.size(); i++){
                bufferedWriter.write(storeItems.get(i) + " Cost: " + storeCost.get(i));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("Something went wrong");
            error.showAndWait();
        }
        storeItems.clear();
        storeItems.trimToSize();
        storeCost.clear();
        storeCost.trimToSize();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("order exported");
        alert.setContentText("list moved to file.txt");
        alert.showAndWait();
    }
}