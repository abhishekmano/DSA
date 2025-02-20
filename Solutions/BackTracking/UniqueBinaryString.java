package Solutions.BackTracking;

import java.util.HashSet;

public class UniqueBinaryString {
    // using Cantor's Diagonal Argument
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder build = new StringBuilder();
        int len = nums[0].length();
        for (int idx = 0; idx < len; ++idx) {
            char ch = nums[idx].charAt(idx);
            if (ch == '0')
                build.append('1');
            else
                build.append('0');
        }
        return build.toString();
    }

    // Using Backtracking and generating every string
    HashSet<Integer> existing;
    String result = "";

    public String findDifferentBinaryString2(String[] nums) {
        existing = new HashSet<Integer>();
        for (String num : nums) {
            int actualNumber = getNumberFromString(num);
            existing.add(actualNumber);
        }
        generate(new StringBuilder(), nums[0].length());
        return result;
    }

    public boolean generate(StringBuilder build, int len) {
        if (build.length() == len) {
            String constructed = build.toString();
            int actual = getNumberFromString(constructed);
            if (!existing.contains(actual)) {
                result = constructed;
                return true;
            }
            return false;
        }
        build.append('0');
        if (generate(build, len))
            return true;
        build.deleteCharAt(build.length() - 1);
        build.append('1');
        if (generate(build, len))
            return true;
        build.deleteCharAt(build.length() - 1);
        return false;
    }

    public int getNumberFromString(String binary) {
        int result = 0;
        for (char ch : binary.toCharArray()) {
            result *= 2;
            if (ch != '0') {
                result++;
            }
        }
        return result;
    }
}
