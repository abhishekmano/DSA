package Solutions.BitManipulation;

//https://leetcode.com/problems/sum-of-two-integers/
//371. Sum of Two Integers
public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        int result = 0;
        int carry = 0;
        for (int idx = 0; idx <= 31; ++idx) {
            int bit1 = a & 1;
            int bit2 = b & 1;
            int sum = bit1 + bit2 + carry;
            int currBit = sum & 1;
            carry = (sum >> 1) & 1;
            result |= currBit << idx;
            b = b >> 1;
            a = a >> 1;
        }
        return result;
    }
}