package Solutions.HashMap;

import java.util.HashMap;
import java.util.PriorityQueue;

//347. Top K Frequent Elements
//https://leetcode.com/problems/top-k-frequent-elements
public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> counter = new HashMap();
        for (int num : nums) {
            counter.put(num, counter.computeIfAbsent(num, j -> 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> counter.get(a) - counter.get(b));
        for (int key : counter.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] res = new int[k];
        for (int idx = 0; idx < k; ++idx) {
            res[idx] = pq.poll();
        }
        return res;

    }
}
