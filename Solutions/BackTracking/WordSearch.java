package Solutions.BackTracking;

//79. Word Search
//https://leetcode.com/problems/word-search
public class WordSearch {
    // complexity is O(N⋅ 3^L) where L is the length of the word and N is number of
    // cells
    public boolean exist(char[][] board, String word) {
        for (int idx = 0; idx < board.length; ++idx) {
            for (int idy = 0; idy < board[0].length; ++idy) {
                if (findWord(board, word, idx, idy, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean findWord(char[][] board, String word, int x, int y, int index) {
        int m = board.length;
        int n = board[0].length;
        int len = word.length();
        if (index == len)
            return true;
        if (!isValid(x, y, m, n))
            return false;
        if (word.charAt(index) != board[x][y])
            return false;
        char ch = board[x][y];
        board[x][y] = '*';

        boolean result = findWord(board, word, x + 1, y, index + 1) || findWord(board, word, x - 1, y, index + 1)
                || findWord(board, word, x, y + 1, index + 1) || findWord(board, word, x, y - 1, index + 1);

        board[x][y] = ch;
        return result;
    }

    public boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }
}
