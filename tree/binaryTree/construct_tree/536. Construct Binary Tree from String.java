/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   / 
  3   1 5   
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()"
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
 base case:  4(2)(3)
 index = 0 root = 4 
 index=1 it is ( index++=2 root.left=2 
       index++=4
 index=4 it is another (
 
 */
class Solution {
  int index = 0;
  public TreeNode str2tree(String s) {
      return dfs(s);
  }
  
  public TreeNode dfs(String s) {
      if(s.length() ==0)  return null;
      TreeNode root = null;
      String temp = "";
      while(index < s.length() && ((s.charAt(index) <= '9' && s.charAt(index) >= '0') || s.charAt(index) == '-'))  {
          temp += s.charAt(index++);
      }
      root = new TreeNode(Integer.valueOf(temp));
      if(index < s.length() && s.charAt(index) == '(') {
          index++;
          root.left = dfs(s);
          index++;  // This is the key!!!
      } 
      if(index < s.length() &&  s.charAt(index) == '(') {  
          index ++ ;
          root.right = dfs(s);
          index++;
      }
      return root;
  }
}
