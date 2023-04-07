package com.example.project4;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.text.DecimalFormat;
import java.util.ArrayList;



/**
 * This is the controller class for the StoreOrderview.fxml file.
 * It contains methods to export orders into a file and cancel orders.
 * */
public class StoreOrderController {
    @FXML
    private TextField totalPrice;
    private static final double SALES_TAX = 6.625;
    MainController mainController = new MainController();
    private static final DecimalFormat df = new DecimalFormat("0.00");
    @FXML
    private ListView<String> orderList;
    private ObservableList<String> base;
    @FXML
    private ComboBox<Integer> select;
    ObservableList<Integer> smallList;

    /**
     * This method sets the mainController
     * @param controller the controller used as parameter
     * */
    public void setMainController(MainController controller){
        mainController = controller;
    }

    /**
     * This is the initialize method that runs every time the StoreOrderController is invoked.
     * @param list the Arraylist that is used to set store orders
     * */
    public void initialize(ArrayList<Integer> list){
        smallList = FXCollections.observableArrayList();
        for(Integer a : list){
            smallList.add( a);
        }
        select.setItems(smallList);
    }


    /**
     * This method displays the orders placed in the listview.
     * @param ace the arraylist that contains the orders
     * @param cost the arraylist that contains cost of the orders
     * */
    public void newDisplay(ArrayList<String> ace, ArrayList<Double> cost){
            base = FXCollections.observableArrayList();
            if (ace.size() != 0) {
                for (String a : ace) {
                    base.add(a);
                }
                double total = 0;
                for (double num : cost) {
                    total = total + num;
                }
                double tax = ((total * SALES_TAX) / 100);
                double u = tax + total;
                totalPrice.setText(String.valueOf(Double.parseDouble(df.format(u))));
                orderList.setItems(base);
            }
    }


    /**
     * this method is executed every time the export button is clicked.
     * this method exports the order into an external file
     * @param event the event that occurs every time the button is clicked
     * */
    @FXML
    void export(ActionEvent event) {
        if (!orderList.getItems().isEmpty()) {
            mainController.internalExport();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("nothing to export to file.");
            alert.showAndWait();
        }
    }

    /**
     * This method cancels an order and removes it from StoreOrderController
     * @param event the event that occurs when the cancel order button is clicked
     * */
    @FXML
    void cancelOrder(ActionEvent event){
        if(!orderList.getItems().isEmpty()){
            totalPrice.setText("");
            base.clear();
            orderList.setItems(base);
            mainController.storeClear();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("no order to cancel.");
            alert.showAndWait();
        }

    }

}
