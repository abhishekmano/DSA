package Solutions.Stack;

import java.util.Deque;
import java.util.LinkedList;

//20. Valid Parentheses
//https://leetcode.com/problems/valid-parentheses
public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<Character>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty())
                    return false;
                char top = stack.pop();
                if (ch == ')' && top != '(')
                    return false;
                if (ch == '}' && top != '{')
                    return false;
                if (ch == ']' && top != '[')
                    return false;

            }
        }
        return stack.isEmpty();
    }
}
