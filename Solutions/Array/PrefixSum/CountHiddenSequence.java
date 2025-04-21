package Solutions.Array.PrefixSum;

//2145. Count the Hidden Sequences
//https://leetcode.com/problems/count-the-hidden-sequences
public class CountHiddenSequence {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        int curr = 0;
        int min = 0;
        int max = 0;
        for (int diff : differences) {
            curr += diff;
            min = Math.min(curr, min);
            max = Math.max(curr, max);
            if (max - min > upper - lower)
                return 0;
        }
        return (upper - lower) - (max - min) + 1;
    }
}
