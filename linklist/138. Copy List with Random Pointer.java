/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 */

 /**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 
  __________
 |          |random
 1 -> 2 -> 3 -> 4 ...
 
 step1: we need to make the list to be:
   __________________
 |                   |random
 1 -> 1`-> 2 -> 2`-> 3 -> 3`-> 4-> 4`...
 
 step2: copy the random pointer: cur.next.random = cur.random.next;
 _____________________
 |                   |random
 1 -> 1`-> 2 -> 2`-> 3 -> 3`-> 4-> 4`...
 cur  |___________________|
 
 step3 : split list to two list
 
 */
public class Solution {
  public RandomListNode copyRandomList(RandomListNode head) {
      if(head == null)  return null;
      RandomListNode cur = head;
      // Step1
      while(cur != null) {
          // Step1
          RandomListNode next = cur.next;
          RandomListNode curCopy = new RandomListNode(cur.label);
          cur.next = curCopy;
          curCopy.next = next;
          cur = curCopy.next;  /// move cur
      }
      // Step 2
      cur = head;
      while(cur != null) {
          cur.next.random = cur.random == null ? null : cur.random.next;
          cur = cur.next.next;
      }
      
      //split list to two list
      cur = head;
      RandomListNode copyHead = cur.next, copy = copyHead;
      while(cur != null) {
          cur.next = copy.next;
          cur = cur.next;
          copy.next = cur == null ? null : cur.next;
          copy = copy.next;
      }
      return copyHead;
  }
}