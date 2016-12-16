package com.allen.learn.v3;

/**
 * 这个是饮料类
 */
public abstract class Beverage {

    private String description ="UNKNOW DESCRIPTION";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract double cost();


}
