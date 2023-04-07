package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This class represents a controller for the donut shop. It allows the user to select and purchase different types of donuts,
 view the selected donuts, and calculate the total cost of their order.
 @author Yehun Kim , Apurva Desai

 */
public class DonutController {
    @FXML
    private ComboBox<String> donutSelect;
    @FXML
    private ComboBox<Integer> quantity;
    @FXML
    private ListView<String> flavorList;
    @FXML
    private ListView<String> finalList;  // has quantity and which donuts
    private ObservableList<String> colorList;
    private ObservableList<String> yeastList;
    private ObservableList<String> cakeList;
    private ObservableList<String> donutHoleList;
    private ObservableList<Integer> numberList;
    private ObservableList<String> containsQuantityDonut;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    private TextField subTotal;
    private MainController mainController = new MainController();
    @FXML
    private ImageView donutImage;
    ArrayList<yeastDonut> listOfYeastDonuts = new ArrayList<>();
    ArrayList<cakeDonut> listOfCakeDonuts = new ArrayList<>();
    ArrayList<donutHoles> listOfDonutHoles = new ArrayList<>();


    /**
     Sets the main controller for this controller.
     @param controller the main controller for this controller
     */
    public void setMainController(MainController controller) {
        mainController = controller;
    }

    /**
     Initializes the UI components of this controller.
     */
    public void initialize(){
        colorList = FXCollections.observableArrayList("Yeast Donuts", "Cake Donuts", "Donut Holes");
        donutSelect.setItems(colorList);
        yeastList =  FXCollections.observableArrayList("glazed Donuts" , "frosted Donuts", "rainbow Donuts", "chocolate Donuts");
        cakeList =  FXCollections.observableArrayList("powdered Donuts" , "apple sliced", "rainbow sprinkles", "chocolate covered");
        donutHoleList = FXCollections.observableArrayList("churro Donut" , "Nutella Donut", "peanut butter", "chocolate glazed");
        numberList = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6);
        quantity.setItems(numberList);
        containsQuantityDonut = FXCollections.observableArrayList();

    }

    /**
     Displays the selected type of donut and its corresponding flavors.
     @param event the event triggered by clicking the corresponding button
     */
    @FXML
    public void displayDonuts(ActionEvent event){
        if(donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Yeast Donuts")){
            File file = new File("yeastDonut.jpg");
            Image image1 = new Image(file.toURI().toString());
            donutImage.setImage(image1);
            flavorList.setItems(yeastList);
        }
        if(donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Cake Donuts")){
            File file = new File("cakeDonut.jpg");
            Image image1 = new Image(file.toURI().toString());
            donutImage.setImage(image1);
            flavorList.setItems(cakeList);
        }
        if(donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Donut Holes")){
            File file = new File("donutHole.jpg");
            Image image1 = new Image(file.toURI().toString());
            donutImage.setImage(image1);
            flavorList.setItems(donutHoleList);
        }

    }


    /**
     Calculates the total amount of the order based on the number of items and their respective prices.
     @return the total amount of the order
     */
    private double calculateAmount(){
        int a = listOfYeastDonuts.size();
        int b = listOfDonutHoles.size();
        int c  = listOfCakeDonuts.size();
        yeastDonut y = new yeastDonut();
        cakeDonut u = new cakeDonut();
        donutHoles h = new donutHoles();

        double amount = (a*y.itemPrice()) + (b*h.itemPrice()) + (c*u.itemPrice());
        return amount;
    }

    /**
     Calculates the price of the yeast donuts based on the number of yeast donuts selected.
     @return the price of the yeast donuts
     */
    private double yeastCalculate(){
        int a = listOfYeastDonuts.size();
        yeastDonut y = new yeastDonut();
        return a*y.itemPrice();
    }

    /**
     Calculates the price of the cake donuts based on the number of cake donuts selected.
     @return the price of the cake donuts
     */
    private double cakeCalculate(){
        int a = listOfCakeDonuts.size();
        cakeDonut c = new cakeDonut();
        return a*c.itemPrice();
    }

    /**
     Calculates the price of the donut holes based on the number of donut holes selected.
     @return the price of the donut holes
     */
    private double donutCalculate(){
        int a = listOfDonutHoles.size();
        donutHoles d = new donutHoles();
        return a*d.itemPrice();
    }

    private void loop1(){
        int b = quantity.getSelectionModel().getSelectedItem();
        for (int i = 0; i < b; i++) {
            yeastDonut z = new yeastDonut();
            listOfYeastDonuts.add(z);
            mainController.getOrder().getOrderList().add(z);
        }
    }

    private void loop2(){
        int b = quantity.getSelectionModel().getSelectedItem();
        for (int i = 0; i < b; i++) {
            cakeDonut v = new cakeDonut();
            listOfCakeDonuts.add(v);
            mainController.getOrder().getOrderList().add(v);

        }
    }

    private void loop3(){
        int b = quantity.getSelectionModel().getSelectedItem();
        for (int i = 0; i < b; i++) {
            donutHoles x = new donutHoles();
            listOfDonutHoles.add(x);
            mainController.getOrder().getOrderList().add(x);

        }
    }

    private void getAlert(){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle("ERROR!");
        error.setContentText("select a different type of donut.");
        error.showAndWait();
    }


    /**
     Adds the selected donut to the order, with the specified quantity and flavor.
     Updates the GUI with the added item and the updated subtotal.
     Displays an error message if necessary fields are not filled.
     @param event the event triggered by clicking on the "Add" button
     */
    @FXML
    void add(ActionEvent event){
        if(!donutSelect.getSelectionModel().isEmpty()) {
            if (!quantity.getSelectionModel().isEmpty()) {
                if (donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Yeast Donuts")) {
                    if (!flavorList.getSelectionModel().isEmpty()) {
                        int b = quantity.getSelectionModel().getSelectedItem();loop1();
                        mainController.getMoreList().add(yeastCalculate());
                        int a = flavorList.getSelectionModel().getSelectedIndex();
                        String c = flavorList.getSelectionModel().getSelectedItem();
                        yeastList.remove(a);
                        flavorList.setItems(yeastList);
                        containsQuantityDonut.add(b + " " + c);
                        finalList.setItems(containsQuantityDonut);
                    } else {getAlert();}
                    subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}
                if (donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Cake Donuts")) {
                    if (!flavorList.getSelectionModel().isEmpty()) {
                        int b = quantity.getSelectionModel().getSelectedItem();loop2();
                        mainController.getMoreList().add(cakeCalculate());
                        int a = flavorList.getSelectionModel().getSelectedIndex();
                        String c = flavorList.getSelectionModel().getSelectedItem();
                        cakeList.remove(a);
                        flavorList.setItems(cakeList);
                        containsQuantityDonut.add(b + " " + c);
                        finalList.setItems(containsQuantityDonut);
                    } else {getAlert();}
                    subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}
                if (donutSelect.getSelectionModel().getSelectedItem().equalsIgnoreCase("Donut Holes")) {
                    if (!flavorList.getSelectionModel().isEmpty()) {
                        int b = quantity.getSelectionModel().getSelectedItem();loop3();
                        mainController.getMoreList().add(donutCalculate());
                        int a = flavorList.getSelectionModel().getSelectedIndex();
                        String c = flavorList.getSelectionModel().getSelectedItem();
                        donutHoleList.remove(a);
                        flavorList.setItems(donutHoleList);
                        containsQuantityDonut.add(b + " " + c);
                        finalList.setItems(containsQuantityDonut);
                    } else {getAlert();}
                    subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("ERROR!");
                error.setContentText("select a quantity.");
                error.showAndWait();}
        }else{getAlert();}}


    /**
     Removes a selected donut from the list and updates the subtotal and flavor list.
     @param event the action event triggered by clicking the "Remove" button
     */
    @FXML
    void remove(ActionEvent event){
        if(!finalList.getSelectionModel().isEmpty()){
            String a = finalList.getSelectionModel().getSelectedItem();
            String[] b = a.split(" ");
            String temp = b[1] + " " +b[2];
            int num = Integer.parseInt(b[0]);
            subTotal.setText(temp);
            if(temp.equalsIgnoreCase("glazed Donuts") || temp.equalsIgnoreCase("frosted Donuts") || temp.equalsIgnoreCase("rainbow Donuts") || temp.equalsIgnoreCase("chocolate Donuts")  ){
                for(int i = 0; i < num; i++){
                    listOfYeastDonuts.remove(listOfYeastDonuts.size()-1);
                    listOfYeastDonuts.trimToSize();}
                int index1 = mainController.getMoreList().indexOf(yeastCalculate());
                mainController.getMoreList().remove(index1);
                mainController.getMoreList().add(index1, yeastCalculate());
                containsQuantityDonut.remove(a);
                yeastList.add(b[1] + " " + b[2]);
                flavorList.setItems(yeastList);
                subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}
            if(temp.equalsIgnoreCase("powdered Donuts") || temp.equalsIgnoreCase("apple sliced") || temp.equalsIgnoreCase("rainbow sprinkles") || temp.equalsIgnoreCase("chocolate covered")  ){
                for(int i = 0; i < num; i++){
                    listOfCakeDonuts.remove(listOfCakeDonuts.size()-1);
                    listOfCakeDonuts.trimToSize();}
                int index2 = mainController.getMoreList().indexOf(cakeCalculate());
                mainController.getMoreList().remove(index2);
                mainController.getMoreList().add(index2, cakeCalculate());
                containsQuantityDonut.remove(a);
                cakeList.add(b[1] + " " + b[2]);
                flavorList.setItems(cakeList);
                subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}
            if(temp.equalsIgnoreCase("churro Donut") || temp.equalsIgnoreCase("Nutella Donut") || temp.equalsIgnoreCase("peanut butter") || temp.equalsIgnoreCase("chocolate glazed")  ){
                for(int i = 0; i < num; i++){
                    listOfDonutHoles.remove(listOfDonutHoles.size()-1);
                    listOfDonutHoles.trimToSize();}
                int index3 = mainController.getMoreList().indexOf(donutCalculate());
                mainController.getMoreList().remove(index3);
                mainController.getMoreList().add(index3, donutCalculate());
                containsQuantityDonut.remove(a);
                donutHoleList.add(b[1] + " " + b[2]);
                flavorList.setItems(donutHoleList);
                subTotal.setText(String.valueOf(Double.parseDouble(df.format(calculateAmount()))));}}else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("no donuts to remove from cart!");
            error.showAndWait();}}


    /**
     This method is called when the "Add to Order" button is clicked.
     It checks if there are any items in the finalList to add to the cart.
     If so, it creates instances of the different types of donuts and loops
     through the list of containsQuantityDonut to add each item to the mainController's list.
     If there are no items in the finalList, an error message is displayed.
     @param event the action event executed by clicking the "Add to Order" button
     */
    @FXML
    void addToOrder(ActionEvent event){
        if(!finalList.getItems().isEmpty()){
            yeastDonut y = new yeastDonut();
            donutHoles d = new donutHoles();
            cakeDonut c = new cakeDonut();
            for (String name: containsQuantityDonut){
                mainController.getList().add(name);
            }
        }else{
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("ERROR!");
            error.setContentText("nothing to add to the cart.");
            error.showAndWait();
        }
    }

}
