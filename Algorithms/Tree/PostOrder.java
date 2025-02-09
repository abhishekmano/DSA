package Algorithms.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import Solutions.Tree.TreeNode;

public class PostOrder {
    List<Integer> post = new ArrayList<Integer>();

    // Recursive
    public void postOrder(TreeNode node) {
        if (node == null)
            return;
        if (node.left != null)
            postOrder(node.left);
        if (node.right != null)
            postOrder(node.right);
        post.add(node.val);

    }

    // Iterative 2 stack
    public List<Integer> postOrderTraversal(TreeNode root) {
        Stack<Integer> res = new Stack<>();
        Stack<TreeNode> it = new Stack<>();
        if (root != null)
            it.push(root);
        while (it.size() != 0) {
            var top = it.pop();
            res.push(top.val);
            if (top.left != null)
                it.push(top.left);
            if (top.right != null)
                it.push(top.right);

        }
        List<Integer> postOrder = new ArrayList<>();
        while (res.size() != 0) {
            postOrder.add(res.pop());
        }
        return postOrder;
    }

    // Using Single Stack
    // Taken From Leetcode solution
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        // If the root is null, return an empty list
        if (root == null) {
            return result;
        }

        // To keep track of the previously processed node
        TreeNode previousNode = null;
        // Stack to manage the traversal
        Stack<TreeNode> traversalStack = new Stack<>();

        // Process nodes until both the root is null and the stack is empty
        while (root != null || !traversalStack.isEmpty()) {
            // Traverse to the leftmost node
            if (root != null) {
                traversalStack.push(root);
                root = root.left;
            } else {
                // Peek at the top node of the stack
                root = traversalStack.peek();

                // If there is no right child or the right child was already processed
                if (root.right == null || root.right == previousNode) {
                    result.add(root.val);
                    traversalStack.pop();
                    previousNode = root;
                    root = null; // Ensure we don’t traverse again from this node
                } else {
                    // Move to the right child
                    root = root.right;
                }
            }
        }

        return result;
    }

}
