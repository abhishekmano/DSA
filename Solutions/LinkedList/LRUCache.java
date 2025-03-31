package Solutions.LinkedList;

import java.util.HashMap;

//146. LRU Cache
//https://leetcode.com/problems/lru-cache
public class LRUCache {
    ListNode head;
    ListNode tail;
    int capacity;
    HashMap<Integer, ListNode> map;

    public LRUCache(int capacity) {
        head = new ListNode(-1, -1);
        tail = new ListNode(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();

    }

    public int get(int key) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            remove(node);
            add(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            remove(node);
            node.value = value;
            add(node);
        } else {
            ListNode node = new ListNode(key, value);
            map.put(key, node);
            add(node);
            if (map.size() > capacity) {
                ListNode nodeToRemove = head.next;
                remove(nodeToRemove);
                map.remove(nodeToRemove.key);
            }
        }
    }

    public void add(ListNode node) {
        ListNode tailPrev = tail.prev;
        tailPrev.next = node;
        tail.prev = node;
        node.prev = tailPrev;
        node.next = tail;
    }

    public void remove(ListNode node) {
        ListNode left = node.prev;
        ListNode right = node.next;
        left.next = right;
        right.prev = left;
    }

    class ListNode {
        ListNode prev;
        ListNode next;
        int value;
        int key;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
