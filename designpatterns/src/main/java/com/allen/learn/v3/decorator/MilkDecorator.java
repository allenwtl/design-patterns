package com.allen.learn.v3.decorator;

import com.allen.learn.v3.Beverage;

/**
 * 这个是调料
 */
public class MilkDecorator extends BaseDecorator {

    private Beverage delegate ;

    public MilkDecorator(Beverage delegate) {
        this.delegate = delegate ;
    }



    @Override
    public String getDescription() {
        return "Milk"+"->"+delegate.getDescription();
    }

    @Override
    public double cost() {
        return 1+delegate.cost();
    }
}
