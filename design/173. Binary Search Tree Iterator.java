/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:
          7
        /    \
      3      15
            /  \
           9   20 

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false
 

Note:

next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.

 */

 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 push root to leftmost st=[7, 3]
 call next() pop 3, 3 has no right, continue
 call next() pop 7, 7 has right, st=[15,9]
 call next() pop 9, 9 has no right, st=[15]
 call next() pop 15, 15 has right, st=[20]
 call next() pop 20, 20 has no right, st=[]
 
 
 
 */
class BSTIterator {
  Stack<TreeNode> st;
  public BSTIterator(TreeNode root) {
      st = new Stack<TreeNode>();
      TreeNode cur = root;
      while(root != null) {
          st.push(root);
          root = root.left;
      }
  }
  
  /** @return the next smallest number */
  public int next() {
      TreeNode cur = st.pop();
      int res = cur.val;
      if(cur.right != null) {
          cur = cur.right;
          while(cur != null) {
              st.push(cur);
              cur = cur.left;
          }
      } 
      return res;
  }
  
  /** @return whether we have a next smallest number */
  public boolean hasNext() {
      return !st.isEmpty();
  }
}

/**
* Your BSTIterator object will be instantiated and called as such:
* BSTIterator obj = new BSTIterator(root);
* int param_1 = obj.next();
* boolean param_2 = obj.hasNext();
*/