package Solutions.Tree.BST;

//230. Kth Smallest Element in a BST
//https://leetcode.com/problems/kth-smallest-element-in-a-bst
public class KthSmallestInBST {
    int count = 0;
    int elem = 0;

    public int kthSmallest(TreeNode root, int k) {
        DFS(root, k);
        return elem;
    }

    public void DFS(TreeNode root, int k) {
        if (root == null)
            return;
        DFS(root.left, k);
        count++;
        if (count == k) {
            elem = root.val;
            return;
        }
        DFS(root.right, k);
    }
}
