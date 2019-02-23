/**
 Two elements of a binary search tree (BST) are swapped by mistake.

Recover the tree without changing its structure.

Example 1:

Input: [1,3,null,null,2]

   1
  /
 3
  \
   2

Output: [3,1,null,null,2]

   3
  /
 1
  \
   2
Example 2:

Input: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

Output: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
[3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

in order: 
st push 3
st push 1  st pop 1 become pre   st pop 3=cur.val > pre.val, pre=3 
st push 4  st push 2, st pop 2, since cur=2< pre=3 first=3 sec=2 pre=2
                    st pop 4, since cur=4> pre=2 
swap frst and second
 */
class Solution {
  public void recoverTree(TreeNode root) {
      TreeNode first= null, sec = null, pre = null;
      Stack<TreeNode> st = new Stack<TreeNode>();
      List<Integer> res = new ArrayList<Integer>();
      TreeNode cur = root;
      while(cur != null || !st.isEmpty()) {
          while(cur!=null) {
              st.push(cur);
              cur = cur.left;
          }
          cur = st.pop();
          if(pre != null && cur.val < pre.val) {
              if(first == null) {
                  first = pre;
                  sec = cur;
              } else {
                  sec = cur;
              }
          }
          pre = cur;
          cur = cur.right;
      }
      
      int val = first.val;
      first.val = sec.val;
      sec.val = val;
  }
}
