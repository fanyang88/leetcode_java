/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
      1
    / . \ 
    2 . 3
    
    st=[1]   st.pop()  cur = 1 res= [1]
    st=[3,2] st.pop()  cur=2 res= [1,2]
    st=[3] st.pop()  cur=3 res= [1,2,3]
 
 */
class Solution {
  public List<Integer> preorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      if(root == null)  return res;
      Stack<TreeNode> st= new Stack<TreeNode>();
      st.push(root);
      while(!st.isEmpty()) {
          TreeNode cur = st.pop();
          res.add(cur.val);
          if(cur.right != null) st.push(cur.right);
          if(cur.left != null) st.push(cur.left);
      }
      return res;
  }
}