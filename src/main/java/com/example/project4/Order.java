package com.example.project4;

import java.util.ArrayList;

/**
 * This is the order class whose objects have two parameters: and orderNumber and an Arraylist containing the Menuitems ordered
 * @author yehun kim, apurva desai
 * */
public class Order {
    private int orderNumber;
    private ArrayList<MenuItem> orderList;

    /**
     * This methods sets the orderNumber
     * */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * This method gets the orderNumber of an order object
     * @return an integer which is the ordernumber
     * */
    public int getOrderNumber(){return orderNumber;}

    /**
     * This is a constructor with two parameters
     * @param orderList the arraylist containing menuitems
     * @param orderNumber the ordernumber*/
    public Order(int orderNumber, ArrayList<MenuItem> orderList){
        this.orderNumber = orderNumber;
        this.orderList = orderList;
    }


    /**
     * This method returns the arraylist containing the menuitems of an order
     * @return an arraylist
     * */
    public ArrayList<MenuItem> getOrderList() {
        return orderList;
    }


}
