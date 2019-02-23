/**
 Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
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

// non-recursive

/*
          1
        / . \
        2 .  3
      / .  \
     4 .   5
     
   1,4,2,5,3 
   
*/
// non-recursive
class Solution {
  public List<Integer> inorderTraversal(TreeNode root) {
      Stack<TreeNode> st = new Stack<TreeNode>();
      List<Integer> res = new ArrayList<Integer>();
      TreeNode cur = root;
      while(cur != null || !st.isEmpty()) {
          while(cur!=null) {
              st.push(cur);
              cur = cur.left;
          }
          cur = st.pop();
          res.add(cur.val);
          cur = cur.right;
      }
      return res;
  }
}

// recursive
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        dfs(root, res);
        return res;
    }
    
    public void dfs(TreeNode root, List<Integer> res) {
        if(root ==null)   return;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    } 
}