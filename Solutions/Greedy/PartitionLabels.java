package Solutions.Greedy;

import java.util.ArrayList;
import java.util.List;

//763. Partition Labels
//https://leetcode.com/problems/partition-labels
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<Integer>();
        int[] last = new int[26];
        for (int idx = 0; idx < s.length(); ++idx) {
            char ch = s.charAt(idx);
            last[ch - 'a'] = idx;
        }
        int currLast = 0;
        int prevLast = -1;
        for (int idx = 0; idx < s.length(); ++idx) {
            char ch = s.charAt(idx);
            ;
            currLast = Math.max(last[ch - 'a'], currLast);
            if (idx == currLast) {
                result.add(currLast - prevLast);
                prevLast = currLast;
            }
        }
        return result;
    }
}
