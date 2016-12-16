package com.allen.learn.v1;


public class HouseBlend extends Beverage {
    public HouseBlend() {
        setDescription("HouseBlend");
    }

    @Override
    double cost() {
        return 1;
    }
}
