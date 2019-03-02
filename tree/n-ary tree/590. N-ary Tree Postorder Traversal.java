/*
Given an n-ary tree, return the postorder traversal of its nodes' values.

For example, given a 3-ary tree:
                          1
                      /   |   \
                     3    2    4
                   /   \
                  5    6
Return its postorder traversal as: [5,6,3,2,4,1].

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
  public List<Integer> postorder(Node root) {
      if(root == null)  return list;
      Stack<Node> stack = new Stack<Node>();
      stack.add(root);
       while(!stack.isEmpty()) {
          Node cur = stack.pop();
          list.add(cur.val);
          for(Node child : cur.children) {
              stack.add(child);
          }
      }
      Collections.reverse(list);
      return list;
  }
}

// Recurisve
class Solution {
  List<Integer> list= new ArrayList<Integer>();
  public List<Integer> postorder(Node root) {
      if(root == null)  return list;
      for(Node child : root.children) 
              postorder(child);
      
      list.add(root.val);
      return list;
  }
}
