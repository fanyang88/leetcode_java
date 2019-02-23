/**
 * Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?
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
  public boolean isPalindrome(ListNode head) {
      if(head==null || head.next == null)  return true;
      ListNode slow = head, fast = head, pre = null;
      while(fast != null && fast.next != null) {
          pre = slow;
          slow = slow.next;
          fast =fast.next.next;
      }
      // break into 2 linklist
      pre.next= null;
      
      ListNode l1= head;
      ListNode l2 = reverse(slow);
      
      while(l1!=null && l2!=null) {
          if(l1.val != l2.val)  return false;
          l1= l1.next;
          l2 = l2.next;
      }
      return true;
  }
  
  public ListNode reverse(ListNode head) {
      if(head == null) return head;
      ListNode newHead = null, cur = head, next, pre = null;
      while(cur != null) {
          //For the first time, assign newhead 
          if(cur != null) newHead = cur;
          next = cur.next;
          cur.next= pre;
          pre = cur;
          cur = next;
      }
      return newHead;
  }
}
