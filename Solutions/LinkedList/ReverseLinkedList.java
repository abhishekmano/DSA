package Solutions.LinkedList;

//206. Reverse Linked List
//https://leetcode.com/problems/reverse-linked-list
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode ptr = head;
        ListNode prev = null;
        while (ptr != null) {
            ListNode next = ptr.next;
            ptr.next = prev;
            prev = ptr;
            ptr = next;

        }
        return prev;
    }

    // Using Recursion
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        ListNode reverse = reverseList2(head.next);
        next.next = head;
        head.next = null;
        return reverse;
    }
}
