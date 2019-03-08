/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 similar to serialize binary tree, we get each serialized string for each subtree, 
 and store in hashmap, if this same string get found twice, we return its root, 
 means this is a duplicate subtree.
 */
class Solution {
  List<TreeNode> res = new ArrayList<TreeNode>();
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
      Map<String, Integer> map = new HashMap<>();
      serialize(root, map);
      return res;
  }
  
  public String serialize(TreeNode root, Map<String, Integer> map) {
      if(root ==null)  return "#";
      String key = "" + root.val + "(" + serialize(root.left, map) +")" + "("+ serialize(root.right, map)+")";
      if(map.get(key) != null && map.get(key) ==1)  res.add(root); // already exist, add res
      map.put(key, map.getOrDefault(key, 0) +1);
      return key;
  }
}
