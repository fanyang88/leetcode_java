/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 How would you optimize the kthSmallest routine?
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

// interative
class Solution {
  int res=0;
  public int kthSmallest(TreeNode root, int k) {
      Stack<TreeNode> st = new Stack<>();
      while(root != null) {
          st.push(root);
          root = root.left;
      }
      
      while(k>0) {
          TreeNode cur = st.pop();
          k--;
          if(k==0)  return cur.val;
          cur = cur.right;
          while(cur != null) {
              st.push(cur);
              cur = cur.left;
          }
      }
      return -1;
  }
}

// recursive
class Solution {
  // if left has 3 nodes, k=4 means current root is the 4th value, directly return root.val
  // if left has 3 nodes, k=2 means we left tree more than needed, continue to search in left tree
  // if left has 3 nodes, k=5, means we need find in right tree, k=5-3-1=1 not 5-3 since we need to exclude root
  public int kthSmallest(TreeNode root, int k) {
      int count = countNodes(root.left);
      if(count+1 == k) {
          return root.val;
      } else if(count +1 < k) {
          return kthSmallest(root.right, k-count-1);
      } else {
          return kthSmallest(root.left, k);
      }
  }
  
   public int countNodes(TreeNode n) {
      if (n == null) return 0;
      return 1 + countNodes(n.left) + countNodes(n.right);
  }
  
}