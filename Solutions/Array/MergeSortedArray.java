package Solutions.Array;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // either i have to move all values for nums1 to its end
        // instead i will start from the end and keep on comparing
        int ptr1 = m - 1;
        int ptr2 = n - 1;
        int ptr = m + n - 1;
        while (ptr >= 0) {
            int first = ptr1 >= 0 ? nums1[ptr1] : Integer.MIN_VALUE;
            int second = ptr2 >= 0 ? nums2[ptr2] : Integer.MIN_VALUE;
            if (first > second) {
                nums1[ptr] = first;
                ptr1--;
            } else {
                nums1[ptr] = second;
                ptr2--;
            }
            ptr--;
        }
    }
}
