package service;

import model.Score;

import java.util.List;
import java.util.Random;

public class RandomScoreGeneratorService {

    private final Random random;

    public RandomScoreGeneratorService(Random random) {
        this.random = random;
    }

    public Score generateBasedOnFrequencies(List<Score> list, List<Integer> frequencies) {
        int prefix[] = new int[list.size()];
        prefix[0] = frequencies.get(0);
        for (int index = 1; index < list.size(); ++index) {
            prefix[index] = prefix[index - 1] + frequencies.get(index);
        }
        int lastPrefixValue = prefix[list.size() - 1];
        int randomScore = random.nextInt(lastPrefixValue) + 1;
        int scoreIndex = ceil(prefix, randomScore);
        return list.get(scoreIndex);
    }

    private int ceil(int[] array, int target) {
        int start;
        for (start = 0; start < array.length && target > array[start]; start++) ;
        return (array[start] >= target) ? start : -1;
    }

}
