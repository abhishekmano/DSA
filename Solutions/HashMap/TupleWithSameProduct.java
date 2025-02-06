package Solutions.HashMap;

import java.util.HashMap;

//1726. Tuple with Same Product
//https://leetcode.com/problems/tuple-with-same-product
public class TupleWithSameProduct {
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> pairs = new HashMap();
        int n = nums.length;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = idx + 1; idy < n; ++idy) {
                int prod = nums[idx] * nums[idy];
                pairs.put(prod, pairs.getOrDefault(prod, 0) + 1);
            }
        }
        int result = 0;
        for (int value : pairs.values()) {
            // int setOfTwo = value * (value - 1)/2;
            // //each pair can product 8 combinations
            // result += setOfTwo * 8;
            result += value * (value - 1) * 4;
        }
        return result;
    }
}
