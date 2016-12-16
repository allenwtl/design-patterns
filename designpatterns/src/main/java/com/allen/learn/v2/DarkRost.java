package com.allen.learn.v2;



public class DarkRost  extends Beverage {

    public DarkRost() {
        setDescription("DarkRost");
    }

    @Override
    public double cost() {
        setSoy(true);
        setMocha(true);
        return super.cost();
    }
}
