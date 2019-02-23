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
Method 1: divide and conquer
e.g: l1: 1,2,3,4
     l2:5,6,7,8
     l3:9,10
     l4:11,12
     we can merge l1, l2->m, merge l3, l4->n
     then merge m and n which is the answer.

     TimeComplexity: O(nlogk) k stands for how many link list, n stands for the number of node in each list
*/

class Solution {
  public ListNode mergeKLists(ListNode[] lists) {
      if(lists == null || lists.length==0 )  return null;
      return sort(lists, 0, lists.length-1);
  }
  
  public ListNode sort(ListNode[] lists, int lo, int hi) {
      if(lo >= hi) return lists[lo];
      int mid = lo + (hi - lo)/2;
      ListNode l1= sort(lists, lo, mid);
      ListNode l2= sort(lists, mid+1, hi);
      return merge(l1, l2);
  }
  
  public ListNode merge(ListNode l1, ListNode l2) {
      if(l1 == null) return l2;
      if(l2 == null) return l1;
      if(l1.val < l2.val) {
          l1.next = merge(l1.next, l2);
          return l1;
      } else {
          l2.next = merge(l1, l2.next);
          return l2;
      }
  }
}