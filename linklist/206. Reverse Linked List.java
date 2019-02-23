/**
 * Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
         1->  2  ->3->4
        cur next
        
        cur.next= pre 
        pre = cur 
        cur=next
        null<-1   2->3->4
              pre cur
        null<-1 <-2  3->4
                 pre cur
        null<-1 <-2<-3   4
                    pre cur
        null<-1 <-2<-3 <-4
                         PRE IS THE NEW HEAD
 */
class Solution {
  public ListNode reverseList(ListNode head) {
      ListNode pre = null, cur = head;
      while(cur != null) {
          ListNode next= cur.next;
          cur.next = pre;
          pre =cur;
          cur= next;
      }
      return pre;
  }
}
