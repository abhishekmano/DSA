package Algorithms.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Solutions.Tree.TreeNode;

public class Inorder {
    List<Integer> inorder = new ArrayList<>();

    // Recursive
    public void inorder(TreeNode root) {
        if (root == null)
            return;
        else {
            inorder(root.left);
            inorder.add(root.val);
            inorder(root.right);
        }

    }

    // Iterative
    public List<Integer> InorderTraversal(TreeNode root) {
        Stack<TreeNode> inorder = new Stack<>();
        List<Integer> res = new ArrayList<>();
        TreeNode ptr = root;
        while (ptr != null || inorder.size() != 0) {
            if (ptr != null) {
                inorder.push(ptr);
                ptr = ptr.left;
            } else {
                var top = inorder.pop();
                res.add(top.val);
                ptr = top.right;

            }
        }
        return res;
    }
}
