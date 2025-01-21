package Solutions.LinkedList;

//21. Merge Two Sorted Lists
//https://leetcode.com/problems/merge-two-sorted-lists
public class MergeSortedLinkedList {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode ptr1 = list1;
        ListNode ptr2 = list2;
        ListNode res = dummy;
        while (ptr1 != null || ptr2 != null) {
            int first = ptr1 != null ? ptr1.val : Integer.MAX_VALUE;
            int second = ptr2 != null ? ptr2.val : Integer.MAX_VALUE;
            if (first < second) {
                res.next = ptr1;
                ptr1 = ptr1.next;
            } else {
                res.next = ptr2;
                ptr2 = ptr2.next;
            }
            res = res.next;
            res.next = null;
        }
        return dummy.next;
    }
}
