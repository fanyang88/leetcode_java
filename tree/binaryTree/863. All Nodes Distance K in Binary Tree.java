/*
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
  Method 1: use HashMap
    1. build a undirected graph using treenodes as vertices, and the parent-child relation as edges
    2. do BFS with source vertice (target) to find all vertices with distance K to it.

 */
class Solution {
  Map<TreeNode, List<TreeNode>> map = new HashMap<>();
  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
      List<Integer> res = new ArrayList<>();
      build(root, null);
      if(!map.containsKey(target)) return res;
      Queue<TreeNode> q = new LinkedList<>();
      Set<TreeNode> visited = new HashSet<>();
      q.offer(target);
      visited.add(target);
      while(K>0) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              TreeNode cur = q.poll();
              if(map.get(cur)==null) continue;
              for(TreeNode next: map.get(cur)) {
                  if(visited.contains(next)) continue;
                  visited.add(next);
                  q.offer(next);
              }
          }
          K--;
      }
      
      while(!q.isEmpty()) res.add(q.poll().val);
      return res;
  }
  
  public void build(TreeNode root, TreeNode parent) {
      if(root ==null)  return;
      if(parent != null) {
          if(!map.containsKey(root)) map.put(root, new ArrayList<>());
          if(!map.containsKey(parent)) map.put(parent, new ArrayList<>());
          map.get(root).add(parent);
          map.get(parent).add(root);
      }
      build(root.left, root);
      build(root.right, root);
  }
}
