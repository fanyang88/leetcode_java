/*
You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 use stack
 */
class Solution {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
      int carry=0;
      Stack<Integer> s1 = new Stack<>();
      Stack<Integer> s2 = new Stack<>();
      while(l1!=null) {
          s1.push(l1.val);
          l1 = l1.next;
      }
      while(l2!=null) {
          s2.push(l2.val);
          l2 = l2.next;
      }
      ListNode head = new ListNode(1);
      while(s1.size() > 0 || s2.size() > 0) {
          int val1 = s1.size()==0 ? 0 : s1.pop();
          int val2 = s2.size()==0 ? 0 : s2.pop();
          ListNode node = new ListNode((val1+val2+carry)%10);
          carry = (val1+val2+carry) / 10;
          node.next = head.next;
          head.next = node; 
      }
      return carry > 0 ? head : head.next;
  }
}
