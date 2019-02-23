/**
 Given an integer n, 
 generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
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
  public List<TreeNode> generateTrees(int n) {
      if(n==0) return new LinkedList<>();
      return dfs(1, n);
  }
  
  public List<TreeNode> dfs(int start, int end) {
      List<TreeNode> res = new LinkedList<>();
      if(start > end) {
          res.add(null);
      } else {
          for(int i=start; i<=end; i++) { // root as i
              List<TreeNode> leftList = dfs(start, i-1); // get left combination
              List<TreeNode> rightList = dfs(i+1, end); // get right combination
              // push left and right to res
              for(TreeNode left: leftList) {
                  for(TreeNode right: rightList) {
                      TreeNode root = new TreeNode(i);
                      root.left = left;
                      root.right = right;
                      res.add(root);
                  }
              }
          }
      }
      return res;
  }
}