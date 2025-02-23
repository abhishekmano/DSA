package Solutions.Tree.Traversal;

import java.util.HashMap;

import Solutions.Tree.TreeNode;

//106. Construct Binary Tree from Inorder and Postorder Traversal
//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
public class ConstructFromPostAndIno {
    HashMap<Integer, Integer> inorderIndex = new HashMap<>();
    int index = 0;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // in pre order root comes first always
        int n = inorder.length;
        index = n - 1;
        for (int idx = 0; idx < n; ++idx) {
            inorderIndex.put(inorder[idx], idx);
        }
        return construct(postorder, 0, n - 1);
    }

    public TreeNode construct(int[] postorder, int left, int right) {
        if (left > right)
            return null;
        int elem = postorder[index];
        index--;
        TreeNode node = new TreeNode(elem);
        int middle = inorderIndex.get(elem);
        node.right = construct(postorder, middle + 1, right);
        node.left = construct(postorder, left, middle - 1);
        return node;
    }
}
