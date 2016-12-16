package com.allen.learn.decorator;

/**
 * Created by tianlun.wu on 2016/12/14.
 */
public class IntroduceCompetitor implements Competitor {

    private Competitor competitor ;

    public IntroduceCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    @Override
    public void sing() {
        competitor.sing();
        introduce();
    }

    private void introduce(){
        System.out.println("Im introduce my story");
    }

}
