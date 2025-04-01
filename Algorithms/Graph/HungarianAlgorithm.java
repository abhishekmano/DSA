package Algorithms.Graph;

import java.util.Arrays;
//sample https://leetcode.com/problems/maximum-number-of-accepted-invitations/
//used for bipartite matching 
//There are m boys and n girls in a class attending an upcoming party.

// You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at most one invitation from a boy.

// Return the maximum possible number of accepted invitations.
public class HungarianAlgorithm {
    public int maximumInvitations(int[][] grid) {
        // we iteratively check if every boy can pick a girl or not
        // if a girl is picked by another guy check if the another guy can pick another
        // girl
        int m = grid.length;
        int n = grid[0].length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        int result = 0;
        for (int guy = 0; guy < m; ++guy) {
            boolean[] visited = new boolean[n];
            if (getMatch(guy, match, grid, visited)) {
                result++;
            }
        }
        return result;
    }

    public boolean getMatch(int guy, int[] match, int[][] grid, boolean[] visited) {
        for (int girl = 0; girl < grid[0].length; ++girl) {
            if (!visited[girl] && grid[guy][girl] == 1) {
                visited[girl] = true;
                if (match[girl] == -1 || getMatch(match[girl], match, grid, visited)) {
                    match[girl] = guy;
                    return true;
                }
            }
        }
        return false;
    }
}
