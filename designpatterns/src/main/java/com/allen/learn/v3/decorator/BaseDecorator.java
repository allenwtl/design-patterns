package com.allen.learn.v3.decorator;

import com.allen.learn.v3.Beverage;

/**
 * 基本的装饰者
 */
public abstract class BaseDecorator extends Beverage{

    public abstract String getDescription();

}
