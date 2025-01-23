package Solutions.Array.TwoDimension;

//1267. Count Servers that Communicate
//https://leetcode.com/problems/count-servers-that-communicate
public class CountServersThatCommunicate {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] rowSum = new int[m];
        int[] colSum = new int[n];
        int totalMachines = 0;
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                rowSum[idx] += grid[idx][idy];
                colSum[idy] += grid[idx][idy];
                totalMachines += grid[idx][idy];
            }
        }
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                if (grid[idx][idy] == 1 && rowSum[idx] == 1 && colSum[idy] == 1) {
                    totalMachines--;
                }
            }
        }
        return totalMachines;
    }
}
