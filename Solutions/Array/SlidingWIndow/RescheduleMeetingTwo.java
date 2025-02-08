package Solutions.Array.SlidingWIndow;

import java.util.ArrayList;
import java.util.List;

//3440. Reschedule Meetings for Maximum Free Time II
//https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-ii
//Check are there any place we can completely reschedule the meeting into and make a huge gap
public class RescheduleMeetingTwo {
    public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
        int end = 0;
        List<Integer> gaps = new ArrayList<Integer>();
        int n = startTime.length;
        for (int idx = 0; idx < n; ++idx) {
            int gap = startTime[idx] - end;
            gaps.add(gap);
            end = endTime[idx];
        }
        gaps.add(eventTime - end);
        int m = gaps.size();

        int[] preMax = new int[m];
        int[] postMax = new int[m];
        for (int idx = 1; idx < m; ++idx) {
            preMax[idx] = Math.max(preMax[idx - 1], gaps.get(idx - 1));
            postMax[m - idx - 1] = Math.max(postMax[m - idx], gaps.get(m - idx));
        }
        // print(gaps);
        // print(preMax);
        // print(postMax);
        int result = 0;
        for (int idx = 0; idx < m - 1; ++idx) {
            result = Math.max(result, gaps.get(idx) + gaps.get(idx + 1));
            int meetingTime = endTime[idx] - startTime[idx];
            int otherGaps = preMax[idx];
            if (idx + 1 < m)
                otherGaps = Math.max(otherGaps, postMax[idx + 1]);
            if (otherGaps >= meetingTime) {
                int newgap = gaps.get(idx) + gaps.get(idx + 1) + meetingTime;
                result = Math.max(newgap, result);
            }
        }
        return result;
    }
}
