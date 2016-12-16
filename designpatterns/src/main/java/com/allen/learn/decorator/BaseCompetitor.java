package com.allen.learn.decorator;

/**
 * Created by tianlun.wu on 2016/12/14.
 */
public class BaseCompetitor implements Competitor {
    private String name ;

    public BaseCompetitor(String name) {
        this.name = name;
    }

    @Override
    public void sing() {
        System.out.println("Im "+ name +", Im sing");
    }
}
