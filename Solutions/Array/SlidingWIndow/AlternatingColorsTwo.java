package Solutions.Array.SlidingWIndow;

//3208. Alternating Groups II
//https://leetcode.com/problems/alternating-groups-ii/
public class AlternatingColorsTwo {
    // Idea is to keep track of valid window and when violation comes make window
    // size 1
    // anu time windw size >= k increase the count
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int count = 0;
        int n = colors.length;
        int lastColor = colors[0];
        int running = 1;
        for (int idx = 1; idx < n + k - 1; ++idx) {
            int index = idx % n;
            if (colors[index] == lastColor) {
                running = 1;
            } else {
                running++;
            }
            if (running >= k)
                count++;
            lastColor = colors[index];
        }
        return count;
    }
}
