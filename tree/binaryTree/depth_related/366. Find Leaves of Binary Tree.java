/*
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

 

Example:

Input: [1,2,3,4,5]
  
          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]
 

Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          
 

2. Now removing the leaf [2] would result in this tree:

          1          
 

3. Now removing the leaf [1] would result in the empty tree:

          []         
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
  // use a map to record every depth and related nodes
  public List<List<Integer>> findLeaves(TreeNode root) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      dfs(root, res);
      return res;
  }
  
  public int dfs(TreeNode root, List<List<Integer>> res) {
      if(root == null)  return -1;  // This is the key!!! not 0 but -1
      int depth = 1 + Math.max(dfs(root.left, res), dfs(root.right, res));
      // e.g: 4,5,3 has depth 0
      if(res.size() == depth)  res.add(new ArrayList<>()); 
      res.get(depth).add(root.val);
      root.left = null;
      root.right = null;
      return depth;
  }
}