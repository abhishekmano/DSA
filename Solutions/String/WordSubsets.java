package Solutions.String;

import java.util.ArrayList;
import java.util.List;

//916. Word Subsets
//https://leetcode.com/problems/word-subsets
public class WordSubsets {
    // Complexity O(M)* S + O(N) * P
    // M length of words 1 N length of words2 M is max length of words in1 P is max
    // length of words in words2
    // O(M+N) //given max length of word is 10
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] count = new int[26];
        List<String> result = new ArrayList<String>();
        for (String word : words2) {
            int[] current = getFreq(word);
            for (int idx = 0; idx < 26; ++idx) {
                count[idx] = Math.max(count[idx], current[idx]);
            }
        }
        for (String word : words1) {
            int[] current = getFreq(word);
            boolean isSuper = true;
            for (int idx = 0; idx < 26; ++idx) {
                if (current[idx] < count[idx]) {
                    isSuper = false;
                    break;
                }
            }
            if (isSuper) {
                result.add(word);
            }
        }
        return result;
    }

    public int[] getFreq(String word) {
        int[] count = new int[26];
        for (char ch : word.toCharArray()) {
            count[ch - 'a']++;
        }
        return count;
    }
}
