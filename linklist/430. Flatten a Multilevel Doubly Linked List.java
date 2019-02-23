/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer, which may or may not point to a separate doubly linked list. These child lists may have one or more children of their own, and so on, to produce a multilevel data structure, as shown in the example below.

Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the first level of the list.

 

Example:

Input:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL

Output:
1-2-3-7-8-11-12-9-10-4-5-6-NULL
 

Explanation for the above example:

Given the following multilevel doubly linked list:


 

We should return the following flattened doubly linked list:


*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};

Start form the head , move one step each time to the next node
When meet with a node with child, say node p, follow its child chain to the end and connect the tail node with p.next, by doing this we merged the child chain back to the main thread
Return to p and proceed until find next node with child.
Repeat until reach null
1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
             
p=> 1  p=>2  p=>3  since it has child p.next = child connect it, p point to 7
   1---2--3
          |
          7---8---9---10---4---5---6--NULL
             |
             11--12--NULL
...

*/
class Solution {
  public Node flatten(Node head) {
      Node cur = head;
      while(cur != null) {
          if(cur.child != null) {
              Node next = cur.next;
              Node child = cur.child;
              while(child.next != null) child = child.next;
              cur.next = cur.child;
              cur.next.prev = cur;
              child.next = next;
              if(next != null)  next.prev = child;
              cur.child = null;  // this is the key
          }
          cur = cur.next;
      }
      return head;
  }
}
