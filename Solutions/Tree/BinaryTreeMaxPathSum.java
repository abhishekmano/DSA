package Solutions.Tree;

//124. Binary Tree Maximum Path Sum
//https://leetcode.com/problems/binary-tree-maximum-path-sum
public class BinaryTreeMaxPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        max = Math.max(left + right + root.val, max);
        int maxToPass = Math.max(left, right) + root.val;
        maxToPass = Math.max(root.val, maxToPass);
        max = Math.max(maxToPass, max);
        max = Math.max(max, left + right + root.val);
        return maxToPass;
    }
}
