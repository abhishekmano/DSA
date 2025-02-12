package Solutions.HashMap;

import java.util.HashMap;
import java.util.PriorityQueue;

//2342. Max Sum of a Pair With Equal Sum of Digits
//https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits
public class MaxSumOfPairWithEqualSumDigits {
    public int maximumSum(int[] nums) {
        HashMap<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int sum = 0;
            int elem = num;
            while (elem != 0) {
                sum += elem % 10;
                elem = elem / 10;
            }
            if (!map.containsKey(sum)) {
                map.put(sum, new PriorityQueue<Integer>());
            }
            map.get(sum).add(num);
            if (map.get(sum).size() > 2) {
                map.get(sum).poll();
            }
        }
        int res = -1;
        for (int key : map.keySet()) {
            PriorityQueue<Integer> pq = map.get(key);
            if (pq.size() == 2) {
                int sum = pq.poll();
                sum += pq.poll();
                res = Math.max(res, sum);
            }
        }
        return res;
    }
}
