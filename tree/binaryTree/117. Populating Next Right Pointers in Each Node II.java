/**
 * 
 * Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
 */
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 
     1
   /  \
  2 -> 3
 / \    \
4   5    7

parent=root, childHead=curChild= null
parent=>1 since childHead=null childHead=>2 child=>2
          since childHead=>2!=null child=>2.next = 3 child=3
          parent = parent.next=null stop
parent=>2 childHead=curChild= null
          since childHead=null childHead=>4 child=>4
          since childHead=>4!=null child=>4.next = 5 child=5
          parent = parent.next= 3
          since childHead=>5!=null child=>5.next = 7 child=7
          
 */
public class Solution {
  public void connect(TreeLinkNode root) {
      TreeLinkNode parent = root, curChild = null, childHead = null;
      while(parent != null) {
          while(parent != null) {
              
              if(parent.left != null) {
                  if(childHead ==null) {
                      childHead = parent.left;
                      curChild = childHead;
                  } else {
                      curChild.next = parent.left;
                      curChild = curChild.next;
                  }
              }
              if(parent.right != null) {
                   if(childHead ==null) {
                      childHead = parent.right;
                      curChild = childHead;
                  } else {
                      curChild.next = parent.right;
                      curChild = curChild.next;
                  }
              }
              parent = parent.next;
          }
          parent = childHead;
          childHead = null;
          curChild = null;
      }
  }
}
