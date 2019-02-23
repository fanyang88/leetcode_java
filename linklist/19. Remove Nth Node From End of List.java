/**
 Given a linked list, remove the n-th node from the end of list and return its head.
Example:
Given linked list: 1->2->3->4->5, and n = 2.
After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Follow up:
Could you do this in one pass?
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 [1,2,3,4] ， n = 2，
 add a dummy head => [0,1,2,3,4]
 fast ->0, slow->0
 n = 2，fast goes 2 steps，fast ->2，slow ->0
 afterwards, fast, slow go at the same pace, 
 fast->3, slow->1
 fast->4, slow->2  fast.next= null, stop
 now slow point to the pervious node next to the node to be deleted
 */
class Solution {
  public ListNode removeNthFromEnd(ListNode head, int n) {
      // Add dummy head
      ListNode h = new ListNode(0);
      h.next = head;
      // This is the key.
      ListNode fast=h, slow=h;
      while(n > 0) {
          n--;
          fast = fast.next;
      }
      
      while(fast.next != null) {
          fast= fast.next;
          slow = slow.next;
      }
      slow.next = slow.next!=null ? slow.next.next : null;
     
      return h.next;
  }
}
