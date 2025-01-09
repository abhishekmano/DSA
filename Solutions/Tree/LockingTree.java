package Solutions.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
//1993. Operations on Tree
//https://leetcode.com/problems/operations-on-tree/description
public class LockingTree {
    HashMap<Integer,TreeNode> map = new HashMap(); 
    HashMap<Integer,Integer> locked = new HashMap();
    public LockingTree(int[] parent) {
        int n = parent.length;
        for(int idx = 0 ; idx < n ; ++idx){
            TreeNode node = new TreeNode(idx);
            map.put(idx , node);
        }
        for(int idx = 0 ; idx < n ; ++idx){
            int parentNode = parent[idx];
            if(parentNode == -1) continue;
            map.get(parentNode).children.add(map.get(idx));
            map.get(idx).parent = map.get(parentNode);
        }
    }
    
    public boolean lock(int num, int user) {
        if(locked.containsKey(num)) return false;
        locked.put(num, user);
        return true;
    }
    
    public boolean unlock(int num, int user) {
        if(!locked.containsKey(num)) return false;
        if(locked.get(num) == user){
            locked.remove(num);
            return true;
        }
        return false;
    }
    
    public boolean upgrade(int num, int user) {
        if(locked.containsKey(num)) return false;
        TreeNode node = map.get(num);
        TreeNode parent = node.parent;
        while(parent!=null){
            if(locked.containsKey(parent.val)) return false;
            parent = parent.parent;
        }
        boolean lockedChild = false;
        Queue<TreeNode> bfs = new LinkedList();
        bfs.add(node);
        while(bfs.size()!=0){
            var top = bfs.remove();
            if(locked.containsKey(top.val)){
                lockedChild = true;
                locked.remove(top.val);
            }
            for(TreeNode child : top.children){
                bfs.add(child);
            }
        }
        if(lockedChild){
            locked.put(num, user);
            return true;
        }
        else{
            return false;
        }
    }
    
}
class TreeNode{
    int val ;
    TreeNode parent;
    List<TreeNode> children;
    public TreeNode(int value){
        val = value;
        children = new ArrayList();
    }
}
