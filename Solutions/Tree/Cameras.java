package Solutions.Tree;

import java.util.HashSet;

//https://leetcode.com/problems/binary-tree-cameras/
//968. Binary Tree Cameras
public class Cameras {
    public int minCameraCover(TreeNode root) {
        Options best = solve(root);
        return Math.min(best.noCam, best.cam);
    }

    // DP
    public Options solve(TreeNode node) {
        if (node == null) {
            return new Options(0, 0, 99999);
        }
        Options left = solve(node.left);
        Options right = solve(node.right);
        int leftBestCover = Math.min(left.noCam, left.cam);
        int rightBestCover = Math.min(right.cam, right.noCam);
        int dontCover = left.noCam + right.noCam;
        int coverNoCam = Math.min(leftBestCover + right.cam, left.cam + rightBestCover);
        int coverCam = 1 + Math.min(leftBestCover, left.no) + Math.min(rightBestCover, right.no);
        return new Options(dontCover, coverNoCam, coverCam);
    }

    // This level is not covered but everthing below is
    // This level is covered but there is no cam
    // This level is covered also cam is there
    public record Options(int no, int noCam, int cam) {
    };

    // Greedy]
    HashSet<TreeNode> covered = new HashSet<TreeNode>();
    int total = 0;

    public int minCameraCover2(TreeNode root) {
        covered.add(null);
        cover(root, null);
        return total;
    }

    public void cover(TreeNode node, TreeNode parent) {
        if (node == null)
            return;
        cover(node.left, node);
        cover(node.right, node);
        if (!covered.contains(node.left) || !covered.contains(node.right)) {
            total++;
            covered.add(node.left);
            covered.add(node.right);
            covered.add(node);
            covered.add(parent);
        }
        if (!covered.contains(node) && parent == null) {
            total++;
            covered.add(node);
        }
    }
}
