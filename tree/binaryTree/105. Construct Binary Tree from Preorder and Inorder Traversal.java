/**
 Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
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
 
 preorder: [3,9,20,15,7]
 inorder:  [9,3,15,20,7]
                root[3] 
      p:[9]     /    \    p[20,15,7]
      in:[9]   /       \  in:[15,20,7]
        root[9]       root[20]
        ...            ...
 */
class Solution {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
      if(preorder.length ==0) return null;
      TreeNode root = new TreeNode(preorder[0]);
      int index=0;
      for(int i=0; i<inorder.length; i++) {
          if(inorder[i] == root.val)  index = i;
      }
      int[] preorderLeft = Arrays.copyOfRange(preorder, 1, index+1);
      int[] preorderRight = Arrays.copyOfRange(preorder, index+1, preorder.length);
      
      int[] inorderLeft = Arrays.copyOfRange(inorder, 0, index);
      int[] inorderRight = Arrays.copyOfRange(inorder, index+1, inorder.length);
      
      root.left = buildTree(preorderLeft, inorderLeft);
      root.right= buildTree(preorderRight, inorderRight);
      return root;
  }
}
