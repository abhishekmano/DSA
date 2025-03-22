package Solutions.Graph.TopologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//3481. Apply Substitutions
//https://leetcode.com/problems/apply-substitutions
public class ApplySubstitution {
    // Idea is to find topological sort to find non dependant replacements
    // incrementally replace these
    // at the end replace the sample text which is given
    public String applySubstitutions(List<List<String>> replacements, String text) {
        HashMap<String, String> mapping = new HashMap<>();
        for (List<String> map : replacements) {
            mapping.put(map.get(0), map.get(1));
        }
        HashMap<String, HashSet<String>> edges = new HashMap<>();
        HashMap<String, Integer> indegree = new HashMap<>();
        // construct the graph
        for (String key : mapping.keySet()) {
            String value = mapping.get(key);
            StringBuilder otherKey = new StringBuilder();
            boolean started = false;
            for (char ch : value.toCharArray()) {
                if (ch == '%') {
                    if (started) {
                        String dep = otherKey.toString();
                        if (!edges.getOrDefault(dep, new HashSet<String>()).contains(key)) {
                            indegree.put(key, indegree.getOrDefault(key, 0) + 1);
                        }
                        edges.computeIfAbsent(dep, k -> new HashSet<String>()).add(key);

                        started = false;
                        otherKey = new StringBuilder();
                    } else {
                        started = true;
                    }
                } else {
                    if (started)
                        otherKey.append(ch);
                }
            }
        }
        Queue<String> bfs = new LinkedList<String>();
        for (String key : mapping.keySet()) {
            if (indegree.getOrDefault(key, 0) == 0)
                bfs.offer(key);
        }
        while (bfs.size() != 0) {
            String top = bfs.poll();
            for (String next : edges.getOrDefault(top, new HashSet<String>())) {
                String repl = mapping.get(next);
                repl = repl.replaceAll('%' + top + '%', mapping.get(top));
                mapping.put(next, repl);
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    bfs.add(next);
                }
            }
        }
        StringBuilder otherKey = new StringBuilder();
        boolean started = false;
        StringBuilder response = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch == '%') {
                if (started) {
                    String dep = otherKey.toString();
                    response.append(mapping.get(dep));
                    started = false;
                    otherKey = new StringBuilder();
                } else {
                    started = true;
                }
            } else {
                if (started)
                    otherKey.append(ch);
                else
                    response.append(ch);
            }
        }
        return response.toString();
    }
}
