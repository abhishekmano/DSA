package Algorithms.Array;

//ALgorithm to find the majority element in an array
public class BoyerMooreVotingAlgorithm {
    public int MajorityElement(int[] nums) {
        int elem = nums[0], count = 1;
        for (int idx = 1; idx < nums.length; ++idx) {
            if (elem == nums[idx]) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    elem = nums[idx];
                    count = 1;
                }
            }

        }
        return elem;

    }
}
