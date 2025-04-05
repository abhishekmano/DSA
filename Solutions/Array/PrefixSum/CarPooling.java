package Solutions.Array.PrefixSum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://leetcode.com/problems/car-pooling
//1094. Car Pooling
public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        List<int[]> tripList = new ArrayList<int[]>();
        for (int[] trip : trips) {
            int from = trip[1];
            int to = trip[2];
            int passengers = trip[0];
            if (passengers > capacity)
                return false;
            tripList.add(new int[] { from, -passengers });
            tripList.add(new int[] { to, passengers });
        }
        // print(tripList);
        Collections.sort(tripList, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int current = capacity;
        // print(tripList);
        for (int[] details : tripList) {
            current += details[1];
            if (current < 0)
                return false;
        }
        return true;
    }
}
