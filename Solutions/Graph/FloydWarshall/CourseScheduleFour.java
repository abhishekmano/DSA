package Solutions.Graph.FloydWarshall;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//1462. Course Schedule IV
//https://leetcode.com/problems/course-schedule-iv
public class CourseScheduleFour {
    // Using Floyed Warshall Algorithm
    // Q + N^3
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        boolean[][] isPre = new boolean[n][n];
        for (int[] pre : prerequisites) {
            isPre[pre[0]][pre[1]] = true;
        }
        for (int mid = 0; mid < n; ++mid) {
            for (int source = 0; source < n; ++source) {
                for (int dest = 0; dest < n; ++dest) {
                    isPre[source][dest] = isPre[source][dest] || (isPre[source][mid] && isPre[mid][dest]);
                }
            }
        }
        List<Boolean> result = new ArrayList<Boolean>();
        for (int[] query : queries) {
            boolean res = isPre[query[0]][query[1]];
            result.add(res);
        }
        return result;
    }

    // using Standard BFS from all source
    // Complexity => Q + N^3
    boolean[][] pre;

    public List<Boolean> checkIfPrerequisite2(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        List<Integer>[] adjList = new List[n];
        pre = new boolean[n][n];
        for (int idx = 0; idx < n; ++idx) {
            adjList[idx] = new ArrayList<Integer>();
        }
        for (int[] pre : prerequisites) {
            adjList[pre[0]].add(pre[1]);
        }
        for (int idx = 0; idx < n; ++idx) {
            bfs(idx, adjList);
        }
        List<Boolean> result = new ArrayList<Boolean>();
        for (int[] query : queries) {
            boolean res = pre[query[0]][query[1]];
            result.add(res);
        }
        return result;
    }

    public void bfs(int node, List<Integer>[] adjList) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int n = adjList.length;
        boolean[] visited = new boolean[n];
        queue.offer(node);
        visited[node] = true;
        while (queue.size() != 0) {
            int top = queue.poll();
            for (int next : adjList[top]) {
                pre[node][next] = true;
                if (visited[next] == false) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }
}
