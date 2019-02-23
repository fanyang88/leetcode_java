/**
 * 
 * Sort a linked list using insertion sort.


A graphical example of insertion sort. 
The partial sorted list (black) initially contains only the first element in the list.
With each iteration one element (red) is removed from the input data and inserted in-place 
into the sorted list
 

Algorithm of Insertion Sort:

Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
At each iteration, insertion sort removes one element from the input data, 
finds the location it belongs within the sorted list, and inserts it there.
It repeats until no input elements remain.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
 */



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 add a dummy head
 0 -> 4-> 5-> 1-> 3
 pre   
cur=> 4 since cur.next.val >= cur.val cur=cur.next cur=>5 
cur=> 5 since cur.next=1 < cur.val we need to first:
    temp = 1 cur.next = temp.next; // remove 1 
    now we need to insert 1 after pre
    pre point to dummy since pre.next.val > temp, next= pre.temp=4 pre.next = temp=1 temp.next= next
cur=5 since .... 

 
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null)  return head;
        ListNode dummy = new ListNode(0);   
        dummy.next= head;
		    ListNode cur= head, temp = null, pre= null;    
        while(cur!= null && cur.next != null) {
            if(cur.val <= cur.next.val) {
                cur = cur.next;
            } else {
                temp = cur.next;
                cur.next = temp.next;
                pre = dummy;
                while(pre != null && pre.next.val <= temp.val) {
                    pre = pre.next;
                }
                temp.next = pre.next;
                pre.next = temp;
            }
        }
        return dummy.next;
    }
}