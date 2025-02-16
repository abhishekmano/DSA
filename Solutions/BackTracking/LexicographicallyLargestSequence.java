package Solutions.BackTracking;

//1718. Construct the Lexicographically Largest Valid Sequence
//https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence
public class LexicographicallyLargestSequence {
    public int[] constructDistancedSequence(int n) {
        int[] result = new int[2 * n - 1];
        boolean[] added = new boolean[n + 1];
        result[0] = n;
        if (n == 1)
            return result;
        result[n] = n;
        added[n] = true;
        construct(result, added, 1, n - 1);
        return result;
    }

    public boolean construct(int[] result, boolean[] added, int index, int numToAdd) {
        int len = result.length;
        if (index == result.length)
            return true;
        // we added a number in this position
        if (result[index] != 0) {
            return construct(result, added, index + 1, numToAdd);
        }
        for (int num = numToAdd; num >= 1; --num) {
            if (added[num])
                continue;
            if (num == 1) {
                added[num] = true;
                result[index] = num;
                if (construct(result, added, index + 1, numToAdd))
                    return true;
                added[num] = false;
                result[index] = 0;
            } else {
                if (index + num < len && result[index + num] == 0) {
                    result[index] = num;
                    result[index + num] = num;
                    added[num] = true;
                    if (construct(result, added, index + 1, numToAdd)) {
                        return true;
                    }
                    result[index] = 0;
                    result[index + num] = 0;
                    added[num] = false;
                }
            }
        }
        return false;
    }
}
