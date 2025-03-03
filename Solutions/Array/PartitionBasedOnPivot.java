package Solutions.Array;

import java.util.ArrayList;
import java.util.List;

//2161. Partition Array According to Given Pivot
//https://leetcode.com/problems/partition-array-according-to-given-pivot
public class PartitionBasedOnPivot {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> first = new ArrayList<Integer>();
        List<Integer> second = new ArrayList<Integer>();
        int pivCount = 0;
        for (int num : nums) {
            if (num < pivot) {
                first.add(num);
            } else if (num > pivot) {
                second.add(num);
            } else {
                pivCount++;
            }
        }
        for (int idx = 1; idx <= pivCount; ++idx) {
            first.add(pivot);
        }
        for (int num : second) {
            first.add(num);
        }
        int[] result = new int[first.size()];
        int index = 0;
        for (int num : first) {
            result[index] = num;
            index++;
        }
        return result;
    }
}
