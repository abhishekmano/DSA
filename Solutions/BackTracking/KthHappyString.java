package Solutions.BackTracking;

//https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/
//1415. The k-th Lexicographical String of All Happy Strings of Length n
public class KthHappyString {
    int K = 0;
    int current = 0;
    String result = "";
    char[] options = new char[] { 'a', 'b', 'c' };

    public String getHappyString(int n, int k) {
        K = k;
        generate(new StringBuilder(), n);
        return result;
    }

    public void generate(StringBuilder build, int n) {
        if (build.length() == n) {
            current++;
            if (current == K)
                result = build.toString();
            return;
        }
        for (int idx = 0; idx < 3; ++idx) {
            char ch = options[idx];
            char last = build.length() == 0 ? '0' : build.charAt(build.length() - 1);
            if (ch != last) {
                build.append(ch);
                generate(build, n);
                if (current >= K)
                    break;
                build.deleteCharAt(build.length() - 1);
            }
        }

    }
}
