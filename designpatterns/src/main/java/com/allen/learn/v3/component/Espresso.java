package com.allen.learn.v3.component;

import com.allen.learn.v3.Beverage;

/**
 * 这个是具体的饮料
 */
public class Espresso  extends Beverage{

    public Espresso() {
        setDescription("Espresso");
    }

    @Override
    public double cost() {
        return 1;
    }
}
