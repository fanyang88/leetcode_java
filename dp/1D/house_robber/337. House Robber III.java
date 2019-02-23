/*
The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
Example 2:

Input: [3,4,5,1,3,null,1]

     3
    / \
   4   5
  / \   \ 
 1   3   1

Output: 9
Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 since for each root, the maxV on this root either equals the root + root.left.left + root.left.right + root.right.left+ ....
  or the root.left+root.right+root.left.left.left + ...
  
 
     3
    / \
   2   3
    \   \ 
     3   1
     
  case 1: rob(root.left) + rob(root.right)
  case 2: rob(root) + rob(root) + rob(root.left.left) +
          rob(root.left.rihgt) + rob(root.right.left) + rob(root.right.rihgt)
 */
class Solution {
  public int rob(TreeNode root) {
      Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
      return dfs(root, map);
  }
  
  public int dfs(TreeNode root, Map<TreeNode, Integer> map) {
      if(root == null)  return 0;
      if(map.get(root) != null)  return map.get(root);
      int val = root.val;
      if(root.left!=null) {
          val += dfs(root.left.left, map) + dfs(root.left.right, map);
      }
      if(root.right!=null) {
          val += dfs(root.right.left, map) + dfs(root.right.right, map);
      }
      int val2 = dfs(root.left, map) + dfs(root.right, map);
      val = Math.max(val, val2);
      map.put(root, val);
      return val;
  }
}
