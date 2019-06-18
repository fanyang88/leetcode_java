/*
Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }\
 
 
 DFS + BFS
 
 First, preform DFS on root in order to find the node whose val = k, 
 at the meantime use HashMap to keep record of all back edges from child to parent;
 Then perform BFS on this node to find the closest leaf node.
 
 use example 3: construct a map:{ 1: [2, 3], 2: [1, 4], 3: [1], 4: [2, 5], 5: [4, 6], 6: [5]}
# leaves: {3, 6}
 then perform BFS, start from next node of k, for target 2, push [1, 4], no leave, pop 1, insert [3], 3 is a leave, return 3.
 */
class Solution {
  public int findClosestLeaf(TreeNode root, int k) {
      // traverse the tree first to record pre and child
      Map<Integer, List<Integer>> map  = new HashMap<>();
      Set<Integer> set = new HashSet<>();
      dfs(root, null, map, set);
      if(set.contains(k))  return k;
      
      // now use bfs to traverse the map
      Queue<Integer> q = new LinkedList<>();
      q.addAll(map.get(k));
      Set<Integer> visited = new HashSet<>();
      while(!q.isEmpty()) {
          Integer cur = q.poll();
          if(set.contains(cur)) return cur;
          if(map.get(cur)==null) continue;
          for(Integer next: map.get(cur)) {
              if(visited.contains(next)) continue;
              q.offer(next);
              visited.add(next);
          }
      }
      return root.val;
  }
  
  public void dfs(TreeNode root, TreeNode pre, Map<Integer, List<Integer>> map, Set<Integer> set) {
      if(root == null)  return;
      if(!map.containsKey(root.val)) map.put(root.val, new ArrayList<>());
      if(pre != null) map.get(root.val).add(pre.val);
      if(root.left == null && root.right== null) {
          set.add(root.val);
          return;
      }
      if(root.left != null) {
          map.get(root.val).add(root.left.val);
          dfs(root.left, root, map, set);
      }
      if(root.right != null) {
          map.get(root.val).add(root.right.val);
          dfs(root.right, root, map, set);
      }
  }
}
