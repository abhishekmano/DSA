package Solutions.BitManipulation;

//190. Reverse Bits
//https://leetcode.com/problems/reverse-bits
public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int idx = 0; idx <= 31; ++idx) {
            res = res << 1;
            int bit = n & 1;
            if (bit == 1) {
                res = res | 1;
            }
            n = n >> 1;
        }
        return res;
    }
}
