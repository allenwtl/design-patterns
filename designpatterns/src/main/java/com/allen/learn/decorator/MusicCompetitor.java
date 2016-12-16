package com.allen.learn.decorator;

/**
 * Created by tianlun.wu on 2016/12/14.
 */
public class MusicCompetitor implements Competitor {

    private Competitor competitor ;

    public MusicCompetitor(Competitor competitor) {
        this.competitor = competitor;
    }

    @Override
    public void sing() {
        competitor.sing();
        playMusic();
    }

    private void playMusic(){
        System.out.println("Im play music");
    }

}
