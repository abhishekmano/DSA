package Solutions.TwoPointer;

import java.util.ArrayList;
import java.util.List;

//2570. Merge Two 2D Arrays by Summing Values
//https://leetcode.com/problems/merge-two-2d-arrays-by-summing-values/description
public class MergeTwoTwoDArrays {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> result = new ArrayList<int[]>();
        int m = nums1.length;
        int n = nums2.length;
        int ptr1 = 0;
        int ptr2 = 0;
        while (ptr1 < m || ptr2 < n) {
            int id1 = ptr1 < m ? nums1[ptr1][0] : Integer.MAX_VALUE;
            int id2 = ptr2 < n ? nums2[ptr2][0] : Integer.MAX_VALUE;
            if (id1 == id2) {
                result.add(new int[] { id1, nums1[ptr1][1] + nums2[ptr2][1] });
                ptr1++;
                ptr2++;
            } else if (id1 < id2) {
                result.add(new int[] { id1, nums1[ptr1][1] });
                ptr1++;
            } else {
                result.add(new int[] { id2, nums2[ptr2][1] });
                ptr2++;
            }
        }
        return result.toArray(new int[result.size()][2]);
    }
}
