/**
 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:

Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5

Note:

Only constant extra memory is allowed.
You may not alter the values in the list's nodes, only nodes itself may be changed.
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 e.g: k=3
 1->2->3->4->5
 count=0, cur=1
 count=1, cur=2
 count=2, cur=3
 count=3, cur=4
    recusive call: count=0 cur=4
                   count=1 cur=5
                   since count!=k, return head is 4 which is cur in currrent layer
    now: 1->2->3->4->5  => 3->2->1->4->5
         |        |
         head     cur
                   now reverse list  
                   2->3   1->4
                   | .    |
                   head .cur
                   
                   3 .  2->1->4
                   | .  |
                   head cur
                
                   temp=null
                   3->2->1->4
                   |
                   cur .  
    
    we go through once, so it is O(n)
 */
class Solution {
  public ListNode reverseKGroup(ListNode head, int k) {
      int count = 0;
      ListNode cur = head;
      while(cur != null && count != k) {
          count++;
          cur = cur.next;
      }
      if(count == k) { 
          // continue to next k part of the list, so we need to recursive call
          cur = reverseKGroup(cur, k);
          // reverse the linklist from head to cur
          while(count > 0) {
              count --;
              ListNode temp = head.next;
              head.next= cur;
              cur = head;
              head = temp;
          }
          head =cur;
      }
      return head;
  }
}