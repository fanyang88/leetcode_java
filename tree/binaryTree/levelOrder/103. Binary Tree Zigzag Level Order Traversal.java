/**
 Given a binary tree, return the zigzag level order traversal of its nodes' values. 
 (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
 */
class Solution {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res= new LinkedList<>();
    boolean order = true;
    if(root == null)  return res;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    while(!q.isEmpty()) {
        int size= q.size();
        List<Integer> cur = new ArrayList<Integer>();
        for(int i=0; i<size; i++) {
            TreeNode node = q.poll();
            if(order) {
                cur.add(node.val);
            } else {
                cur.add(0, node.val);
            }
            if(node.left != null) q.offer(node.left);
            if(node.right != null) q.offer(node.right);
        }
        order = order ? false : true;
        res.add(cur);
    }
    return res;
  }
}
