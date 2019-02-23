/**
 Given a linked list and a value x, 
 partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
  public ListNode partition(ListNode head, int x) {
      ListNode l1= new ListNode(0), l2 = new ListNode(0);
      ListNode cur = head, p1= l1, p2=l2;
      while(cur != null) {
          if(cur.val < x) {
              p1.next = cur;
              p1 = p1.next;
          } else {
              p2.next = cur;
              p2 = p2.next;
          }
          cur = cur.next;
      }
      p2.next = null;
      p1.next = l2.next;
      return l1.next;
  }
}
