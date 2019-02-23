/*
Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 
 1->2->3->4->5->NULL
 odd=1 even=2
 oddNext= 3 evenNext= 4 
  1->3 2->4  odd.next = oddNext even.next = evenNext odd => 3 even=>4
  oddNext= 5 evenNext= null
  1->3->5 2->4->null  odd => 5 even=>null
  odd->evenHead=2
 */
class Solution {
  public ListNode oddEvenList(ListNode head) {
      if(head == null || head.next ==null)  return head;
      ListNode odd = head, even = head.next, evenHead =head.next;
      while(even != null && even.next != null) {
          ListNode oddN = odd.next.next;
          ListNode evenN = even.next.next;
          odd.next = oddN;
          even.next = evenN;
          
          even = even.next;
          odd = odd.next;
      }
      odd.next = evenHead;
      return head;
  }
}