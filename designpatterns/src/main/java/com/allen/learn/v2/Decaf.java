package com.allen.learn.v2;



public class Decaf extends Beverage {

    public Decaf() {
        setDescription("Decaf");
    }

    @Override
    public double cost() {
        setMocha(true);
        return super.cost();
    }
}
