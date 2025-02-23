package Solutions.Tree.Traversal;

import java.util.HashMap;

import Solutions.Tree.TreeNode;

//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
//105. Construct Binary Tree from Preorder and Inorder Traversal
public class ConstructFromPreAndIno {
    HashMap<Integer, Integer> inorderIndex = new HashMap<>();
    int index = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // in pre order root comes first always
        int n = inorder.length;
        for (int idx = 0; idx < n; ++idx) {
            inorderIndex.put(inorder[idx], idx);
        }
        return construct(preorder, 0, n - 1);

    }

    public TreeNode construct(int[] preOrder, int left, int right) {
        if (left > right)
            return null;
        int elem = preOrder[index];
        index++;
        TreeNode node = new TreeNode(elem);
        int middle = inorderIndex.get(elem);
        node.left = construct(preOrder, left, middle - 1);
        node.right = construct(preOrder, middle + 1, right);
        return node;
    }
}
