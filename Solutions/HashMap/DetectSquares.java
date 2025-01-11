package Solutions.HashMap;

import java.util.HashMap;

//2013. Detect Squares
//https://leetcode.com/problems/detect-squares
public class DetectSquares {
    HashMap<Integer, HashMap<Integer, Integer>> points;

    public DetectSquares() {
        points = new HashMap<Integer, HashMap<Integer, Integer>>();
    }

    public void add(int[] point) {
        int x = point[0];
        int y = point[1];
        if (!points.containsKey(x)) {
            points.put(x, new HashMap<Integer, Integer>());
        }
        points.get(x).put(y, points.get(x).getOrDefault(y, 0) + 1);
    }

    public int count(int[] point) {
        int x = point[0];
        int y = point[1];
        int total = 0;
        if (points.containsKey(x)) {
            for (int nexty : points.get(x).keySet()) {
                int diff = nexty - y;
                int side = Math.abs(diff);
                int count = points.get(x).get(nexty);
                if (diff == 0)
                    continue;
                // above x
                if (points.containsKey(x + side)) {
                    HashMap<Integer, Integer> above = points.get(x + side);
                    if (above.containsKey(y) && above.containsKey(nexty)) {
                        total += (above.get(y) * above.get(nexty) * count);
                    }
                }
                // below x
                if (points.containsKey(x - side)) {
                    HashMap<Integer, Integer> below = points.get(x - side);
                    if (below.containsKey(y) && below.containsKey(nexty)) {
                        total += (below.get(y) * below.get(nexty) * count);
                    }
                }
            }
        }
        return total;
    }
}
