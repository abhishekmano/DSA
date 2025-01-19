package Solutions.BitManipulation;

//338. Counting Bits
//https://leetcode.com/problems/counting-bits
public class CountingBits {
    // this is using DP to count set bits
    // just check least significant bit and calculate
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        result[0] = 0;
        if (n == 0)
            return result;
        result[1] = 1;
        for (int idx = 2; idx <= n; ++idx) {
            result[idx] = ((idx & 1) == 1 ? 1 : 0) + result[idx >> 1];
        }
        return result;
    }
}
