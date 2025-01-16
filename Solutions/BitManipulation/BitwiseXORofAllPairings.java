package Solutions.BitManipulation;

//2425. Bitwise XOR of All Pairings
//https://leetcode.com/problems/bitwise-xor-of-all-pairings
public class BitwiseXORofAllPairings {
    public int xorAllNums(int[] nums1, int[] nums2) {
        // if num2 length is odd then every element in a num1 is available in xor
        // if num1 length is even then num2 element wont be in result
        int result = 0;
        if (nums1.length % 2 == 1) {
            for (int num : nums2)
                result ^= num;
        }
        if (nums2.length % 2 == 1) {
            for (int num : nums1)
                result ^= num;
        }
        return result;
    }
}
