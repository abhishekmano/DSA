package Solutions.Tree.Traversal;

import Solutions.Tree.TreeNode;

//889. Construct Binary Tree from Preorder and Postorder Traversal
//https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
public class ConstructFromPreAndPost {
    int[] postIndex;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = postorder.length;
        postIndex = new int[n + 1];
        for (int idx = 0; idx < n; ++idx) {
            postIndex[postorder[idx]] = idx;
        }
        TreeNode root = construct(preorder, postorder, 0, n - 1, 0);
        return root;
    }

    // PreStart
    // PreEnd
    // PostStart
    // assume that the next element in preOrder is always its left child
    public TreeNode construct(int[] preOrder, int[] postOrder, int preS, int preE, int poS) {
        if (preS > preE)
            return null;
        int elem = preOrder[preS];
        TreeNode root = new TreeNode(elem);
        if (preS == preE)
            return root;
        int leftChild = preOrder[preS + 1];
        // root has this many left childs
        int leftChildsCount = postIndex[leftChild] - poS + 1;
        root.left = construct(preOrder, postOrder, preS + 1, preS + leftChildsCount, poS);
        root.right = construct(preOrder, postOrder, preS + leftChildsCount + 1, preE, poS + leftChildsCount);
        return root;
    }
}
