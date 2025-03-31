package Solutions.SegmentTree;

//2407. Longest Increasing Subsequence II
//https://leetcode.com/problems/longest-increasing-subsequence-ii
public class LongestIncreasingSubSequenceTwo {
    public int lengthOfLIS(int[] nums, int k) {
        // 10^5
        Node segment = buildTree(0, 100001);
        int result = 1;
        for (int num : nums) {
            int max = getMax(segment, Math.max(0, num - k), num - 1) + 1;
            result = Math.max(result, max);
            update(segment, num, max);
        }
        return result;
    }

    Node buildTree(int start, int end) {
        if (start == end) {
            return new Node(start, end, 0);
        }
        Node node = new Node(start, end, 0);
        int mid = (start) + (end - start) / 2;
        node.left = buildTree(start, mid);
        node.right = buildTree(mid + 1, end);
        return node;
    }

    public int getMax(Node node, int left, int right) {
        if (node == null || node.end < left || node.start > right) {
            return 0;
        }
        // check completely inside;
        if (node.start >= left && node.end <= right) {
            return node.val;
        }
        return Math.max(getMax(node.left, left, right), getMax(node.right, left, right));
    }

    public void update(Node node, int index, int value) {
        if (node == null || node.end < index || node.start > index) {
            return;
        }
        node.val = Math.max(node.val, value);
        if (node.start != node.end) {
            update(node.left, index, value);
            update(node.right, index, value);
        }
    }

    class Node {
        Node left;
        Node right;
        int start;
        int end;
        int val;

        public Node(int start, int end, int val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
}
