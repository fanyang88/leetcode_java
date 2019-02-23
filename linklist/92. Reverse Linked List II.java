/**
 Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 1 - 2 - 3 - 4 - 5
 |   |       |
 p   m .     n
 we need to insert m node to after n node, keep n node point to 4, m node then move to previous m node next
 1  3 - 4 - 2 - 5
 |  |   |
 p  m . n
we need to insert m node to after n node, keep n node point to 4, m node then move to previous m node next
 1  - 4 - 2 - 3 - 5
 |    |
 p   n,m
once m and n meet, we stop



 */
class Solution {
  public ListNode reverseBetween(ListNode head, int m, int n) {
      ListNode dummy= new ListNode(0);
      dummy.next= head;
      ListNode pre= null, mNode= dummy, nNode =dummy;
      int i=0;
      while(i++<m) {
          pre = mNode;
          nNode = nNode.next;
          mNode = mNode.next;
      }
      while(i++<=n) {  // <=n, this is the KEY
          nNode = nNode.next;
      }
      while(mNode != nNode) {
          ListNode mNext = mNode.next;
          pre.next = mNext;
          ListNode nNext = nNode.next;
          nNode.next = mNode;
          mNode.next = nNext;
          mNode = mNext;
      }
      return dummy.next;
  }
}
