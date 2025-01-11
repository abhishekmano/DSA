package Solutions.Sorting;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/longest-common-subsequence-between-sorted-arrays
//Leetcode 1940 Longest Common Subsequence Between Sorted Arrays
public class SortedArrayCommonSubSequence {
    // O(N) * Max(size(array))
    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        List<Integer> result = new ArrayList();
        for (int elem : arrays[0]) {
            result.add(elem);
        }
        for (int idx = 1; idx < arrays.length; ++idx) {
            List<Integer> second = new ArrayList();
            for (int elem : arrays[idx])
                second.add(elem);
            result = giveCommon(result, second);
        }
        return result;

    }

    // O( Size(a) + Size(b) )
    public List<Integer> giveCommon(List<Integer> first, List<Integer> second) {
        int ptr1 = 0;
        int ptr2 = 0;
        List<Integer> result = new ArrayList();
        while (ptr1 < first.size() && ptr2 < second.size()) {
            int firstElement = first.get(ptr1);
            int secondElement = second.get(ptr2);
            if (firstElement == secondElement) {
                result.add(firstElement);
                ptr1++;
                ptr2++;
            } else if (firstElement < secondElement) {
                ptr1++;
            } else {
                ptr2++;
            }
        }
        return result;
    }
}
