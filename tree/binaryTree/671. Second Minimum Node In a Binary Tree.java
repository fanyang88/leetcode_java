/*
Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input: 
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input: 
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.
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
    int min1= -1, min2=-1;
    public int findSecondMinimumValue(TreeNode root) {
        dfs(root);
        return min2;
    }
    
    void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.left);
        if(min1 == -1) {
            min1 = root.val;
        } else if(min1 > root.val) {
            min2 = min1;
            min1 = root.val;
        } else if(min1 < root.val) {
            min2 = min2==-1 ? root.val : Math.min(min2, root.val);
        }
        dfs(root.right);
    }
}
