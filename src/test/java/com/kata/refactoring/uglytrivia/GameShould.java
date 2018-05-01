package com.kata.refactoring.uglytrivia;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameShould {

    private static final int TOTAL_NUMBER_OF_ROLLS = 98;
    private static final int SEED = 100;
    private static final int MAX = 50;
    private Game game;

    private Random random = new Random(SEED);
    private List<String> goes = new ArrayList<String>();

    @Before
    public void setUp(){
        game = new Game();
        game.add("Brad");
        game.add("Sue");
        game.add("Betty");
        game.add("Paul");
        game.add("Stefen");
    }

    @Test
    public void addPlayer() {
        generateRandomRolls();
        Approvals.verify(getStringRepresentationFor(goes));
    }

    private void generateRandomRolls() {
        for (int i = 0; i < TOTAL_NUMBER_OF_ROLLS; i++) {
            game.roll(random.nextInt(MAX));
            if (random.nextBoolean())
                game.wasCorrectlyAnswered();
            else
                game.wrongAnswer();
            goes.add(game.toString());
        }
    }

    private String getStringRepresentationFor(List<String> plays) {
        StringBuilder builder = new StringBuilder();
        for (String play : plays) {
            builder.append(play).append("\r");
        }
        return builder.toString();
    }
}