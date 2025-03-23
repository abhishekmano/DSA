package Solutions.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//556. Next Greater Element III
//https://leetcode.com/problems/next-greater-element-iii/
public class NextGreaterElementThree {
    public int nextGreaterElement(int n) {
        // 4532
        List<Integer> digits = new ArrayList<Integer>();
        while (n != 0) {
            digits.add(n % 10);
            n = n / 10;
        }
        // System.out.println(digits);
        int size = digits.size();
        int breaking = 0;
        for (int idx = 1; idx < size; ++idx) {
            if (digits.get(idx) < digits.get(idx - 1)) {
                breaking = idx;
                break;
            }
        }
        if (breaking == 0)
            return -1;
        // System.out.println("breaking is " + breaking);
        // swap digits at breaking and next largest
        for (int idx = 0; idx < breaking; ++idx) {
            if (digits.get(idx) > digits.get(breaking)) {
                int temp = digits.get(idx);
                digits.set(idx, digits.get(breaking));
                digits.set(breaking, temp);
                break;
            }
        }
        // System.out.println("after swapping " );
        // System.out.println(digits);
        // from 0 to breaking - 1 reverse everying
        int start = 0;
        int end = breaking - 1;
        while (start < end) {
            int temp = digits.get(start);
            digits.set(start, digits.get(end));
            digits.set(end, temp);
            start++;
            end--;
        }
        // convert back to number
        long result = 0;
        // least significant bit is at the start//
        // so either start from end or reverse it before processing
        Collections.reverse(digits);
        for (int digit : digits) {
            result = result * 10 + digit;
            if (result > Integer.MAX_VALUE)
                return -1;
        }
        return (int) result;
    }
}
