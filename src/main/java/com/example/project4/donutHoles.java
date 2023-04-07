package com.example.project4;


/**
 * This class extends the donutHoles class and makes objects of type donutHoles
 * @author yehun kim, apurva desai*/
public class donutHoles extends MenuItem{

    /**
     * This method overrides the itemPrice method in MenuIterm
     * @return it returns a double
     * */
    @Override
    public double itemPrice() {
        return 0.39;
    }


    @Override
    /**
     * This method overrides the toString method
     * @return returns a string
     */
    public String toString(){
        return "donut holes";
    }



}


