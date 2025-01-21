package Solutions.LinkedList;

//19. Remove Nth Node From End of List
//https://leetcode.com/problems/remove-nth-node-from-end-of-list
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = head;
        for (int idx = 1; idx <= n; ++idx) {
            fast = fast.next;
        }
        ListNode slow = dummy;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
