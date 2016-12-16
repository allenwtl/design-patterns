package com.allen.learn.decorator;

/**
 * Created by tianlun.wu on 2016/12/14.
 */
public class Client {

    public static void main(String[] args) {

        Competitor competitor = new BaseCompetitor("wtl");

        competitor = new DanceCompetitor(competitor);

        competitor = new MusicCompetitor(competitor);

        competitor.sing();

        System.out.println("--------------------------------------------");

        competitor = new IntroduceCompetitor(competitor);

        competitor.sing();

    }

}
