package Solutions.Array.PrefixSum;

//https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum
//1524. Number of Sub-arrays With Odd Sum
public class SubArrayWIthOddSum {
    // Kepp track of odd and even sum
    public int numOfSubarrays(int[] arr) {
        int MOD = 1_000_000_007;
        long sum = 0;
        int odd = 0;
        int even = 1;
        long running = 0;
        for (int num : arr) {
            running += num;
            if (running % 2 == 0) {
                sum += odd;
                even++;
            } else {
                sum += even;
                odd++;
            }
            sum %= MOD;
        }
        return (int) sum;
    }
}
