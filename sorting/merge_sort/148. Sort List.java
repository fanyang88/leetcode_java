/**
 * Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 *//**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
     step 1. cut the list to two halves
     step 2. sort each half
     step 3. merge them
 */

class Solution {
  public ListNode sortList(ListNode head) {
      if(head == null || head.next == null) return head;
      ListNode slow = head, fast= head, pre = null;
      while(fast != null && fast.next != null) {
          pre = slow;
          fast = fast.next.next;
          slow =slow.next;
      }
      // pre point to the last one in first half, slow point to the first one in second half
      pre.next = null; //break into 2 half
      ListNode l1 = sortList(head);
      ListNode l2 = sortList(slow);
      return merge(l1, l2);
  }
  
  public ListNode merge(ListNode h1, ListNode h2) {
      ListNode dummy = new ListNode(0);
      ListNode cur = dummy, cur1 = h1, cur2 = h2;
      while(cur1 != null || cur2 != null) {
          if(cur2 == null || (cur1 != null && cur1.val < cur2.val)) {
              cur.next = cur1;
              cur1= cur1.next;
          } else  {
              cur.next = cur2;
              cur2= cur2.next;
          } 
          cur = cur.next;
      }
      return dummy.next;
  }
}