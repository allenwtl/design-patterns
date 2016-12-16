package com.allen.learn.v3.component;

import com.allen.learn.v3.Beverage;

/**
 * 这个是具体的饮料
 */
public class Decaf extends Beverage{

    public Decaf() {
        setDescription("Decaf");
    }

    @Override
    public double cost() {
        return 4;
    }
}
