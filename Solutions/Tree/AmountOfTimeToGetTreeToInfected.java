package Solutions.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//2385. Amount of Time for Binary Tree to Be Infected
//https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected
public class AmountOfTimeToGetTreeToInfected {
    TreeNode target = null;
    HashMap<TreeNode, TreeNode> parent = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root, start);
        parent.put(root, null);
        int maxTime = 0;
        Queue<TreeNode> bfs = new LinkedList<TreeNode>();
        HashSet<TreeNode> visited = new HashSet<>();
        visited.add(target);
        bfs.offer(target);
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                TreeNode top = bfs.poll();
                count--;
                if (top.left != null && !visited.contains(top.left)) {
                    bfs.offer(top.left);
                    visited.add(top.left);
                }
                if (top.right != null && !visited.contains(top.right)) {
                    bfs.offer(top.right);
                    visited.add(top.right);
                }
                TreeNode p = parent.get(top);
                if (p != null && !visited.contains(p)) {
                    bfs.offer(p);
                    visited.add(p);
                }
            }
            maxTime++;
        }
        return maxTime - 1;
    }

    public void dfs(TreeNode node, int start) {
        if (node == null)
            return;
        if (node.val == start)
            target = node;
        if (node.left != null) {
            parent.put(node.left, node);
            dfs(node.left, start);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            dfs(node.right, start);
        }
    }

    private int maxDistance = 0;

    // Single Pass Super Duper Solution from LeetCode editorial
    public int amountOfTime2(TreeNode root, int start) {
        traverse(root, start);
        return maxDistance;
    }

    public int traverse(TreeNode root, int start) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        int leftDepth = traverse(root.left, start);
        int rightDepth = traverse(root.right, start);

        if (root.val == start) {
            maxDistance = Math.max(leftDepth, rightDepth);
            depth = -1;
        } else if (leftDepth >= 0 && rightDepth >= 0) {
            depth = Math.max(leftDepth, rightDepth) + 1;
        } else {
            int distance = Math.abs(leftDepth) + Math.abs(rightDepth);
            maxDistance = Math.max(maxDistance, distance);
            depth = Math.min(leftDepth, rightDepth) - 1;
        }

        return depth;
    }
}
