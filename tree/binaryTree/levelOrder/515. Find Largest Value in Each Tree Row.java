/*
You need to find the largest value in each row of a binary tree.

Example:
Input: 

          1
         / \
        3   2
       / \   \  
      5   3   9 

Output: [1, 3, 9]
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
  public List<Integer> largestValues(TreeNode root) {
      List<Integer> res= new ArrayList<Integer>();
      if(root == null)  return res;
      Queue<TreeNode> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size = q.size(), maxV = Integer.MIN_VALUE;
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll();
              maxV = Math.max(maxV, cur.val);
              if(cur.left!=null) q.offer(cur.left);
              if(cur.right!=null) q.offer(cur.right); 
          }
          res.add(maxV);
      }
      return res;
  }
}
