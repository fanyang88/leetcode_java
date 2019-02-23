/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:

Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286, and k = 2

    4
   / \
  2   5
 / \
1   3

Output: [4,3]
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 Use two stack to store the value bigger then target and values smaller than target, 
 then we pick values from these two stacks
 
 */
class Solution {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
      List<Integer> res = new ArrayList<Integer>();
      Stack<Integer> minSt = new Stack<Integer>();
      Stack<Integer> maxSt = new Stack<Integer>();
      inorder(root, minSt, target, false); // getmin
      inorder(root, maxSt, target, true); // getmax
      while(k-- > 0) {
          if(minSt.isEmpty() && maxSt.isEmpty())  break;
          if(minSt.isEmpty()) {
              res.add(maxSt.pop());
          } else if(maxSt.isEmpty()) {
              res.add(minSt.pop());
          } else {
              res.add(Math.abs(minSt.peek() - target) < Math.abs(maxSt.peek() - target) ? minSt.pop() : maxSt.pop());
          }
      }
      return res;
      
  }
  
  public void inorder(TreeNode root, Stack<Integer> st, double target, boolean isMax) {
      if(root ==null) return;
      inorder(isMax ? root.right : root.left, st, target, isMax);
      if(isMax) {
          if(root.val <= target)  return;
      } else {
          if(root.val > target)  return;
      }
      st.push(root.val);
      inorder(isMax ? root.left : root.right, st, target, isMax);
  }
}
