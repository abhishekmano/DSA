package Solutions.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//253. Meeting Rooms II
//https://leetcode.com/problems/meeting-rooms-ii
//Question is given time stamp of meeting rooms how many meeting rooms are required in minimum
public class MeetingRoomsTwo {
    // using Priority Queue
    public int minMeetingRoomsPQ(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
        for (int[] inter : intervals) {
            // new room is required
            if (pq.isEmpty() || pq.peek()[1] > inter[0]) {
                pq.offer(inter);
            } else {
                pq.poll();
                pq.offer(inter);
            }
        }
        return pq.size();
    }

    // using Chronological sorting of time
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> times = new ArrayList<int[]>();
        for (int[] interval : intervals) {
            times.add(new int[] { interval[0], 1 });
            times.add(new int[] { interval[1], 0 });
        }
        // same time should give prio to ending meetings
        times.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int max = 0;
        int running = 0;
        for (int[] pair : times) {
            if (pair[1] == 1)
                running++;
            else
                running--;
            max = Math.max(running, max);
        }
        return max;

    }
}
