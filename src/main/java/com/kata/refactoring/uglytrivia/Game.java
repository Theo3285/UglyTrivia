package com.kata.refactoring.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
    private static final int MAX_PLACES = 6;
    private static final int MAX_PURSES = 6;
    private static final int MAX_PENALTY_BOX = 6;

    private int[] places = new int[MAX_PLACES];
    private int[] purses = new int[MAX_PURSES];
    private boolean[] inPenaltyBox = new boolean[MAX_PENALTY_BOX];

    private ArrayList<String> players = new ArrayList<String>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;
    private int roll;

    private Questions questions;

    public Game(Questions questions, String... players) {
        Collections.addAll(this.players, players);
        this.questions = questions;
        startTheGame();
    }

    private void startTheGame() {
        places[countPlayers()] = 0;
        purses[countPlayers()] = 0;
        inPenaltyBox[countPlayers()] = false;
    }

    public int countPlayers() {
        return players.size();
    }

    public void roll(int aRoll) {
        roll = aRoll;
        if (playerCanPlay()) {
            movePlayerOntoTheBoard();
            questions.askQuestionAt(places[currentPlayer]);
        }
    }

    private boolean isPlayerInPenaltyBox() {
        return inPenaltyBox[currentPlayer];
    }

    private boolean canPlayerGetOutOfPenaltyBox() {
        isGettingOutOfPenaltyBox = roll % 2 != 0;
        return isGettingOutOfPenaltyBox;
    }

    private void movePlayerOntoTheBoard() {
        places[currentPlayer] += roll;
        if (places[currentPlayer] > 11) {
            places[currentPlayer] -= 12;
        }
    }

    public boolean wasCorrectlyAnswered() {
        addPurse();
        nextPlayer();
        return didPlayerWin();
    }

    private void addPurse() {
        if (playerCanPlay()) {
            purses[currentPlayer]++;
        }
    }

    private boolean playerCanPlay() {
        return !isPlayerInPenaltyBox() || canPlayerGetOutOfPenaltyBox();
    }

    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer == players.size()) {
            currentPlayer = 0;
        }
    }

    public boolean wrongAnswer() {
        movePlayerToPenaltyBox();
        nextPlayer();
        return true;
    }

    private void movePlayerToPenaltyBox() {
        inPenaltyBox[currentPlayer] = true;
    }

    private boolean didPlayerWin() {
        return (purses[currentPlayer] != 6);
    }

    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", places=" + Arrays.toString(places) +
                ", purses=" + Arrays.toString(purses) +
                ", inPenaltyBox=" + Arrays.toString(inPenaltyBox) +
                ", popQuestions=" + questions.getPopQuestions() +
                ", scienceQuestions=" + questions.getScienceQuestions() +
                ", sportsQuestions=" + questions.getSportsQuestions() +
                ", rockQuestions=" + questions.getRockQuestions() +
                ", currentPlayer=" + currentPlayer +
                ", isGettingOutOfPenaltyBox=" + isGettingOutOfPenaltyBox +
                '}';
    }
}
