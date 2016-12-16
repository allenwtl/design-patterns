package com.allen.learn.v2;



public class Espress extends Beverage {

    public Espress() {
        setDescription("Espress");
    }

    @Override
    public double cost() {
        setWhep(true);
        return super.cost();
    }
}
