package Solutions.HashMap;

//2661. First Completely Painted Row or Column
//https://leetcode.com/problems/first-completely-painted-row-or-column
public class FirstCompletelyPainterRowOrColumn {
    // O(M*N) time complexity
    // O(M*N) + O(M) + O(N) space
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Pair[] mapper = new Pair[m * n + 1];
        int[] rCounter = new int[m];
        int[] cCounter = new int[n];
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                Pair p = new Pair(idx, idy);
                mapper[mat[idx][idy]] = p;
            }
        }
        for (int idx = 0; idx < arr.length; ++idx) {
            Pair mapped = mapper[arr[idx]];
            int x = mapped.x;
            int y = mapped.y;
            rCounter[x]++;
            cCounter[y]++;
            if (rCounter[x] == n)
                return idx;
            if (cCounter[y] == m)
                return idx;
        }
        return arr.length - 1;
    }

    public record Pair(int x, int y) {
    };
}
