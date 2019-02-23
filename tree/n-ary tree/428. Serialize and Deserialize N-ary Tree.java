/*
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

                          1
                      /   |   \
                     3    2    4
                   /   \
                  5    6
as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 

Note: N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};

the different is we need to record the size of each children
*/
class Codec {

  // Encodes a tree to a single string.
  public String serialize(Node root) {
      List<String> res = new ArrayList<String>();
      _serialize(root, res);
      return String.join(",", res);
  }
  
  public void _serialize(Node root, List<String> res) {
      if(root == null) {
          res.add("#");
      } else {
          res.add(""+root.val);
          res.add(""+root.children.size());
          for(Node child: root.children) {
              _serialize(child, res);
          }
      }
  }

  // Decodes your encoded data to tree.
  public Node deserialize(String data) {
      Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(",")));
      return _deserialize(nodes);
  }
  
  public Node _deserialize(Deque<String> nodes) {
      String val = nodes.removeFirst();
      Node root;
      if(val.equals("#")) {
          root = null;
      } else {
          root = new Node(Integer.valueOf(val));
          root.children = new ArrayList<Node>();
          int size = Integer.valueOf(nodes.removeFirst());
          for(int i=0; i<size; i++) {
              root.children.add(_deserialize(nodes));
          }
      }
      return root;
  }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

