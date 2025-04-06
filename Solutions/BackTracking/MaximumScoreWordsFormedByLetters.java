package Solutions.BackTracking;

//1255. Maximum Score Words Formed by Letters
//https://leetcode.com/problems/maximum-score-words-formed-by-letters
public class MaximumScoreWordsFormedByLetters {
    int maxScore = 0;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int[] count = new int[26];
        for (char letter : letters) {
            count[letter - 'a']++;
        }
        scoreFinder(0, 0, words, count, score);
        return maxScore;

    }

    public void scoreFinder(int index, int sum, String[] word, int[] count, int[] score) {
        if (index == word.length) {
            maxScore = Math.max(maxScore, sum);
            return;
        }
        scoreFinder(index + 1, sum, word, count, score);
        boolean isPossible = true;
        int currentScore = 0;
        for (char letter : word[index].toCharArray()) {
            count[letter - 'a']--;
            currentScore += score[letter - 'a'];
        }
        for (int elem : count) {
            if (elem < 0) {
                isPossible = false;
                break;
            }
        }
        if (isPossible) {
            scoreFinder(index + 1, sum + currentScore, word, count, score);
        }
        for (char letter : word[index].toCharArray()) {
            count[letter - 'a']++;
        }
    }
}
