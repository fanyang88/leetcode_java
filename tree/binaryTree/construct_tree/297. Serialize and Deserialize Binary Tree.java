/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
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
public class Codec {

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
      List<String> list = new ArrayList<String>();
      _serialize(root, list);
      return String.join(",", list);
  }
  
  public void _serialize(TreeNode root, List<String> list) {
      if(root ==null) {
          list.add("#"); 
      } else {
          list.add(""+root.val);
          _serialize(root.left, list);
          _serialize(root.right, list);
      }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
      Deque<String> nodes = new LinkedList<>();
      nodes.addAll(Arrays.asList(data.split(",")));
      return _deserialize(nodes);
  }
  
  public TreeNode _deserialize(Deque<String> nodes) {
      String val = nodes.remove();
      TreeNode root;
      if(val.equals("#")) {
          root = null;
      } else {
          root = new TreeNode(Integer.valueOf(val));
          root.left = _deserialize(nodes);
          root.right = _deserialize(nodes);
      }
      return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
