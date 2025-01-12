package Solutions.HashMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

//939. Minimum Area Rectangle
//https://leetcode.com/problems/minimum-area-rectangle
public class MinimumAreaRectangle {
    // This is by grouping them by x and then storing the y
    // Iterate through pair of y and checking which was the last x which had the
    // same y pairs
    public int minAreaRect(int[][] points) {
        TreeMap<Integer, List<Integer>> map = new TreeMap();
        int min = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            if (!map.containsKey(x)) {
                map.put(x, new ArrayList<Integer>());
            }
            map.get(x).add(y);
        }
        HashMap<Pair, Integer> ypairMap = new HashMap();
        for (int x : map.keySet()) {
            List<Integer> sety = map.get(x);
            Collections.sort(sety);
            int n = sety.size();
            for (int idx = 0; idx < n; ++idx) {
                for (int idy = idx + 1; idy < n; ++idy) {
                    int y1 = sety.get(idx);
                    int y2 = sety.get(idy);
                    Pair ypair = new Pair(y1, y2);
                    if (ypairMap.containsKey(ypair)) {
                        int x1 = ypairMap.get(ypair);
                        min = Math.min((y2 - y1) * (x - x1), min);
                    }
                    ypairMap.put(ypair, x);
                }
            }

        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public record Pair(int y1, int y2) {
    };

    // This is by adding all pairs into a hashset and looping through 2 pairs and
    // check their alternates exist
    public int minAreaRect2(int[][] points) {
        HashSet<Pair> map = new HashSet();
        int min = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x = point[0];
            int y = point[1];
            Pair pair = new Pair(x, y);
            map.add(pair);
        }
        int n = points.length;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = idx + 1; idy < n; ++idy) {
                int x1 = points[idx][0];
                int y1 = points[idx][1];
                int x2 = points[idy][0];
                int y2 = points[idy][1];
                if (x1 == x2 || y1 == y2)
                    continue;
                if (map.contains(new Pair(x1, y2)) && map.contains(new Pair(x2, y1))) {
                    int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
                    min = Math.min(area, min);
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
