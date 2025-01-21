package Solutions.LinkedList;

//143. Reorder List
//https://leetcode.com/problems/reorder-list
public class ReorderLinkedList {
    public void reorderList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }

        }
        // The last node slow is in the first half
        ListNode mid = slow.next;
        slow.next = null;
        ListNode reversedHead = reverse(mid);
        combine(dummy.next, reversedHead);

    }

    public ListNode combine(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode ptr1 = a;
        ListNode ptr2 = b;
        ListNode res = dummy;
        boolean first = true;
        while (ptr1 != null || ptr2 != null) {
            if (first) {
                res.next = ptr1;
                if (ptr1 != null)
                    ptr1 = ptr1.next;
            } else {
                res.next = ptr2;
                if (ptr2 != null)
                    ptr2 = ptr2.next;
            }
            if (res.next != null)
                res = res.next;
            first = !first;
        }
        return dummy.next;
    }

    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        ListNode reverse = reverse(head.next);
        next.next = head;
        head.next = null;
        return reverse;
    }
}
