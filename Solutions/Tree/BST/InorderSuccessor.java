package Solutions.Tree.BST;

//https://leetcode.com/problems/inorder-successor-in-bst
//285. Inorder Successor in BST
public class InorderSuccessor {
    TreeNode result = null;

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        find(root, p);
        return result;
    }

    public void find(TreeNode node, TreeNode p) {
        if (node == null)
            return;
        if (node.val > p.val) {
            result = node;
            // find a further small value
            find(node.left, p);
        } else {
            find(node.right, p);
        }
    }
}
