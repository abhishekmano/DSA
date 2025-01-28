package Solutions.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//56. Merge Intervals
//https://leetcode.com/problems/merge-intervals/description/
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int n = intervals.length;
        for (int idx = 1; idx < n; ++idx) {
            int[] inter = intervals[idx];
            int[] last = result.get(result.size() - 1);
            int start = last[0];
            int end = last[1];
            boolean overlap = end >= inter[0];
            if (overlap) {
                end = Math.max(end, inter[1]);
                result.set(result.size() - 1, new int[] { start, end });
            } else {
                result.add(inter);
            }
        }
        return result.toArray(new int[result.size()][]);

    }
}
