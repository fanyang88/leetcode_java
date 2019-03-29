/*
Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 

Note:

Both of the given trees will have between 1 and 100 nodes.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 All you need to do is traverse for both trees and records their leaves as string and then compare the strings.
 */

class Solution {
    
  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
      StringBuilder sb1= new StringBuilder(), sb2= new StringBuilder();
      dfs(root1, sb1);
      dfs(root2, sb2);
      return sb1.toString().equals(sb2.toString());
  }
  
  public void dfs(TreeNode root, StringBuilder sb) {
      if(root ==null) return;
      dfs(root.left, sb);
      if(root.left == null && root.right==null) sb.append(root.val + "-");
      dfs(root.right, sb);
  }
}
