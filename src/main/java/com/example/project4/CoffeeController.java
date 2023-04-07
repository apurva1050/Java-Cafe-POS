package com.example.project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * This is a controller class for the OrderingCoffee.fxml file.
 * It makes coffee objects and contains Arraylists that contain their values
 * @author apurva desai., yehun kim
 * */
public class CoffeeController {
    private ObservableList<Integer> numberList;
    private ObservableList<String> sizeList;
    @FXML
    private ComboBox<Integer> quantityCoffee;
    @FXML
    private ComboBox<String> sizeSelect;
    @FXML
    private CheckBox sweetCream;
    @FXML
    private CheckBox frenchVanilla;
    @FXML
    private CheckBox irishCream;
    @FXML
    private CheckBox caramel;
    @FXML
    private CheckBox mocha;
    @FXML
    private TextField subTotal;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private ArrayList<MenuItem> menuItems = new ArrayList<>();
    private MainController mainController = new MainController();


    /**
     * This is the initialize method that is executed every time the coffee controller is invoked in any other classes
     * */
    public void initialize(){
        numberList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
        quantityCoffee.setItems(numberList);
        sizeList = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti");
        sizeSelect.setItems(sizeList);

    }

    /**
     * this method sets the Maincontroller to mainController
     * @param controller the controller parameter passed
     * */
    public void setMainController(MainController controller){
        mainController = controller;
    }

    /**
     * This method sets all the addIns checkboxes as false,
     * It is a helper method used in some method like checkErrors below
     * */
    private void setFalse(){
        sweetCream.setSelected(false);
        irishCream.setSelected(false);
        mocha.setSelected(false);
        caramel.setSelected(false);
        frenchVanilla.setSelected(false);
    }




    /**
     * This method checks for any errors when addinf order to the cart
     * @return boolean it returns a boolean false if there are any errors, true otherwise
     * */
    private boolean checkErrors(){
        if(!sizeSelect.getSelectionModel().isEmpty() && !quantityCoffee.getSelectionModel().isEmpty()){
            return true;
        }
        if(sizeSelect.getSelectionModel().isEmpty() && quantityCoffee.getSelectionModel().isEmpty()){
            setFalse();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("select a size and the quantity");
            error.showAndWait();
            return false;
        }
        if(sizeSelect.getSelectionModel().isEmpty() && !quantityCoffee.getSelectionModel().isEmpty()){
            setFalse();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("select a size");
            error.showAndWait();
            return false;
        }
        if(!sizeSelect.getSelectionModel().isEmpty() && quantityCoffee.getSelectionModel().isEmpty()){
            setFalse();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("select the quantity.");
            error.showAndWait();
            return false;
        }
        return false;
    }


    /**
     * this method displays the total amount of the order when the addIns checkboxes are selected
     * @param event the event that occurs after clicking on the checkbox
     * */
    @FXML
    void displayAmount(ActionEvent event){
        subTotal.setText(df.format(amt()));
    }


    /**
     * This method calculates the price of the donuts and returns the price as a double
     * @return double it returns a double which is the price of the donuts
     * */
     private double amt(){
        if(checkErrors()) {
            double total = 0;
            Coffee c = new Coffee();
            int a = quantityCoffee.getSelectionModel().getSelectedItem();
            if (sizeSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("short")) {
                total = c.itemPrice() * a;
            }
            if (sizeSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("tall")) {
                total = (c.itemPrice() + 0.40) * a;
            }
            if (sizeSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("grande")) {
                total = (c.itemPrice() + 0.80) * a;
            }
            if (sizeSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("venti")) {
                total = (c.itemPrice() + 1.20) * a;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            if (sweetCream.isSelected()) {
                total = total + 0.30;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            if (frenchVanilla.isSelected()) {
                total = total + 0.30;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            if (irishCream.isSelected()) {
                total = total + 0.30;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            if (caramel.isSelected()) {
                total = total + 0.30;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            if (mocha.isSelected()) {
                total = total + 0.30;
            }
            subTotal.setText(String.valueOf(df.format(total)));
            return total;
        }
        return 0;
    }


    /**
     Adds a new coffee order to the shopping cart.
     adds it to the menuItems ArrayList and the mainController's orderList.
     The method also updates the mainController's list and moreList with the new order and the total amount, respectively.
     @param event the event that triggers the method
     */
    @FXML
    void addToCart(ActionEvent event){
        if(checkErrors()){
            //mainController.getOrder().setOrderNumber(1);
            int a = quantityCoffee.getSelectionModel().getSelectedItem();
            ArrayList<String> addIns = new ArrayList<>();
            if(frenchVanilla.isSelected()){
                addIns.add("french Vanilla");
            }
            if(sweetCream.isSelected()){
                addIns.add("sweet cream");
            }
            if(irishCream.isSelected()){
                addIns.add("irish cream");
            }
            if(caramel.isSelected()){
                addIns.add("caramel");
            }
            if(mocha.isSelected()){
                addIns.add("mocha");
            }
            for(int i = 0; i < a; i++){
                Coffee c = new Coffee(sizeSelect.getSelectionModel().getSelectedItem(), addIns);
                menuItems.add(c);
                mainController.getOrder().getOrderList().add(c);
            }
            Coffee c = new Coffee(sizeSelect.getSelectionModel().getSelectedItem(), addIns);
            mainController.getList().add( "Coffee" + " ("+quantityCoffee.getSelectionModel().getSelectedItem() + ") " + c.toString() );
            double b = Double.parseDouble(df.format(amt()));
            mainController.getMoreList().add(b);
        }
    }

}
