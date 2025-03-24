package Solutions.Intervals;

import java.util.Arrays;

//https://leetcode.com/problems/count-days-without-meetings
//3169. Count Days Without Meetings
public class CountWaysWithoutMeeting {
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int total = 0;
        int n = meetings.length;
        int maxEnd = 0;
        for (int idx = 0; idx < meetings.length; ++idx) {
            int end = meetings[idx][1];
            int start = meetings[idx][0];
            if (start > maxEnd)
                total += start - maxEnd - 1;
            maxEnd = Math.max(maxEnd, end);
        }
        if (maxEnd < days)
            total += days - maxEnd;
        return total;
    }
}
