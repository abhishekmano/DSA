package Solutions.HashMap;

//2657. Find the Prefix Common Array of Two Arrays
//https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays
public class CommonPrefixArrayCount {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int[] result = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0)
                result[idx] = result[idx - 1];
            freq[A[idx]]++;
            freq[B[idx]]++;
            if (freq[A[idx]] == 2)
                result[idx]++;
            // This is to avoid double counting
            if (A[idx] != B[idx])
                if (freq[B[idx]] == 2)
                    result[idx]++;
        }
        return result;
    }

    // or the comparison can be removed by
    public int[] findThePrefixCommonArray2(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int[] result = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0)
                result[idx] = result[idx - 1];
            freq[A[idx]]++;
            if (freq[A[idx]] == 2)
                result[idx]++;
            freq[B[idx]]++;
            if (freq[B[idx]] == 2)
                result[idx]++;
        }
        return result;
    }
}
