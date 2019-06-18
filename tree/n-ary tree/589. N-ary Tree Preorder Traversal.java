/*
Given an n-ary tree, return the preorder traversal of its nodes' values.

For example, given a 3-ary tree:
                          1
                      /   |   \
                     3    2    4
                   /   \
                  5    6

Return its preorder traversal as: [1,3,5,6,2,4].
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
*/

// Iterative
class Solution {
  List<Integer> list= new ArrayList<Integer>();
  public List<Integer> preorder(Node root) {
      if(root == null)  return list;
      Stack<Node> stack = new Stack<Node>();
      stack.add(root);
       while(!stack.isEmpty()) {
          Node cur = stack.pop();
          list.add(cur.val);
          // from right to left child
          for (int i = cur.children.size() - 1; i >= 0; i--) 
              stack.add(cur.children.get(i));
      }
      return list;
  }
}

// Recursive
class Solution {
  List<Integer> list= new ArrayList<Integer>();
  public List<Integer> preorder(Node root) {
      if(root == null)  return list;
      list.add(root.val);
      for(Node child : root.children) preorder(child);
      return list;
  }
}