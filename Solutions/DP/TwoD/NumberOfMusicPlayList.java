package Solutions.DP.TwoD;

//920. Number of Music Playlists
//https://leetcode.com/problems/number-of-music-playlists
public class NumberOfMusicPlayList {
    // dp[x][y]
    // number of playlist of length x with y songs
    public int numMusicPlaylists(int n, int goal, int k) {
        int MOD = 100_000_0007;
        // goal is the length n is the total songs
        long[][] dp = new long[goal + 1][n + 1];
        // for length zero the possibility is always zero
        // except if k is 0
        dp[0][0] = 1;
        for (int idx = 1; idx <= goal; ++idx) {
            // now we have option to choose 1 to n unique songs
            // but if number of unique songs is more than idx then the possibility is zero
            // we cant form length idx with unique song more than idx
            for (int idy = 1; idy <= Math.min(n, idx); ++idy) {
                // if we choose a new song
                // options of length i-1 with j-1 unique * new options are (n - (j-1))
                dp[idx][idy] = (dp[idx - 1][idy - 1] * (n - idy + 1)) % MOD;
                // if we picked k songs already then we can reuse a song
                if (idy > k) {
                    dp[idx][idy] = (dp[idx][idy] + dp[idx - 1][idy] * (idy - k)) % MOD;
                }
            }
        }
        return (int) dp[goal][n];
    }
}
