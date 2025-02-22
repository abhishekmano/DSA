package Solutions.Tree;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/recover-a-tree-from-preorder-traversal
//1028. Recover a Tree From Preorder Traversal
public class RecoverATreeFromPreOrder {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public TreeNode recoverFromPreorder(String traversal) {
            List<TreeNode> levelQueue = new ArrayList<>();
            List<Node> nodeAndLevel = new ArrayList<>();
            int level = 0;
            int lastNum = 0;
            int n = traversal.length();
            for (int idx = 0; idx < n; ++idx) {
                char ch = traversal.charAt(idx);
                if (Character.isDigit(ch)) {
                    lastNum = lastNum * 10 + Integer.valueOf(ch - '0');
                    // we got a new number
                    if (idx == n - 1 || !Character.isDigit(traversal.charAt(idx + 1))) {
                        nodeAndLevel.add(new Node(lastNum, level));
                        level = 0;
                        lastNum = 0;
                    }
                } else {
                    level++;
                }
            }
            // System.out.println(nodeAndLevel);
            TreeNode root = new TreeNode();
            for (Node element : nodeAndLevel) {
                // we have a new Level
                int value = element.value;
                int lvl = element.level;
                if (lvl == levelQueue.size()) {
                    levelQueue.add(new TreeNode());
                }
                if (lvl == 0) {
                    TreeNode node = new TreeNode(value);
                    levelQueue.set(0, node);
                    root = node;

                } else {
                    TreeNode parent = levelQueue.get(lvl - 1);
                    TreeNode node = new TreeNode(value);
                    if (parent.left == null) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                    levelQueue.set(lvl, node);
                }
            }
            return root;
        }

        public record Node(int value, int level) {
        };
    }
}
