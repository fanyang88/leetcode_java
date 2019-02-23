/**
 Merge two sorted linked lists and return it as a new list. 
 The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
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
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode n1 = l1, n2 = l2, head = new ListNode(0), cur = head;
      while(n1 !=null || n2 !=null) {
          if(n2 == null || (n1!=null && n2!=null && n1.val < n2.val)) {
              cur.next = n1;
              n1 = n1.next;
          } else  {
              cur.next = n2;
              n2 = n2.next;
          }
          cur = cur.next;
      }
      return head.next;
  }
}