package com.allen.learn.v1;


public class Decaf extends Beverage {

    public Decaf() {
        setDescription("Decaf");
    }

    @Override
    double cost() {
        return 0;
    }
}
