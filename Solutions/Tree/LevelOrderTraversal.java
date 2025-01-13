package Solutions.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//102. Binary Tree Level Order Traversal
//https://leetcode.com/problems/binary-tree-level-order-traversal/
public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> bfs = new LinkedList();
        bfs.add(root);
        while (bfs.size() != 0) {
            List<Integer> current = new ArrayList();
            int count = bfs.size();
            while (count != 0) {
                count--;
                var top = bfs.remove();
                current.add(top.val);
                if (top.left != null)
                    bfs.add(top.left);
                if (top.right != null)
                    bfs.add(top.right);
            }
            result.add(current);
        }
        return result;

    }
}
