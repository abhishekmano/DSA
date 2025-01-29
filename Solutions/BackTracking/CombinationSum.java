package Solutions.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//39. Combination Sum
//https://leetcode.com/problems/combination-sum
public class CombinationSum {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        generate(candidates, target, 0, new ArrayList<>());
        return result;
    }

    public void generate(int[] candidate, int left, int index, List<Integer> generated) {
        if (left == 0) {
            List<Integer> temp = new ArrayList<>();
            for (int num : generated) {
                temp.add(num);
            }
            result.add(temp);
            return;
        }
        for (int idx = index; idx < candidate.length; ++idx) {
            if (left - candidate[idx] >= 0) {
                generated.add(candidate[idx]);
                generate(candidate, left - candidate[idx], idx, generated);
                generated.remove(generated.size() - 1);
            }
        }
    }
}
