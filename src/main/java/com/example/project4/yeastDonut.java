package com.example.project4;


/**
 * This class extends the MenuItem class and makes objects of type yeastDonut
 * @author yehun kim, apurva desai*/
public class yeastDonut extends MenuItem{

    /**
     * This method overrides the itemPrice method in MenuIterm
     * @return it returns a double
     * */
    @Override
    public double itemPrice() {
        return 1.59;
    }


    /**
     * This method overrides the toString method
     * @return returns a string
     */
    @Override
    public String toString(){
        return "yeast donuts";
    }
}
