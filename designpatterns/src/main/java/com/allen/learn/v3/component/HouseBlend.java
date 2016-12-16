package com.allen.learn.v3.component;


import com.allen.learn.v3.Beverage;

/**
 * 这个是具体的饮料
 */
public class HouseBlend extends Beverage{

    public HouseBlend() {
        setDescription("HouseBlend");
    }

    @Override
    public double cost() {
        return 2;
    }
}
