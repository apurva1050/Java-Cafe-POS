package com.example.project4;
import java.util.ArrayList;

/**
 * This class extends the MenuItem class and makes  object of Coffee
 * This class also overrides methods such as toString and itemPrice from MenuItem
 * */
public class Coffee extends MenuItem{

    private String cupSize;
    private ArrayList<String> addIns;


    /**
     * This is a constructor without any parameters
     * */
    public Coffee(){
        this.cupSize=null;
        this.addIns = null;
    }

    /**
     * This is a constructor with parameters
     * @param addIns the arraylist parameter
     * @param cupSize the cupSize parameter that sets the size
     * */
    public Coffee(String cupSize, ArrayList<String> addIns){
        this.cupSize=cupSize;
        this.addIns=addIns;
    }


    @Override
    /**
     * This method overrides the itemPrice method in MenuIterm
     * @return it returns a double
     * */
    public double itemPrice() {
        return 1.89;}


    /**
     * This method overrides the toString method
     * @return returns a string
     */
    @Override
    public String toString(){
        String a = "";
        for(String name: addIns){
            a = a + name + " ";
        }
        return cupSize + " ( "+ a + ")";
    }





}
