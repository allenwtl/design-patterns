package com.allen.learn.v3.decorator;

import com.allen.learn.v3.Beverage;

/**
 * 这个是调料
 */
public class SoyDecorator extends BaseDecorator {

    private Beverage delegate ;

    public SoyDecorator(Beverage delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getDescription() {
        return "Soy"+"->"+delegate.getDescription();
    }

    @Override
    public double cost() {
        return 1.3+delegate.cost();
    }
}
