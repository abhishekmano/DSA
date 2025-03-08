package Solutions.Array.SlidingWIndow;

//2379. Minimum Recolors to Get K Consecutive Black Blocks
//https://leetcode.com/problems/minimum-recolors-to-get-k-consecutive-black-blocks
public class MinimumRecolorsToConsecutiveKBlacks {
    public int minimumRecolors(String blocks, int k) {
        // we will solve it using a window of size k;
        int minCount = Integer.MAX_VALUE;
        int runningCount = 0;
        for (int idx = 0; idx < k; ++idx) {
            if (blocks.charAt(idx) == 'W')
                runningCount++;
        }
        minCount = runningCount;
        for (int idx = k; idx < blocks.length(); ++idx) {
            if (blocks.charAt(idx) == 'W')
                runningCount++;
            if (blocks.charAt(idx - k) == 'W')
                runningCount--;
            minCount = Math.min(minCount, runningCount);
            if (minCount == 0)
                return 0;
        }
        return minCount;
    }
}
