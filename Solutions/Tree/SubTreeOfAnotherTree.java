package Solutions.Tree;

//https://leetcode.com/problems/subtree-of-another-tree/
//572. Subtree of Another Tree
public class SubTreeOfAnotherTree {
    // Runs in O (M * N)
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (isSameTree(root, subRoot))
            return true;
        if (root != null)
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        return false;
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null)
            return p == null && q == null;
        if (p.val != q.val)
            return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
