package Solutions.Tree;

//979. Distribute Coins in Binary Tree
//https://leetcode.com/problems/distribute-coins-in-binary-tree
public class DistributeCoins {
    int total = 0;

    public int distributeCoins(TreeNode root) {
        dfs(root);
        return total;
    }

    public int dfs(TreeNode node) {
        if (node == null)
            return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        total += Math.abs(left) + Math.abs(right);
        int current = left + right + node.val;
        // take one and give rest to parent
        current--;
        return current;
    }
}
