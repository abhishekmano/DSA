package Solutions.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//49. Group Anagrams
//https://leetcode.com/problems/group-anagrams
public class GroupAnagram {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            String hash = getCountString(s);
            map.computeIfAbsent(hash, k -> new ArrayList<String>()).add(s);
        }
        return new ArrayList(map.values());
    }

    public String getCountString(String s) {
        int[] counter = new int[26];
        for (char ch : s.toCharArray()) {
            counter[ch - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int count : counter) {
            res.append(count);
            res.append("#");
        }
        return res.toString();
    }
}
