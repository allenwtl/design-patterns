package com.allen.learn.v2;

/**
 * 这个是饮料类
 *  mil,soy,mocha,whep,为各种可选的调料
 *
 *  子类是菜单上的一种饮品
 *  可能是由一种或者多个调料组合而成
 *
 *  当增加一种调料，就要增加新的字段，新的方法，新的饮品
 *
 *
 */
public class Beverage {

    private String description ;

    private boolean milk;

    private boolean soy;

    private boolean mocha;

    private boolean whep ;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double cost(){
        double money = 2;
        if(hasMilk()){
            money +=1;
        }
        if(hasMocha()){
            money +=2;
        }

        if(hasSoy()){
            money +=3;
        }

        if(hasWhep()){
            money +=4;
        }
        return money;
    }


    public boolean hasMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean hasSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean hasMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean hasWhep() {
        return whep;
    }

    public void setWhep(boolean whep) {
        this.whep = whep;
    }
}
