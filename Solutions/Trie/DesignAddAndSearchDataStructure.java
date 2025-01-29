package Solutions.Trie;

//211. Design Add and Search Words Data Structure
//https://leetcode.com/problems/design-add-and-search-words-data-structure
//search can contain . which is a wild card matching
public class DesignAddAndSearchDataStructure {
    class WordDictionary {
        public Trie trie;

        public WordDictionary() {
            trie = new Trie();
        }

        public void addWord(String word) {
            trie.insert(word);
        }

        public boolean search(String word) {
            return findRecurse(word, 0, trie.root);
        }

        public boolean findRecurse(String word, int index, TrieNode node) {
            if (node == null)
                return false;
            if (index == word.length()) {
                return node.isEnd;
            }
            char ch = word.charAt(index);
            if (ch == '.') {
                for (int idx = 0; idx < 26; ++idx) {
                    if (findRecurse(word, index + 1, node.children[idx])) {
                        return true;
                    }
                }
            } else {
                return findRecurse(word, index + 1, node.children[ch - 'a']);
            }
            return false;
        }
    }

    class Trie {
        public final TrieNode root;

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
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */
}
