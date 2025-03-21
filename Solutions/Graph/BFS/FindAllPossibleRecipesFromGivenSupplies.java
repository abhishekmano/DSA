package Solutions.Graph.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//2115. Find All Possible Recipes from Given Supplies
//https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies
public class FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        HashSet<String> supplySet = new HashSet<String>();
        HashSet<String> recipieSet = new HashSet<String>();
        HashMap<String, Integer> indegree = new HashMap();
        Queue<String> bfs = new LinkedList();
        for (String supply : supplies) {
            supplySet.add(supply);
            bfs.add(supply);
        }
        List<String> result = new ArrayList();

        HashMap<String, List<String>> graph = new HashMap();
        for (int idx = 0; idx < recipes.length; ++idx) {
            String currentRecipe = recipes[idx];
            recipieSet.add(currentRecipe);
            indegree.put(currentRecipe, ingredients.get(idx).size());
            for (String ingredient : ingredients.get(idx)) {
                if (!graph.containsKey(ingredient)) {
                    graph.put(ingredient, new ArrayList());
                }
                graph.get(ingredient).add(currentRecipe);
            }
        }

        while (bfs.size() != 0) {
            String top = bfs.remove();
            if (!supplySet.contains(top)) {
                result.add(top);
            }
            if (graph.containsKey(top)) {
                for (String adj : graph.get(top)) {
                    if (indegree.containsKey(adj)) {
                        indegree.put(adj, indegree.get(adj) - 1);
                        if (indegree.get(adj) == 0) {
                            bfs.add(adj);
                        }
                    }
                }
            }
        }
        return result;
    }
}
