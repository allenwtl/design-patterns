package com.allen.learn.v3.decorator;

import com.allen.learn.v3.Beverage;

/**
 * 这个是调料
 */
public class MochaDecorator extends BaseDecorator {

    private Beverage delegate ;

    public MochaDecorator(Beverage delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getDescription() {
        return "Mocha"+"->"+delegate.getDescription();
    }

    @Override
    public double cost() {
        return 1.2+delegate.cost();
    }
}
