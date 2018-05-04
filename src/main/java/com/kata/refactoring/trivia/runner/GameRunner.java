package com.kata.refactoring.trivia.runner;

import java.util.Random;

import com.kata.refactoring.uglytrivia.Game;
import com.kata.refactoring.uglytrivia.Questions;

public class GameRunner {

    private static boolean notAWinner;

    public static void main(String[] args) {
        Questions questions = new Questions();
        Game aGame = new Game(questions, "Chet", "Pat", "Sue");

        Random rand = new Random();

        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

    }
}
