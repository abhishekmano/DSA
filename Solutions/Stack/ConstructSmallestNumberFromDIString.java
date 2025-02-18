package Solutions.Stack;

import java.util.Stack;

//2375. Construct Smallest Number From DI String
//https://leetcode.com/problems/construct-smallest-number-from-di-string
public class ConstructSmallestNumberFromDIString {
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        StringBuilder result = new StringBuilder();
        Stack<Integer> stack = new Stack<Integer>();
        for (int idx = 0; idx <= n; ++idx) {
            if (idx == n || pattern.charAt(idx) == 'I') {
                result.append((char) (idx + '1'));
                while (stack.size() != 0) {
                    int top = stack.pop();
                    result.append((char) (top + '1'));
                }
                // result.append((char)(idx + '1'));
            } else {
                stack.push(idx);
            }
        }
        return result.toString();

    }
}
