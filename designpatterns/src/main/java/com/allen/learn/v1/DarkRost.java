package com.allen.learn.v1;


public class DarkRost  extends  Beverage{

    public DarkRost() {
        setDescription("DarkRost");
    }

    @Override
    double cost() {
        return 2;
    }
}
