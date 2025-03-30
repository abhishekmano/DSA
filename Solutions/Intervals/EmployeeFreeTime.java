package Solutions.Intervals;
// We are given a list schedule of employees, which represents the working time for each employee.

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.

// Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.

// (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).  Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.

//https://leetcode.com/problems/employee-free-time
//759. Employee Free Time
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> merged = new ArrayList<Interval>();
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new PairComparer());
        for (List<Interval> sch : schedule) {
            pq.offer(new Pair(sch, 0));
        }
        while (pq.size() != 0) {
            Pair top = pq.remove();
            Interval inter = top.inter.get(top.index);
            if (merged.size() == 0)
                merged.add(inter);
            else {
                int endTime = merged.get(merged.size() - 1).end;
                if (endTime < inter.start) {
                    merged.add(inter);
                } else {
                    merged.get(merged.size() - 1).end = Math.max(inter.end, endTime);
                }
            }
            int newIndex = top.index + 1;
            if (newIndex != top.inter.size()) {
                pq.offer(new Pair(top.inter, newIndex));
            }
        }
        List<Interval> result = new ArrayList<Interval>();
        for (int idx = 1; idx < merged.size(); ++idx) {
            Interval toAdd = new Interval(merged.get(idx - 1).end, merged.get(idx).start);
            result.add(toAdd);
        }
        return result;

    }

    public record Pair(List<Interval> inter, int index) {
    }

    public class PairComparer implements Comparator<Pair> {
        public int compare(Pair a, Pair b) {
            if (a.index >= a.inter.size())
                return 1;
            if (b.index >= b.inter.size())
                return -1;
            return a.inter.get(a.index).start - b.inter.get(b.index).start;

        }
    }
}

class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
