package Solutions.Intervals;

import java.util.Arrays;

//https://leetcode.com/problems/meeting-rooms/
//252. Meeting Rooms
//given meeting schedule check if a single person attend all meetings
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        int n = intervals.length;
        for (int idx = 0; idx < n - 1; ++idx) {
            if (intervals[idx][1] > intervals[idx + 1][0])
                return false;
        }
        return true;
    }
}
