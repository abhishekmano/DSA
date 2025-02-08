package Solutions.Array.SlidingWIndow;

import java.util.ArrayList;
import java.util.List;

//3439. Reschedule Meetings for Maximum Free Time I
//https://leetcode.com/problems/reschedule-meetings-for-maximum-free-time-i/description
//Idea is if can reschedule k meeting then maximum gap is teh sum of K+1 gaps they have
public class RescheduleMeetingOne {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        List<Integer> gaps = new ArrayList<Integer>();
        int end = 0;
        int n = startTime.length;
        for (int idx = 0; idx < n; ++idx) {
            gaps.add(startTime[idx] - end);
            end = endTime[idx];
        }
        gaps.add(eventTime - end);
        int max = 0;
        for (int idx = 0; idx <= k; ++idx) {
            max += gaps.get(idx);
        }
        int running = max;
        for (int idx = k + 1; idx < gaps.size(); ++idx) {
            running -= gaps.get(idx - k - 1);
            running += gaps.get(idx);
            max = Math.max(max, running);
        }
        return max;
    }
}
