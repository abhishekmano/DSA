package Solutions.Tree;

//2096. Step-By-Step Directions From a Binary Tree Node to Another
//https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class StepByStepFromOneNodeToAnother {
    HashMap<TreeNode, TreeNode> parent = new HashMap<>();
    List<Character> toStart = new ArrayList<>();
    List<Character> toEnd = new ArrayList<>();

    // This method uses technique to find lca lowest common ancestor of a tree
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = lca(root, startValue, destValue);
        find(lca, startValue, true);
        find(lca, destValue, false);
        // System.out.println(toStart);
        // System.out.println(toEnd);
        StringBuilder result = new StringBuilder();
        for (char ch : toStart)
            result.append(ch);
        for (char ch : toEnd)
            result.append(ch);
        return result.toString();
    }

    public boolean find(TreeNode node, int val, boolean isStart) {
        if (node == null)
            return false;
        if (node.val == val)
            return true;
        if (isStart)
            toStart.add('U');
        else
            toEnd.add('L');
        boolean left = find(node.left, val, isStart);
        if (left)
            return true;
        if (isStart)
            toStart.remove(toStart.size() - 1);
        else
            toEnd.remove(toEnd.size() - 1);
        if (isStart)
            toStart.add('U');
        else
            toEnd.add('R');
        boolean right = find(node.right, val, isStart);
        if (right == false) {
            if (isStart)
                toStart.remove(toStart.size() - 1);
            else
                toEnd.remove(toEnd.size() - 1);
        }
        return right;
    }

    public TreeNode lca(TreeNode node, int a, int b) {
        if (node == null)
            return null;
        if (node.val == a || node.val == b)
            return node;
        TreeNode left = lca(node.left, a, b);
        TreeNode right = lca(node.right, a, b);
        if (left != null && right != null)
            return node;
        return left != null ? left : right;
    }

    TreeNode start = null;
    TreeNode dest = null;

    // We create a graph from the tree by storing parent node as wel
    // after than we perform a BFS to find the route
    public String getDirections2(TreeNode root, int startValue, int destValue) {
        parent.put(root, null);
        traverse(root, startValue, destValue);
        HashSet<TreeNode> visited = new HashSet<>();
        Queue<Pair> bfs = new LinkedList<Pair>();
        bfs.offer(new Pair(start, ""));
        visited.add(start);
        while (bfs.size() != 0) {
            int count = bfs.size();
            while (count != 0) {
                count--;
                Pair top = bfs.poll();
                if (top.node == dest)
                    return top.path;
                TreeNode parentNode = parent.get(top.node);
                if (top.node.left != null && !visited.contains(top.node.left)) {
                    visited.add(top.node.left);
                    bfs.offer(new Pair(top.node.left, top.path + 'L'));
                }
                if (top.node.right != null && !visited.contains(top.node.right)) {
                    visited.add(top.node.right);
                    bfs.offer(new Pair(top.node.right, top.path + 'R'));
                }
                if (parentNode != null && !visited.contains(parentNode)) {
                    visited.add(parentNode);
                    bfs.offer(new Pair(parentNode, top.path + 'U'));
                }
            }
        }
        return "";
    }

    public record Pair(TreeNode node, String path) {
    }

    public void traverse(TreeNode node, int startValue, int destValue) {
        if (node == null)
            return;
        if (node.val == startValue)
            start = node;
        if (node.val == destValue)
            dest = node;
        if (node.left != null) {
            parent.put(node.left, node);
            traverse(node.left, startValue, destValue);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            traverse(node.right, startValue, destValue);
        }
    }
}
