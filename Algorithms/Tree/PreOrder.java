package Algorithms.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Solutions.Tree.TreeNode;

public class PreOrder {
    List<Integer> result = new ArrayList<>();

    // Iterative
    public List<Integer> preOrderTraversal(TreeNode root) {
        Stack<TreeNode> pre = new Stack<>();
        if (root != null)
            pre.push(root);
        while (pre.size() != 0) {
            TreeNode top = pre.pop();
            result.add(top.val);
            if (top.right != null)
                pre.push(top.right);
            if (top.left != null)
                pre.push(top.left);
        }
        return result;
    }

    // Recursive
    public void preOrder(TreeNode node) {
        if (node == null)
            return;
        result.add(node.val);
        preOrder(node.left);
        preOrder(node.right);

    }
}
