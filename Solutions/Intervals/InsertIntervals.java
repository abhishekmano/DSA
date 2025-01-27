package Solutions.Intervals;

import java.util.ArrayList;
import java.util.List;

//57. Insert Interval
//https://leetcode.com/problems/insert-interval
public class InsertIntervals {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int a = newInterval[0];
        int b = newInterval[1];
        int idx = 0;
        int n = intervals.length;
        // before interval
        while (idx < n && intervals[idx][1] < a) {
            result.add(intervals[idx]);
            ++idx;
        }
        // start time of current interval is before end time of new interval
        while (idx < n && intervals[idx][0] <= b) {
            a = Math.min(a, intervals[idx][0]);
            b = Math.max(b, intervals[idx][1]);
            idx++;
        }
        result.add(new int[] { a, b });
        while (idx < n) {
            result.add(intervals[idx]);
            idx++;
        }
        return result.toArray(new int[result.size()][]);

    }
}
