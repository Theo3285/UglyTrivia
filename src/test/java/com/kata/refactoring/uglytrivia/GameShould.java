package com.kata.refactoring.uglytrivia;

import org.approvaltests.Approvals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameShould {

    private Game game;
    private List<String> goes = new ArrayList<String>();
    Questions questions;

    @Before
    public void setUp(){
        questions = new Questions();
        game = new Game(questions, "Brad", "Sue", "Betty", "Paul", "Stefen");
    }

    @Test
    public void run_the_game_with_approvals() {
        Random rand = new Random(50);
        int i = 0;
        do {
            game.roll(rand.nextInt(5) + 1);
            if (rand.nextBoolean()) {
                game.wrongAnswer();
            } else {
                game.wasCorrectlyAnswered();
            }
            i++;
            goes.add(game.toString());
        } while (i < 100);
        Approvals.verify(getStringRepresentationFor(goes));
    }

    @Test
    public void play_until_someone_win_the_game() {
        Random rand = new Random();
        boolean notAWinner = false;

        Questions questions = new Questions();
        Game aGame = new Game(questions, "Chet", "Pat", "Sue");

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);
    }
    private String getStringRepresentationFor(List<String> plays) {
        StringBuilder builder = new StringBuilder();
        for (String play : plays) {
            builder.append(play).append("\r");
        }
        return builder.toString();
    }

}