package com.example.project4;

/**
 * This class extends the MenuItem class and makes objects of type cakeDonuts
 * @author yehun kim, apurva desai*/
public class cakeDonut extends MenuItem{


    @Override
    /**
     * This methods overrides the itemPrice method in MenuItem
     * @returns a double
     * */
    public double itemPrice() {
        return 1.79;
    }

    /**
     * This method overrides the toString method
     * @return returns a string
     */
    @Override
    public String toString(){
        return "cake donuts";
    }
}
