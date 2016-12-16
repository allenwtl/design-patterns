package com.allen.learn.v1;


public class Espress extends Beverage{

    public Espress() {
        setDescription("Espress");
    }

    @Override
    double cost() {
        return 3;
    }
}
