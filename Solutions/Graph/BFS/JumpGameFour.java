package Solutions.Graph.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//1345. Jump Game IV
//https://leetcode.com/problems/jump-game-iv
public class JumpGameFour {
    public int minJumps(int[] arr) {
        int n = arr.length;
        if (n == 1)
            return 0;
        boolean[] visited = new boolean[n];
        HashMap<Integer, List<Integer>> same = new HashMap<>();
        for (int idx = 0; idx < n; ++idx) {
            same.computeIfAbsent(arr[idx], k -> new ArrayList<Integer>()).add(idx);
        }
        // print(adjList);
        // we can start bfs now;
        Queue<Integer> bfs = new LinkedList<Integer>();
        bfs.add(0);
        visited[0] = true;
        int steps = 0;
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                var top = bfs.poll();
                if (same.containsKey(arr[top])) {
                    for (int adj : same.get(arr[top])) {
                        if (!visited[adj]) {
                            visited[adj] = true;
                            bfs.offer(adj);
                            if (adj == n - 1)
                                return steps + 1;
                        }
                    }
                    same.remove(arr[top]);
                }
                if (top + 1 < n) {
                    int adj = top + 1;
                    if (!visited[adj]) {
                        visited[adj] = true;
                        bfs.offer(adj);
                        if (adj == n - 1)
                            return steps + 1;
                    }
                }
                if (top - 1 >= 0) {
                    int adj = top - 1;
                    if (!visited[adj]) {
                        visited[adj] = true;
                        bfs.offer(adj);
                        if (adj == n - 1)
                            return steps + 1;
                    }
                }
            }
            steps++;
        }
        return -1;

    }
}
