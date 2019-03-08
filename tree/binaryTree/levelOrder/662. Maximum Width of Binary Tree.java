/*
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 if root index=i left child is 2*i , right child is 2*i+1
push index instead of value
use example 1: 
push 0 st=[0]
     pop 0: push [2*0], [2*0+1]    width = 1-0+1
     pop 0: push [0] [1]  pop [1]: push [3]  width=3-0+1 

 */
class Solution {
  public int widthOfBinaryTree(TreeNode root) {
      Queue<TreeNode> q = new LinkedList<>();
      Deque<Integer> q2 = new ArrayDeque<>();
      int max = 1;//Integer.MIN_VALUE;
      q.offer(root);
      q2.offer(0);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll();
              int index = q2.poll();
              if(cur.left != null) {
                  q.offer(cur.left);
                  q2.offer(index*2);
              }
              if(cur.right != null) {
                  q.offer(cur.right);
                  q2.offer(index*2+1);
              }
          }
          if(!q2.isEmpty()) max = Math.max(max, q2.peekLast() - q2.peekFirst()+1);
      }
      return max;
  }
}
