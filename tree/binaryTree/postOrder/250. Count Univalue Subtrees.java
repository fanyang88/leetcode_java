/**
 Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * 
  when @leftmost 5, isUnivalue(node.left=null)=true & isUnivalue(node.right=null)=true
  count++, return true
  when @middlemost 5, isUnivalue(node.left=null)=true & isUnivalue(node.right=null)=true
  count++, return true
  when @1, isUnivalue(node.left)==true & isUnivalue(node.right)==true, but 1!=5, so return false
 * 
 */
class Solution {
  int count=0;
  public int countUnivalSubtrees(TreeNode root) {
      dfs(root);
      return count;
  }
  
  public boolean isUnivalue(TreeNode node){
    if(node == null) return true;
    if(isUnivalue(node.left) & isUnivalue(node.right)){
        if(node.left != null && node.left.val != node.val) return false;
        if(node.right != null && node.right.val != node.val) return false;
        count += 1;
        return true;
    }
    return false; 
  }
}
