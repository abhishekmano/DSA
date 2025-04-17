package Solutions.Trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//212. Word Search II
//https://leetcode.com/problems/word-search-ii
public class WordSearchTwo {
    public Trie tree = new Trie();
    HashSet<String> result = new HashSet<String>();
    int m;
    int n;

    public List<String> findWords(char[][] board, String[] words) {
        m = board.length;
        n = board[0].length;
        for (String word : words) {
            tree.addWord(word);
        }
        for (int idx = 0; idx < m; ++idx) {
            for (int idy = 0; idy < n; ++idy) {
                DFS(idx, idy, board, tree.getRoot(), new StringBuilder());
            }
        }
        return new ArrayList<String>(result);
    }

    public void DFS(int x, int y, char[][] board, TrieNode node, StringBuilder word) {
        if (node == null)
            return;
        if (node.isWord)
            result.add(word.toString());
        if (x >= m || x < 0 || y < 0 || y >= n)
            return;
        char ch = board[x][y];
        if (ch == '#')
            return;
        word.append(ch);
        node = node.children[ch - 'a'];
        board[x][y] = '#';
        DFS(x + 1, y, board, node, word);
        DFS(x - 1, y, board, node, word);
        DFS(x, y + 1, board, node, word);
        DFS(x, y - 1, board, node, word);
        word.deleteCharAt(word.length() - 1);
        board[x][y] = ch;
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public TrieNode getRoot() {
            return root;
        }

        public void addWord(String word) {
            TrieNode ptr = root;
            for (char ch : word.toCharArray()) {
                if (ptr.children[ch - 'a'] == null) {
                    ptr.children[ch - 'a'] = new TrieNode();
                }
                ptr = ptr.children[ch - 'a'];
            }
            ptr.isWord = true;
        }

        public boolean hasWord(String word) {
            TrieNode ptr = root;
            for (char ch : word.toCharArray()) {
                if (ptr.children[ch - 'a'] == null) {
                    return false;
                }
                ptr = ptr.children[ch - 'a'];
            }
            return ptr.isWord;
        }
    }

    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        TrieNode() {
            children = new TrieNode[26];
            isWord = false;
        }
    }
}
