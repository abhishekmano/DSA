package Solutions.Graph.TopologicalSort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//2127. Maximum Employees to Be Invited to a Meeting
//https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting
public class MaximumEmployeesForMeeting {
    // Idea is to remove the employees who are not part of any cycle and
    // run dfs on every employees part of the cycle and find largest cycle
    // for each cycle of 2 length a<->b every node on the other sides of a and =b
    // can be sat
    // so one variable to keep the sum of these employees
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] depth = new int[n];
        int[] indegree = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            indegree[favorite[idx]]++;
        }
        Queue<Integer> bfs = new LinkedList<Integer>();
        for (int idx = 0; idx < n; ++idx) {
            if (indegree[idx] == 0)
                bfs.offer(idx);
        }
        Arrays.fill(depth, 1);
        while (bfs.size() != 0) {
            int top = bfs.remove();
            int next = favorite[top];
            depth[next] = Math.max(depth[next], depth[top] + 1);
            indegree[next]--;
            if (indegree[next] == 0)
                bfs.offer(next);
        }
        int max = 0;
        int twoCycles = 0;
        for (int idx = 0; idx < n; ++idx) {
            // this has a cycle from it
            if (indegree[idx] != 0) {
                int len = 0;
                int next = idx;
                bfs.offer(idx);
                while (bfs.size() != 0) {
                    next = bfs.poll();
                    len++;
                    indegree[next] = 0;
                    if (indegree[favorite[next]] != 0) {
                        bfs.add(favorite[next]);
                    }
                }
                if (len == 2) {
                    twoCycles += depth[idx] + depth[next];
                } else {
                    max = Math.max(max, len);
                }
            }
        }
        return Math.max(max, twoCycles);

    }
}
