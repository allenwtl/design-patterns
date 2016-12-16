package com.allen.learn.v3.component;

import com.allen.learn.v3.Beverage;

/**
 * 这个是具体的饮料
 */
public class DarkRoast extends Beverage{

    public DarkRoast() {
        setDescription("DarkRoast");
    }

    @Override
    public double cost() {
        return 3;
    }
}
