/**
 Given a binary tree, return the level order traversal of its nodes' values. 
 (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
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
 
 queue.offer = push to back
 queue.poll  = pop from front
 */
class Solution {
  public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> res= new LinkedList<>();
      if(root == null)  return res;
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size= q.size();
          List<Integer> cur = new ArrayList<Integer>();
          for(int i=0; i<size; i++) {
              TreeNode node = q.poll();
              cur.add(node.val);
              if(node.left != null) q.offer(node.left);
              if(node.right != null) q.offer(node.right);
          }
          res.add(cur);
      }
      return res;
  }
}