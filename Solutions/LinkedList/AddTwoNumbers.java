package Solutions.LinkedList;

//2. Add Two Numbers
//https://leetcode.com/problems/add-two-numbers
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode(-1);
        ListNode ptr = sum;
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        int carry = 0;
        while (ptr1 != null || ptr2 != null || carry != 0) {
            int first = ptr1 != null ? ptr1.val : 0;
            int second = ptr2 != null ? ptr2.val : 0;
            int result = first + second + carry;
            carry = result / 10;
            int digit = result % 10;
            ListNode node = new ListNode(digit);
            ptr.next = node;
            ptr = ptr.next;
            if (ptr1 != null)
                ptr1 = ptr1.next;
            if (ptr2 != null)
                ptr2 = ptr2.next;

        }
        return sum.next;

    }
}
