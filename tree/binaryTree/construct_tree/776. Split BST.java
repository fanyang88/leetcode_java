/*
Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.

Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.

You should output the root TreeNode of both subtrees after splitting, in any order.

Example 1:

Input: root = [4,2,6,1,3,5,7], V = 2
Output: [[2,1],[4,3,6,null,null,5,7]]
Explanation:
Note that root, output[0], and output[1] are TreeNode objects, not arrays.

The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
Note:

The size of the BST will not exceed 50.
The BST is always valid and each node's value is different.
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

// res[0]: root with values <= V
// res[1]: root with values > V

class Solution {
  public TreeNode[] splitBST(TreeNode root, int V) {
         if (root == null)  return new TreeNode[]{null, null};
          if (root.val <= V) {
              // The cut off is somewhere in the right subtree relative to root
              TreeNode[] res = splitBST(root.right, V);
              // res[0] is the node for all values <= V found in the right sub tree
              // since it's from the right sub tree, all values must be < =V
              // so safe to do the following
              root.right = res[0];

              // root along with everything in its left subtree are already <= V
              // so after setting root.right=res[1], now root represents all nodes <= V
              // the node for all values greater than V is still res[1], some node found on the right subtree
              return new TreeNode[]{root, res[1]};
          } else {
              // The cut off is somewhere in the left subtree relative to root              
              TreeNode[] res = splitBST(root.left, V);
              // res[1] is the node for all values > V found in the left sub tree
              // since it's from the left sub tree, all values must be less than root
              // so safe to do the following
              root.left = res[1];
              // root along with everything in its right subtree are already >V
              // so after setting root.left=res[1], now root represents all values >V
              // the node for all values less than V is still res[0], some node found on the left subtree 
              return new TreeNode[]{res[0], root};
          }
  }
}

