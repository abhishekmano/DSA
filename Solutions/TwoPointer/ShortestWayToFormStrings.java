package Solutions.TwoPointer;

//1055. Shortest Way to Form String
// /https://leetcode.com/problems/shortest-way-to-form-string
public class ShortestWayToFormStrings {
    public int shortestWay(String source, String target) {
        boolean[] set = new boolean[26];
        for (char ch : source.toCharArray()) {
            set[ch - 'a'] = true;
        }
        for (char ch : target.toCharArray()) {
            if (!set[ch - 'a'])
                return -1;
        }
        int count = 1;
        int sLen = source.length();
        int tLen = target.length();
        int sIndex = 0;
        for (int idx = 0; idx < tLen; ++idx) {
            while (sIndex < sLen && source.charAt(sIndex) != target.charAt(idx)) {
                sIndex++;
            }
            if (sIndex == sLen) {
                count++;
                sIndex = 0;
                idx--;
            } else {
                sIndex++;
            }
        }
        return count;

    }
}
