package com.allen.learn.v3;

import com.allen.learn.v3.component.DarkRoast;
import com.allen.learn.v3.decorator.MochaDecorator;
import com.allen.learn.v3.decorator.SoyDecorator;

/**
 *
 */
public class Client {

    public static void main(String[] args) {
        Beverage beverage = new DarkRoast();

        beverage = new MochaDecorator(beverage);

        beverage = new SoyDecorator(beverage);

        System.out.println(beverage.getDescription());
        System.out.println(beverage.cost());

    }

}
