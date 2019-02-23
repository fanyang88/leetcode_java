/**
 Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?
 */

 /*
        [5,2,1,3,6]
        i=0 st=[5]
        i=2 st=[5,2,1]
        i=3 since 3>st.peek=1 pop st root=1 root=2 current root is 2, push 3: st=[5,3]
        i=4 since 6>st.peek=3 pop st root=3 root=5 current root is 5 push [6]: st=[6]
        
        [5,2,6,1,3]
        i=0 st=[5]
        i=1 st=[5,2]
        i=2 since 6> st.peek() root=2 root=5 current root=5 push 6, st:[6]
        i=3 since current root is 5 > 1, which return false
        
        the key is current root should always smaller than the new node
        
*/

class Solution {
  public boolean verifyPreorder(int[] preorder) {
      Stack<Integer> st = new Stack<Integer>();
      int root = -1;
      for(int node: preorder) {
          while(!st.isEmpty() && st.peek() < node) {
              root = st.pop();
          }
          if(root != -1 && root >= node) return false;
          st.push(node);
      }
      return true;
  }
}
