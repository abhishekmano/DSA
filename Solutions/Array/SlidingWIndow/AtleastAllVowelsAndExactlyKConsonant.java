package Solutions.Array.SlidingWIndow;

//https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii
//3306. Count of Substrings Containing Every Vowel and K Consonants II
public class AtleastAllVowelsAndExactlyKConsonant {
    public long countOfSubstrings(String word, int k) {
        long second = atleastK(word, k + 1);
        long first = atleastK(word, k);
        return first - second;
    }

    public long atleastK(String word, int k) {
        int[] count = new int[26];
        int vowCount = 0;
        int consCount = 0;
        int right = 0;
        int n = word.length();
        long res = 0;
        for (int left = 0; left < n; ++left) {
            while (right < n && (vowCount < 5 || consCount < k)) {
                char ch = word.charAt(right);
                count[ch - 'a']++;
                if (count[ch - 'a'] == 1) {
                    if (isVowel(ch)) {
                        vowCount++;
                    }
                }
                if (!isVowel(ch))
                    consCount++;
                right++;
            }
            if (vowCount == 5 && consCount >= k) {
                res += (long) (n - right + 1);
            }
            char ch = word.charAt(left);
            count[ch - 'a']--;
            if (count[ch - 'a'] == 0) {
                if (isVowel(ch)) {
                    vowCount--;
                }
            }
            if (!isVowel(ch))
                consCount--;

        }
        return res;
    }

    public boolean isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }
}
