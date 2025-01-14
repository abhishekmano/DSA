package Solutions.TwoPointer;

//424. Longest Repeating Character Replacement
//https://leetcode.com/problems/longest-repeating-character-replacement
public class LongestRepeatingCharacterReplacement {
    // This answer might not be very intuitive since the max (maximum freq saw in a
    // window so far) is used continuously
    // but a window is valid for a max f then only a higher maxF can generate a new
    // result
    // so it should be fine
    public int characterReplacement(String s, int k) {
        int n = s.length();
        int left = 0;
        // this is the maximum frequency we have seen so far
        int max = 0;
        int result = 0;
        int[] count = new int[128];
        for (int idx = 0; idx < n; ++idx) {
            count[s.charAt(idx)]++;
            max = Math.max(count[s.charAt(idx)], max);
            while (idx - left + 1 - max > k) {
                count[s.charAt(left)]--;
                left++;
            }
            result = Math.max(result, idx - left + 1);
        }
        return result;
    }
}
