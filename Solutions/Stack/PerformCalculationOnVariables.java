package Solutions.Stack;

import java.util.HashMap;
import java.util.Stack;

//Question - Given a formula of letters with parentheses, remove all parentheses from the formula.
// Examples:
// a-(b+c) -> a-b-c
// a-(a-b) -> b
public class PerformCalculationOnVariables {
    public static void main(String[] args) {
        operate("a+b-a+c+(-b+c)-(+b - (a-b))");
        operate("a+a+a+a-(b+c)");
    }

    public static String operate(String input) {
        Stack<Integer> op = new Stack<Integer>();
        HashMap<Character, Integer> counter = new HashMap<>();
        int n = input.length();
        Integer currentOp = 1;
        Integer sign = 1;
        for (int idx = 0; idx < n; ++idx) {
            char ch = input.charAt(idx);
            switch (ch) {
                case '-':
                    sign = -1;
                    break;
                case '+':
                    sign = 1;
                    break;
                case ')':
                    currentOp = op.pop();
                    break;
                case '(':
                    op.push(currentOp);
                    if (sign == -1) {
                        currentOp = -1 * currentOp;
                        sign = 1;
                    }
                    break;
                default:
                    int currentSign = sign * currentOp;
                    int count = counter.getOrDefault(ch, 0);
                    // System.out.println(currentSign + " " + ch);
                    count += currentSign;
                    counter.put(ch, count);
            }
        }
        StringBuilder result = new StringBuilder();
        // System.out.println(counter);
        for (Character ch : counter.keySet()) {
            int count = counter.get(ch);
            if (count > 0) {
                if (result.length() != 0) {
                    result.append("+");
                }
                if (count != 1)
                    result.append(count);
            } else if (count < 0) {
                if (count == -1)
                    result.append('-');
                else
                    result.append(count);
            }
            if (count != 0)
                result.append(ch);
        }
        System.out.println(result);
        return result.toString();
    }
}
