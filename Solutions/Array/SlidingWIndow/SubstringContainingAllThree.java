package Solutions.Array.SlidingWIndow;

//1358. Number of Substrings Containing All Three Characters
//https://leetcode.com/problems/number-of-substrings-containing-all-three-characters
public class SubstringContainingAllThree {
    public int numberOfSubstrings(String s) {
        int[] count = new int[3];
        int total = 0;
        int unique = 0;
        int right = 0;
        int n = s.length();
        for (int left = 0; left < s.length(); ++left) {
            while (right < n && unique < 3) {
                char ch = s.charAt(right);
                count[ch - 'a']++;
                if (count[ch - 'a'] == 1)
                    unique++;
                right++;
            }
            if (unique == 3)
                total += (n - right + 1);
            char ch = s.charAt(left);
            count[ch - 'a']--;
            if (count[ch - 'a'] == 0)
                unique--;

        }
        return total;
    }
}
