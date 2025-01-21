package Solutions.LinkedList;

//876. Middle of the Linked List
//https://leetcode.com/problems/middle-of-the-linked-list
public class MiddleOfLinkedList {
    // [1 2 3 4 5 ] gives 3
    // [1 2 3 4 5 6] gives 4
    // for odd gives the middle for even gives the second middle
    public ListNode middleNode(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // [1 2 3 4 5 ] gives 3
    // [1 2 3 4 5 6] gives 3
    // gives mid node for both for even gives the first mid
    public ListNode middleNode2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // [1 2 3 4 5 ] gives 2
    // [1 2 3 4 5 6] gives 3
    // gives one node before mid
    public ListNode middleNode3(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
