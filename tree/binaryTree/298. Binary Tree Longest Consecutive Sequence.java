/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    / 
   2    
  / 
 1

Output: 2 

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
*/


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 Note: has to be parent to child, 
 and sequence must be 1-2-3 not 3-2-1
 so we check from root to left and right seperately
 
 */
class Solution {
  int maxV = 0;
  public int longestConsecutive(TreeNode root) {
      if(root == null)  return 0;
      dfs(root.val, root, 0);
      return maxV;
  }
  
  public void dfs(int nextV, TreeNode root, int cur) {
      if(root == null)  return;
      cur = root.val == nextV ? cur+1 : 1;
      maxV = Math.max(maxV, cur);
      dfs(root.val+1, root.left, cur);
      dfs(root.val+1, root.right, cur);
  }
}

