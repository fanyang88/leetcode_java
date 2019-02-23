/**
 * Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
    1
   / \
  2   5
 / \   \
3   4   6

|


1   
 \  
  2   
 / \   
3   4   
     \
      5
       \
        6
        
|

 */
class Solution {
  public void flatten(TreeNode root) {
      if(root == null)  return;
      if(root.left != null) {
          TreeNode next = root.left;
          TreeNode right = root.right;
          while(next.right != null)  next = next.right;
     
          root.right = root.left;
          root.left = null;
          next.right  = right;
      }
      flatten(root.right);
  }
}