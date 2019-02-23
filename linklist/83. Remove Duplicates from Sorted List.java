/**
 Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
     -1->1->1->2->3->3
      p  c
            c
      p.next = c  p=p.next c= c.next
    -1->1->2->3->3
        p  c 
    since p.next =c move p and c, p=p.next c= c.next
    -1->1->2->3->3
           p     c
    p.next = c  p=p.next c= c.next
    -1->1->2->3
              p  c
 */
class Solution {
  public ListNode deleteDuplicates(ListNode head) {
      ListNode dummy = new ListNode(-1);
      dummy.next = head;
      ListNode pre= dummy, cur = head;
      while(cur != null) {
          while(cur.next != null && cur.val == cur.next.val) {
              cur = cur.next;
          }
          if(pre.next!= cur) {
              pre.next = cur; // cur point to the last dup
          } 
          pre = pre.next;
          cur = cur.next;
      }
      return dummy.next;
  }
}
