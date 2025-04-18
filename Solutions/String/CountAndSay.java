package Solutions.String;

//38. Count and Say
//https://leetcode.com/problems/count-and-say
public class CountAndSay {
    public String countAndSay(int n) {
        String res = "1";
        for (int idx = 1; idx < n; ++idx) {
            res = countAndSay(res);
        }
        return res;
    }

    public String countAndSay(String num) {
        StringBuilder result = new StringBuilder();
        char prev = num.charAt(0);
        int count = 1;
        for (int idx = 1; idx <= num.length(); ++idx) {
            char curr = ' ';
            if (idx != num.length())
                curr = num.charAt(idx);
            if (curr != prev) {
                result.append(count);
                result.append(prev);
                prev = curr;
                count = 1;
            } else {
                count++;
            }
        }
        return result.toString();
    }
}
