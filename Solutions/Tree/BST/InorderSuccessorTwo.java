package Solutions.Tree.BST;

//https://leetcode.com/problems/inorder-successor-in-bst-ii
//510. Inorder Successor in BST II
public class InorderSuccessorTwo {
    public Node inorderSuccessor(Node node) {
        // if it has right child then inorder successor is the leftmost child of right
        // subtree
        if (node.right != null) {
            Node ptr = node.right;
            while (ptr.left != null) {
                ptr = ptr.left;
            }
            return ptr;
        }
        // in this case we have to go to the parent iteratively and find the first
        // parent whose left child is this
        Node ptr = node;
        Node parent = node.parent;
        while (parent != null && parent.left != ptr) {
            ptr = parent;
            parent = parent.parent;
        }
        return parent;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
