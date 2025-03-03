package Solutions.BinarySearch;

import java.util.Arrays;

//875. Koko Eating Bananas
//https://leetcode.com/problems/koko-eating-bananas
public class KokoEatingBanana {
    public int minEatingSpeed(int[] piles, int h) {
        int max = Arrays.stream(piles).max().getAsInt();
        int min = 1;
        int res = max;
        while (max >= min) {
            int mid = min + (max - min) / 2;
            long hour = reqHours(piles, mid);
            if (hour <= h) {
                res = mid;
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return res;
    }

    public long reqHours(int[] piles, int count) {
        long total = 0;
        for (int elem : piles) {
            total += elem / count;
            if (elem % count != 0)
                total++;
        }
        return total;
    }
}
