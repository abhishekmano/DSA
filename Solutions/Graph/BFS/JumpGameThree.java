package Solutions.Graph.BFS;

import java.util.LinkedList;
import java.util.Queue;

//1306. Jump Game III
//https://leetcode.com/problems/jump-game-iii
public class JumpGameThree {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;
        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.offer(start);
        while (bfs.size() != 0) {
            var top = bfs.poll();
            if (arr[top] == 0)
                return true;
            int next = top + arr[top];
            if (next < n && !visited[next]) {
                bfs.add(next);
                visited[next] = true;
            }
            int prev = top - arr[top];
            if (prev >= 0 && !visited[prev]) {
                bfs.add(prev);
                visited[prev] = true;
            }
        }
        return false;
    }
}
