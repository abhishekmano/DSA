package Solutions.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class FindPermutation {
    // 484. Find Permutation
    // https://leetcode.com/problems/find-permutation
    public int[] findPermutation(String s) {
        int n = s.length();
        int[] res = new int[n + 1];
        int index = 0;
        Deque<Integer> stack = new ArrayDeque();
        for (int idx = 1; idx <= n; ++idx) {
            if (s.charAt(idx - 1) == 'I') {
                stack.addFirst(idx);
                while (stack.size() != 0) {
                    res[index++] = stack.removeFirst();
                }
            } else {
                stack.addFirst(idx);
            }
        }
        stack.addFirst(n + 1);
        while (stack.size() != 0) {
            res[index++] = stack.removeFirst();
        }
        return res;
    }

    // 2375. Construct Smallest Number From DI String
    // https://leetcode.com/problems/construct-smallest-number-from-di-string/
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        StringBuilder result = new StringBuilder();
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for (int idx = 1; idx <= n; ++idx) {
            char ch = pattern.charAt(idx - 1);
            // Time to add all elements
            if (ch == 'I') {
                stack.push(idx);
                while (!stack.isEmpty()) {
                    result.append((char) (stack.pop() + '0'));
                }
            }
            // just push to stack
            else {
                stack.push(idx);
            }
        }
        stack.push(n + 1);
        while (!stack.isEmpty()) {
            result.append((char) (stack.pop() + '0'));
        }
        return result.toString();

    }
}
