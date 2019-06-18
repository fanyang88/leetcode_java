/**
 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

/*
Method 2: priorityQueue
e.g: we have 1->2->4, 4->5->7, 3->4->6
1. the queue would sort all nodes in ASC, so we first put all lists into Q
    1->2->4
    3->4->6
    4->5->7
2. each time we get the smallest node from Q using q.poll();
   e.g: cur = 1, since cur.next=2, we put it back to q:
   2->4
   3->4->6
   4->5->7

*/
class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
      if(lists == null || lists.length==0 )  return null;
      ListNode dummy = new ListNode(0), cur = dummy;
      PriorityQueue<ListNode> q = new PriorityQueue<>(lists.length, (a, b)-> a.val - b.val);
      // 1. put all list head into Q
      for(ListNode list: lists) {
          if(list != null) q.add(list);
      }
      // 2. get the smallest node from queue and put the rest back
      while(!q.isEmpty()) {
          cur.next = q.poll(); 
          cur = cur.next; // cur point to 1
          if(cur.next != null) {
              q.add(cur.next);
          }
      }
      return dummy.next;
  }
}