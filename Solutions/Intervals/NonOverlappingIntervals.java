package Solutions.Intervals;

import java.util.Arrays;

//435. Non-overlapping Intervals
//https://leetcode.com/problems/non-overlapping-intervals/
public class NonOverlappingIntervals {
    // sort based on end time and the one ending first should be the best pick at
    // every time
    public int eraseOverlapIntervals(int[][] intervals) {
        int count = 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int index = 0;
        for (int idx = 1; idx < intervals.length; ++idx) {
            if (intervals[index][1] <= intervals[idx][0]) {
                index = idx;
            } else {
                // there is overalap need to remove
                count++;
            }
        }
        return count;
    }
}
