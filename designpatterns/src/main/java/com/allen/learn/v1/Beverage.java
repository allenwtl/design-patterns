package com.allen.learn.v1;

/**
 * 这个是饮料类
 * 当这个饮料配合多种调料，将会产生很多的类
 * A^{a,b,c,d}
 * 总共产生这么多类，维护很麻烦
 * Aa,Ab,Ac,Ad,Aab,Aac,Aad,Abc,Abd,Acd,Aabc,Aabd,Aacd,Abcd,Aabcd
 */
public abstract class Beverage {

    private String description ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    abstract double cost();
}
