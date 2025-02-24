package Solutions.Tree.Traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import Solutions.Tree.TreeNode;

//314. Binary Tree Vertical Order Traversal
//https://leetcode.com/problems/binary-tree-vertical-order-traversal
public class VerticalOrderTraversal {
    TreeMap<Integer, List<Integer>> vertical = new TreeMap<Integer, List<Integer>>();

    // O(NLog N)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<Pair> bfs = new LinkedList<>();
        if (root != null)
            bfs.offer(new Pair(root, 0));
        while (bfs.size() != 0) {
            Pair top = bfs.poll();
            TreeNode node = top.node;
            int index = top.index;
            vertical.computeIfAbsent(index, k -> new ArrayList<>()).add(node.val);
            if (node.left != null)
                bfs.offer(new Pair(node.left, index - 1));
            if (node.right != null)
                bfs.offer(new Pair(node.right, index + 1));
        }
        for (int key : vertical.keySet()) {
            result.add(vertical.get(key));
        }
        return result;
    }

    public record Pair(TreeNode node, int index) {
    };
}
