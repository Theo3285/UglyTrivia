package com.kata.refactoring.uglytrivia;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.converters.Param;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class QuestionsShould {

    public static final int MAX_QUESTIONS_PER_CATEGORY = 50;

    @Test
    public void create_questions() {
        //Arrange
        LinkedList<String> expectedPopQuestions = new LinkedList<String>();
        LinkedList<String> expectedScienceQuestions = new LinkedList<String>();
        LinkedList<String> expectedSportsQuestions = new LinkedList<String>();
        LinkedList<String> expectedRockQuestions = new LinkedList<String>();

        for (int i = 0; i < MAX_QUESTIONS_PER_CATEGORY; i++) {
            expectedPopQuestions.addLast("Pop Question " + i);
            expectedScienceQuestions.addLast(("Science Question " + i));
            expectedSportsQuestions.addLast(("Sports Question " + i));
            expectedRockQuestions.addLast("Rock Question " + i);
        }

        //Act
        Questions questions = new Questions();

        //Assert
        assertThat(questions.getPopQuestions(), is(expectedPopQuestions));
        assertThat(questions.getScienceQuestions(), is(expectedScienceQuestions));
        assertThat(questions.getSportsQuestions(), is(expectedSportsQuestions));
        assertThat(questions.getRockQuestions(), is(expectedRockQuestions));
    }

    @Test
    @Parameters ({
            "0, Pop Question 0",
            "1, Science Question 0",
            "2, Sports Question 0",
            "3, Rock Question 0"
    })
    public void return_question_at_place(int place, String question) {
        Questions questions = new Questions();
        assertThat(questions.askQuestionAt(place), is(question));
    }
}