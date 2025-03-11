package Solutions.BinarySearch;

//3453. Separate Squares I
//https://leetcode.com/problems/separate-squares-i/
public class SeperateSquaresOne {
    public double separateSquares(int[][] squares) {
        double start = 0;
        double end = 2 * 1e9;
        for (int i = 0; i < 60; i++) {
            double mid = (double) (end + start) / 2.0;
            double diff = area(squares, mid);
            // System.out.println(diff);
            // System.out.println(start + "," + end);
            if (diff > 0)
                start = mid;
            else
                end = mid;
        }
        return end;

    }

    public double area(int[][] squares, double line) {
        double above = 0;
        double below = 0;
        for (int[] square : squares) {
            int x = square[0];
            int y = square[1];
            double side = (double) square[2];
            double area = (double) side * (double) side;
            if (y >= line) {
                above += area;
            } else if (line >= y + side) {
                below += area;
            } else {
                double areaBelow = (double) (line - y) * side;
                below += areaBelow;
                above += (area - areaBelow);

            }
        }
        return above - below;
    }
}
