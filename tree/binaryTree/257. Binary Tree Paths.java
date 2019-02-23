/**
 Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
  public List<String> binaryTreePaths(TreeNode root) {
      List<String> res = new ArrayList<String>();
      if(root == null)  return res;
    
      dfs(root, new ArrayList<Integer>(), res);
      return res;
  }
  
  public void dfs(TreeNode root, List<Integer> path, List<String> res) {
      path.add(root.val);
      if(root.left != null) {
          dfs(root.left, path, res);
      }
      if(root.right != null) {
          dfs(root.right, path, res);
      }
       if(root.left == null && root.right ==null) {
          res.add(toString(path));
       }
      path.remove(path.size()-1);
  }
  
  public String toString(List<Integer> path) {
      String res = "";
      for(int n: path) {
          res += ""+n+ "->";
      }
      return res.substring(0, res.length()-2);
  }
}
