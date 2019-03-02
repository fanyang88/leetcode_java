/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

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
      List<String> res = new ArrayList<String>();
      _serialize(root, res);
      return String.join(",", res);
  }
  
  public void _serialize(TreeNode root, List<String> res) {
      if(root == null) {
          res.add("#");
      } else {
          res.add(""+root.val);
          _serialize(root.left, res);
          _serialize(root.right, res);
      }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
      Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
      return _deserialize(nodes);
  }
  
  public TreeNode _deserialize(Deque<String> nodes) {
      String val = nodes.remove();
      TreeNode root = null;
      if(!val.equals("#")) {
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
