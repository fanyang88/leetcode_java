/**
 Given a linked list, swap every two adjacent nodes and return its head.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the list's nodes, only nodes itself may be changed.
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
0->1->2->3->4
 |     ^
 |     |
 ------|
 p  c  n  np
 
 => 0->2->1->3->4
          |  |
          p  c
   p->next = n
   n(2)->next = c(1)
   c.next= nextP
*/
class Solution {
  public ListNode swapPairs(ListNode head) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode pre = dummy, cur = head;
      while(cur!=null && cur.next!=null) {
          ListNode next = cur.next;
          ListNode nextP = next.next;
          pre.next = next;
          next.next = cur;
          cur.next= nextP;
          pre = cur;
          cur = nextP;
      }
      return dummy.next;
  }
}