package service;

import model.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RandomScoreGeneratorServiceTest {

    private RandomScoreGeneratorService randomScoreGeneratorService;
    private Random random;

    private List<Score> scores;
    private List<Integer> weights;

    @BeforeEach
    void setUp() {
        this.scores = Arrays.asList(Score.OUT, Score.ONE, Score.OUT.THREE);
        this.weights = Arrays.asList(2, 3, 1);
        this.random = mock(Random.class);
        this.randomScoreGeneratorService = new RandomScoreGeneratorService(random);
    }

    @Test
    void shouldGetRandomNumberBelowSumOfWeights() {
        this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        verify(this.random).nextInt(6);
    }

    @Test
    void shouldReturnFirstScore() {
        when(this.random.nextInt(6)).thenReturn(0);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(0));
    }

    @Test
    void shouldReturnFirstScoreWhenRandomReturns1() {
        when(this.random.nextInt(6)).thenReturn(1);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(0));
    }

    @Test
    void shouldReturnSecondScoreWhenRandomReturns2() {
        when(this.random.nextInt(6)).thenReturn(2);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(1));
    }

    @Test
    void shouldReturnSecondScoreWhenRandomReturns3() {
        when(this.random.nextInt(6)).thenReturn(3);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(1));
    }

    @Test
    void shouldReturnSecondScoreWhenRandomReturns4() {
        when(this.random.nextInt(6)).thenReturn(4);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(1));
    }

    @Test
    void shouldReturnSecondScoreWhenRandomReturns5() {
        when(this.random.nextInt(6)).thenReturn(5);

        Score score = this.randomScoreGeneratorService.generateBasedOnFrequencies(this.scores, this.weights);

        assertEquals(score, this.scores.get(2));
    }

}