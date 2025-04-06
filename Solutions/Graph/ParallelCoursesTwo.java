package Solutions.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/parallel-courses-ii/
//1494. Parallel Courses II
public class ParallelCoursesTwo {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pre = new int[n];
        int target = (1 << n) - 1;
        // compute the prerequisite bitmask of the every course
        for (int[] relation : relations) {
            int a = relation[0] - 1;
            int b = relation[1] - 1;
            pre[b] |= 1 << a;
        }
        Queue<Pair> bfs = new LinkedList<Pair>();
        bfs.offer(new Pair(0, 0));
        // Shows the visited states
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(0);
        while (!bfs.isEmpty()) {
            Pair top = bfs.poll();
            if (top.courses == target)
                return top.sem;
            int courseLeft = 0;
            for (int idx = 0; idx < n; ++idx) {
                // all courses which are part of the prereq should be already taken
                if (((top.courses >> idx) & 1) == 0 && ((pre[idx] & top.courses) == pre[idx])) {
                    courseLeft |= (1 << idx);
                }
            }
            // pick all the possible combination of next courses
            for (int sub = courseLeft; sub > 0; sub = (sub - 1) & courseLeft) {
                int bitCount = Integer.bitCount(sub);
                if (bitCount > k)
                    continue;
                int newTaken = top.courses | sub;
                if (!visited.contains(newTaken)) {
                    bfs.add(new Pair(newTaken, top.sem + 1));
                    visited.add(newTaken);
                }
            }

        }
        return -1;

    }

    public record Pair(int courses, int sem) {
    }
}
