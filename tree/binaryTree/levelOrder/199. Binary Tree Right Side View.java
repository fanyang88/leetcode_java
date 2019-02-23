/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 level order traverse
 */
class Solution {
  public List<Integer> rightSideView(TreeNode root) {
      
      List<Integer> res = new ArrayList<Integer>();
      if(root == null)  return res;

      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll();
              if(i==size-1)  res.add(cur.val);
              if(cur.left != null) q.offer(cur.left);
              if(cur.right != null) q.offer(cur.right);
          }
      } 
      return res;
  }
}
