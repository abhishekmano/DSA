package Solutions.Array.SlidingWIndow;

//1151. Minimum Swaps to Group All 1's Together
//https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together
public class MinSwapToMakeAllOnesTogether {
    public int minSwaps(int[] data) {
        int oneCount = 0;
        for (int num : data) {
            oneCount += num;
        }
        int running = 0;
        for (int idx = 0; idx < oneCount; ++idx) {
            running += data[idx];
        }
        int minSwap = oneCount - running;
        if (minSwap == 0)
            return 0;
        for (int idx = oneCount; idx < data.length; ++idx) {
            running += (data[idx] - data[idx - oneCount]);
            minSwap = Math.min(minSwap, oneCount - running);
            if (minSwap == 0)
                return 0;

        }
        return minSwap;
    }
}
