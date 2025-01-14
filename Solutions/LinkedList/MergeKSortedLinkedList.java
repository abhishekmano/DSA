package Solutions.LinkedList;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/merge-k-sorted-lists
//23. Merge k Sorted Lists
public class MergeKSortedLinkedList {
    // Runs in Nlogk
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(new ListNodeComparator());
        ListNode dummyHead = new ListNode(-1);
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }
        ListNode ptr = dummyHead;
        while (!pq.isEmpty()) {
            ListNode top = pq.remove();
            ptr.next = top;
            ptr = ptr.next;
            ListNode next = top.next;
            top.next = null;
            if (next != null)
                pq.add(next);
        }
        return dummyHead.next;
    }

    public class ListNodeComparator implements Comparator<ListNode> {
        public int compare(ListNode a, ListNode b) {
            return a.val - b.val;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
