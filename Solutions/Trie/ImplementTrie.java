package Solutions.Trie;

//https://leetcode.com/problems/implement-trie-prefix-tree/
//208. Implement Trie (Prefix Tree)
public class ImplementTrie {
    class Trie {
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode tmp = root;
            for (char ch : word.toCharArray()) {
                if (tmp.children[ch - 'a'] == null) {
                    tmp.children[ch - 'a'] = new TrieNode();
                }
                tmp = tmp.children[ch - 'a'];
            }
            tmp.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode temp = root;
            for (char ch : word.toCharArray()) {
                if (temp.children[ch - 'a'] != null) {
                    temp = temp.children[ch - 'a'];
                } else
                    return false;
            }
            return temp.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode temp = root;
            for (char ch : prefix.toCharArray()) {
                if (temp.children[ch - 'a'] != null) {
                    temp = temp.children[ch - 'a'];
                } else
                    return false;
            }
            return true;
        }
    }

    class TrieNode {
        TrieNode[] children;
        boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}
