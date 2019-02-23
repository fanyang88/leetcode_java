/**
 * 
 Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
  public List<List<Integer>> levelOrderBottom(TreeNode root) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      if(root == null)  return res;
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size = q.size();
          List<Integer> temp = new ArrayList<Integer>();
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll(); // poll is get from front
              temp.add(cur.val);
              if(cur.left != null)  q.offer(cur.left);
              if(cur.right != null)  q.offer(cur.right);
          }
          res.add(0, temp);
      }
      return res;
  }
}
