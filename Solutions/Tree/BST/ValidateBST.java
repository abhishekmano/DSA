package Solutions.Tree.BST;

//98. Validate Binary Search Tree
//https://leetcode.com/problems/validate-binary-search-tree
public class ValidateBST {
    // Solution using DFS
    TreeNode prev = null;
    boolean ans = true;

    public boolean isValidBST2(TreeNode root) {
        DFS(root);
        return ans;
    }

    public void DFS(TreeNode root) {
        if (root == null)
            return;
        DFS(root.left);
        if (prev != null) {
            if (root.val <= prev.val) {
                ans = false;
                return;
            }
        }
        prev = root;
        DFS(root.right);
    }

    // This uses range of inputs
    public boolean isValidBST(TreeNode root) {
        return isValid(root, (long) Integer.MIN_VALUE, (long) Integer.MAX_VALUE);
    }

    public boolean isValid(TreeNode node, long left, long right) {
        if (node == null)
            return true;
        if (node.val > right || node.val < left)
            return false;
        return isValid(node.left, left, (long) node.val - 1) && isValid(node.right, (long) node.val + 1, right);
    }
}
