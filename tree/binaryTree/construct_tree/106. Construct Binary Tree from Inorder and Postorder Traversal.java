/**
 Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
    root=3
    
 */
class Solution {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
      if(inorder.length ==0) return null;
      TreeNode root = new TreeNode(postorder[postorder.length-1]);
      int index=0;
      for(int i=0; i<inorder.length; i++) {
          if(inorder[i] == root.val)  index = i;
      }
      int[] postorderLeft = Arrays.copyOfRange(postorder, 0, index);
      int[] postorderRight = Arrays.copyOfRange(postorder, index, postorder.length-1);
    
      int[] inorderLeft = Arrays.copyOfRange(inorder, 0, index);
      int[] inorderRight = Arrays.copyOfRange(inorder, index+1, inorder.length);
    
      root.left = buildTree(inorderLeft, postorderLeft);
      root.right= buildTree(inorderRight, postorderRight);
      return root;
  }
}