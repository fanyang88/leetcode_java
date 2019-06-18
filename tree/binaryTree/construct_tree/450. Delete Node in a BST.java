/*
Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5                   5
   / \                 / \
  3   6    --->       4   6
 / \   \             /     \
2   4   7           2       7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 if to be delete node has both left and right, e.g:  node 3 in example 1
 we replace 4 with 3 and delete 4 
 
 */
class Solution {
  public TreeNode deleteNode(TreeNode root, int key) {
      if(root ==null)  return root;
      if(root.val < key) {
          root.right = deleteNode(root.right, key);
      } else if(root.val > key) {
          root.left = deleteNode(root.left, key);
      } else {
          // current root = key
          if(root.left == null && root.right == null)  {
              return null;
          } else if(root.left == null) {
              return root.right;
          } else if(root.right == null) {
              return root.left;
          } else {
              TreeNode cur = root.right; // find the next larger number, store in val
              while(cur.left != null)  cur = cur.left;
              int val = cur.val;
              root.val = val; // make root value to be val
              root.right = deleteNode(root.right, val); // delete the node with value=val in root right branch
          }
      }
      return root;
  }
}

