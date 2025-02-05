package Solutions.Array.Maths;

import java.util.HashSet;

//963. Minimum Area Rectangle II
//https://leetcode.com/problems/minimum-area-rectangle-ii/
public class MinimumAreaRectangleTwo {
    public double minAreaFreeRect(int[][] points) {
        // (a ,b ) (c , d)
        HashSet<Point> set = new HashSet<>();
        int n = points.length;
        Point[] array = new Point[n];
        for (int idx = 0; idx < n; ++idx) {
            int[] p = points[idx];
            Point point = new Point(p[0], p[1]);
            array[idx] = point;
            set.add(point);
        }
        double result = Double.MAX_VALUE;
        for (int idx = 0; idx < n; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (idx == idy)
                    continue;
                for (int idz = idy + 1; idz < n; ++idz) {
                    if (idz == idx)
                        continue;
                    Point p1 = array[idx];
                    Point p2 = array[idy];
                    Point p3 = array[idz];
                    Point p4 = findLast(p1, p2, p3);
                    if (set.contains(p4)) {
                        int dot = ((p2.x - p1.x) * (p3.x - p1.x) +
                                (p2.y - p1.y) * (p3.y - p1.y));
                        if (dot == 0) {
                            double area = distance(p1, p2) * distance(p1, p3);
                            result = Math.min(result, area);
                        }
                    }
                }
            }
        }
        return result == Double.MAX_VALUE ? 0 : result;

    }

    public record Point(int x, int y) {
    };

    public double distance(Point a, Point b) {
        int diffx = a.x - b.x;
        int diffy = a.y - b.y;
        return Math.sqrt(diffx * diffx + diffy * diffy);
    }

    // b and c are corners
    public Point findLast(Point a, Point b, Point c) {
        int x = b.x + c.x - a.x;
        int y = b.y + c.y - a.y;
        return new Point(x, y);
    }
}
