/*
Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:
                          1
                      /   |   \
                     3    2    4
                   /   \
                  5     6
We should return its level order traversal:

[
     [1],
     [3,2,4],
     [5,6]
]
 

Note: The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
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
class Solution {
  public List<List<Integer>> levelOrder(Node root) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      if(root == null)  return res;
      Queue<Node> q = new LinkedList<>();
      q.offer(root);
      while(!q.isEmpty()) {
          int size = q.size();
          List<Integer> list = new ArrayList<Integer>();
          for(int i=0; i<size; i++) {
              Node cur = q.poll();
              list.add(cur.val);
              if(cur.children.size() > 0) {
                  for(Node child : cur.children) {
                      q.offer(child);
                  }
              }
          }
          res.add(list);
      }
      return res; 
  }
}