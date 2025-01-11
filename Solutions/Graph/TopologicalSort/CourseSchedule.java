package Solutions.Graph.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//207. Course Schedule
//https://leetcode.com/problems/course-schedule
public class CourseSchedule {
    // Using Kahn's Algorithm
    //
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<Integer>[] adjList = new List[numCourses];
        for (int idx = 0; idx < numCourses; ++idx) {
            adjList[idx] = new ArrayList<Integer>();
        }
        for (int[] pre : prerequisites) {
            adjList[pre[1]].add(pre[0]);
            indegree[pre[0]]++;
        }
        List<Integer> result = new ArrayList();
        Queue<Integer> bfs = new LinkedList();
        for (int idx = 0; idx < numCourses; ++idx) {
            if (indegree[idx] == 0)
                bfs.add(idx);
        }
        while (bfs.size() != 0) {
            int top = bfs.remove();
            result.add(top);
            for (int adj : adjList[top]) {
                indegree[adj]--;
                if (indegree[adj] == 0)
                    bfs.add(adj);
            }
        }
        if (result.size() != numCourses)
            return false;
        return true;
    }
}
