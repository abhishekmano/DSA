package Solutions.HashMap.SortedMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

//This is unrealistic to solve in an interview that too without inbuilt functions
//https://leetcode.com/problems/range-module/
//715. Range Module
public class RangeModule {
    TreeSet<Range> set;

    public RangeModule() {
        set = new TreeSet<>(new RangeComparator());
    }

    public void addRange(int left, int right) {
        Iterator<Range> tail = set.tailSet(new Range(0, left)).iterator();
        while (tail.hasNext()) {
            Range next = tail.next();
            if (next.left > right) {
                break;
            }
            left = Math.min(left, next.left);
            right = Math.max(right, next.right);
            tail.remove();
        }
        set.add(new Range(left, right));

    }

    public boolean queryRange(int left, int right) {
        Range high = set.higher(new Range(0, left));
        if (high == null)
            return false;
        if (high.left <= left && high.right >= right)
            return true;
        return false;
    }

    public void removeRange(int left, int right) {
        Iterator<Range> tail = set.tailSet(new Range(0, left - 1)).iterator();
        List<Range> toAdd = new ArrayList<Range>();
        while (tail.hasNext()) {
            Range next = tail.next();
            if (next.left > right) {
                break;
            }
            if (next.left < left) {
                toAdd.add(new Range(next.left, left));
            }
            if (right < next.right) {
                toAdd.add(new Range(right, next.right));
            }
            tail.remove();
        }
        for (Range add : toAdd) {
            set.add(add);
        }

    }

    public record Range(int left, int right) {
    };

    public class RangeComparator implements Comparator<Range> {
        public int compare(Range a, Range b) {
            return a.right == b.right ? a.left - b.left : a.right - b.right;
        }
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
