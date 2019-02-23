/**
 Given a sorted linked list, delete all nodes that have duplicate numbers, 
 leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3

 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
  dummy-> 1->2->3->3->4->4->5
  pre    cur  
  since cur.val != cur.next.val && pre.next = cur pre=pre.next  cur = cur.next
  dummy-> 1->2->3->3->4->4->5
         pre cur  
  since cur.val != cur.next.val && pre.next = cur pre=pre.next  cur = cur.next
  dummy-> 1->2->3->3->4->4->5
            pre cur
                  cur
 pre.next = cur.next  cur = cur.next
 dummy-> 1->2-> 4->4->5
           pre cur 
           pre    cur
 since pre.next!=cur
 pre.next = cur.next  cur = cur.next
 dummy-> 1->2->5
           pre   cur 
 
 pre point to the last no dup element
 cur point to the next element could have dup
 
 */
class Solution {
  public ListNode deleteDuplicates(ListNode head) {
      ListNode dummy = new ListNode(-1);
      dummy.next = head;
      ListNode pre = dummy, cur = head;
      while(cur != null) {
          while(cur.next != null && cur.val == cur.next.val) {
              cur = cur.next;
          }
          if(pre.next == cur) {  // no dup, keep moving forward, this is the key
              pre = pre.next;
          } else { // otherwise, cur point to the last dup, pre link to the one after last dup
              pre.next = cur.next;
          }
          cur = cur.next;
      }
      return dummy.next;
  }
}