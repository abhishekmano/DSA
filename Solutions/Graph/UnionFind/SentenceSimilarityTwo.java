package Solutions.Graph.UnionFind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
//737. Sentence Similarity II
//https://leetcode.com/problems/sentence-similarity-ii
// We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].

// Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

// Return true if sentence1 and sentence2 are similar, or false if they are not similar.

// Two sentences are similar if:

// They have the same length (i.e., the same number of words)
// sentence1[i] and sentence2[i] are similar.
// Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
public class SentenceSimilarityTwo {
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length)
            return false;
        HashMap<String, Integer> nodeIndex = new HashMap<>();
        int count = 0;
        for (List<String> similar : similarPairs) {
            String a = similar.get(0);
            String b = similar.get(1);
            if (!nodeIndex.containsKey(a)) {
                nodeIndex.put(a, count);
                count++;
            }
            if (!nodeIndex.containsKey(b)) {
                nodeIndex.put(b, count);
                count++;
            }
        }
        UnionFind set = new UnionFind(count);
        for (List<String> similar : similarPairs) {
            String a = similar.get(0);
            String b = similar.get(1);
            int aIndex = nodeIndex.get(a);
            int bIndex = nodeIndex.get(b);
            set.union(aIndex, bIndex);
        }
        int n = sentence1.length;
        for (int idx = 0; idx < n; ++idx) {
            String a = sentence1[idx];
            String b = sentence2[idx];
            if (a.equals(b))
                continue;
            if (!nodeIndex.containsKey(a) || !nodeIndex.containsKey(b)) {
                return false;
            }
            int aIndex = nodeIndex.get(a);
            int bIndex = nodeIndex.get(b);
            if (!set.isConnected(aIndex, bIndex))
                return false;
        }
        return true;

    }

    public class UnionFind {
        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int idx = 0; idx < n; ++idx) {
                parent[idx] = idx;
            }
        }

        public int findParent(int a) {
            if (parent[a] == a)
                return a;
            return parent[a] = findParent(parent[a]);
        }

        public boolean union(int a, int b) {
            int pa = findParent(a);
            int pb = findParent(b);
            if (pa == pb)
                return false;
            if (size[pa] < size[pb]) {
                parent[pa] = pb;
                size[pb] += size[pa];
            } else {
                parent[pb] = pa;
                size[pa] += size[pb];
            }
            return true;
        }

        public boolean isConnected(int a, int b) {
            return findParent(a) == findParent(b);
        }
    }
}
