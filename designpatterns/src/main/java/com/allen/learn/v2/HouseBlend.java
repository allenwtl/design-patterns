package com.allen.learn.v2;



public class HouseBlend extends Beverage {
    public HouseBlend() {
        setDescription("HouseBlend");
    }

    @Override
    public double cost() {
        setMilk(true);
        return super.cost();
    }
}
