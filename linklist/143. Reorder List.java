/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 Find the middle of the list
 Reverse the half after middle  1->2->3->4->5->6 to 1->2->3->6->5->4
  start merge 
  
  1->2->3->4->5->6
  firstEnd => null slow => 1 fast=>1
  firstEnd=>1 slow => 2 fast=>2 fast=>3 
  firstEnd=>2 slow => 3 fast=>4 fast=>5 
  firstEnd=>3 slow => 4 fast=>6 fast=>null
  firstEnd point to the last one in first half
  slow point to the first one in second half
  
  make firstEnd.next = null, break into 2 linkedlist . 1->2->3  & 4->5->6
  reverse 4->5->6
  cur = slow, pre = null;
  cur=> 4 next=>5 cur.next = pre = null 4->null pre=>4 cur=>5
  cur=>5 next=>6 cur.next=pre=> 5->4->null pre=5 cur=6
  cur=>6 next=>null cur.next=pre=> 6->5->4->null pre=6 cur=null stop
  
  after we get the two linkedlist, we just need to merge them
  cur1 = head, cur2 = pre
  1->6->2   cur2=>5
       cur1
  1->6->2->5->3 . cur2=>4
             cur1                since cur1.next=null and cur2.next= null stop loop
  dont forget the last step cur1.next = cur2 to link 3=>4 THE KEY!!!
  1->6->2->5->3->4 
 */
class Solution {
  public void reorderList(ListNode head) {
      if(head==null || head.next == null)  return;
      // find the firstEnd
      ListNode slow= head, fast= head, firstEnd = null;
      while(fast != null && fast.next != null) {
          firstEnd = slow;
          fast = fast.next.next;
          slow = slow.next;
      }
      // break to 2 linkedlist
      firstEnd.next = null;
      
      // reverse second half
      ListNode cur =slow, pre =null;
      while(cur != null) {
          ListNode next = cur.next;
          cur.next = pre;
          pre = cur;
          cur = next;
      }
      
      // merge two linkedlist
      ListNode cur1=head, cur2= pre;
      while(cur1.next != null & cur2.next != null) {
          ListNode next1 = cur1.next;
          ListNode next2 = cur2.next;
          cur1.next = cur2;
          cur2.next = next1;
          cur1 = next1;
          cur2 = next2;
      }
      cur1.next = cur2;
  }
}
