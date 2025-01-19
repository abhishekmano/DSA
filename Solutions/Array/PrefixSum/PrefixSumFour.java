package Solutions.Array.PrefixSum;

//1871. Jump Game VII
//https://leetcode.com/problems/jump-game-vii   
public class PrefixSumFour {
    // use the idea of prefix sum and find each index is reachable or not
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] visited = new int[n];
        visited[0] = 0;
        for (int idx = 0; idx < n; ++idx) {
            if (idx != 0)
                visited[idx] += visited[idx - 1];
            if (idx == 0 || (visited[idx] > 0 && s.charAt(idx) == '0')) {
                int start = idx + minJump;
                int end = idx + maxJump;
                if (start < n) {
                    visited[start]++;
                }
                if (end + 1 < n) {
                    visited[end + 1]--;
                }
            }
        }
        return visited[n - 1] > 0 && s.charAt(n - 1) == '0';
    }
}
