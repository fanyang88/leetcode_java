/**
 * 
 * Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
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
 
     e.g:       1
             /    \
            2      3
        2,3,1
        
        st=[1]  st.pop . cur=1 res=[1]
        st=[2,3] st.pop cur=3 . res=[1,3]
        st=[2] st.pop cur=2 . res=[1,3,2]
        reverse res=[2,3,1]
        
 */
class Solution {
  public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> res= new ArrayList<Integer>();
      if(root == null) return res;
      Stack<TreeNode> st = new Stack<TreeNode>();
      st.push(root);
      while(!st.isEmpty()) {
          TreeNode cur = st.pop();
          res.add(cur.val);
          if(cur.left != null) st.push(cur.left);
          if(cur.right != null) st.push(cur.right);
      }
      Collections.reverse(res);
      return res;
  }
}
