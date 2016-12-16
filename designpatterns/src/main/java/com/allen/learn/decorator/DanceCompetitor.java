package com.allen.learn.decorator;

/**
 * Created by tianlun.wu on 2016/12/14.
 */
public class DanceCompetitor implements Competitor{

    private Competitor competitor ;

    public DanceCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    @Override
    public void sing() {
        competitor.sing();
        dance();
    }

    private void dance(){
        System.out.println("Im dancing");
    }

}
