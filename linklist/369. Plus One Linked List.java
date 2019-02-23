/*
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example :

Input: [1,2,3]
Output: [1,2,4]
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 add a dummy head 0 and have two pointers i, j point to dummy
 j process to the end, and i point to the last digit not 9
 i increase 1 and change everything after i to be 0
 e.g: 999 -> 0999  i is 0, increase 1, => 1000
      12909 -> i point to 0, increase 1 => 12910
 */
class Solution {
  public ListNode plusOne(ListNode head) {
      ListNode dummy = new ListNode(0);
      ListNode i = dummy, j=dummy;
      dummy.next = head;
      while(j!=null) {
          if(j.val !=9) i=j;
          j = j.next;
      }
      // increment i.val and change to 0 for all the nodes after i
      i.val ++;
      i = i.next;
      while(i!= null) {
          i.val = 0;
          i = i.next;
      }
      return dummy.val == 0 ? dummy.next : dummy;
  }
}
