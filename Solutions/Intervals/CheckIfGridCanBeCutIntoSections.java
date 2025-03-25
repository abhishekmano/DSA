package Solutions.Intervals;

import java.util.Arrays;

//3394. Check if Grid can be Cut into Sections
// https://leetcode.com/problems/check-if-grid-can-be-cut-into-sections
public class CheckIfGridCanBeCutIntoSections {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        Arrays.sort(rectangles, (a, b) -> a[0] - b[0]);
        int maxEnding = rectangles[0][2];
        int freeSlots = 0;
        for (int[] h : rectangles) {
            if (h[0] >= maxEnding) {
                freeSlots++;
            }
            maxEnding = Math.max(maxEnding, h[2]);
        }
        if (freeSlots >= 2)
            return true;
        freeSlots = 0;
        Arrays.sort(rectangles, (a, b) -> a[1] - b[1]);
        maxEnding = rectangles[0][3];
        for (int[] v : rectangles) {
            if (v[1] >= maxEnding) {
                freeSlots++;
            }
            maxEnding = Math.max(maxEnding, v[3]);
        }
        if (freeSlots >= 2)
            return true;
        return false;
    }
}
