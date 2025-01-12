package Solutions.Queue;

import java.util.ArrayList;
import java.util.List;

//2534. Time Taken to Cross the Door
//https://leetcode.com/problems/time-taken-to-cross-the-door
public class TimeTakenToCrossTheDoor {
    public int[] timeTaken(int[] arrival, int[] state) {
        List<Pair> in = new ArrayList<Pair>();
        List<Pair> out = new ArrayList<Pair>();
        int n = arrival.length;
        int[] result = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            if (state[idx] == 0)
                in.add(new Pair(arrival[idx], idx));
            else
                out.add(new Pair(arrival[idx], idx));
        }
        // If input is sorted then no need to sort
        // Collections.sort(in , (a,b)-> a.time == b.time ? a.index - b.index : a.time -
        // b.time);
        // Collections.sort(in , (a,b)-> a.time == b.time ? a.index - b.index : a.time -
        // b.time);
        int first = in.size();
        int second = out.size();
        int currentState = -1;
        // state 0 means in state 1 means out;
        int time = 0;
        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr1 < first || ptr2 < second) {
            // no valid
            int inTime = ptr1 < first ? in.get(ptr1).time : Integer.MAX_VALUE;
            int outTime = ptr2 < second ? out.get(ptr2).time : Integer.MAX_VALUE;
            if (inTime > time && outTime > time) {
                time = Math.min(inTime, outTime);
                currentState = -1;
            }
            if (currentState == -1 || currentState == 1) {
                // current out can go
                if (ptr2 < second && out.get(ptr2).time <= time) {
                    Pair elem = out.get(ptr2);
                    result[elem.index] = time;
                    time++;
                    ptr2++;
                } else if (ptr1 < first && in.get(ptr1).time <= time) {
                    Pair elem = in.get(ptr1);
                    result[elem.index] = time;
                    time++;
                    currentState = 0;
                    ptr1++;
                }
            } else {
                if (ptr1 < first && in.get(ptr1).time <= time) {
                    Pair elem = in.get(ptr1);
                    result[elem.index] = time;
                    time++;
                    ptr1++;
                } else if (ptr2 < second && out.get(ptr2).time <= time) {
                    Pair elem = out.get(ptr2);
                    result[elem.index] = time;
                    time++;
                    currentState = 1;
                    ptr2++;
                }
            }
        }
        return result;

    }

    public record Pair(int time, int index) {
    }
}
