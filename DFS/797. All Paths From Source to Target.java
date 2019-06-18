/*
Given a directed, acyclic graph of N nodes. Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

*/

class Solution {
  List<List<Integer>> res;
  
  public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
      res = new ArrayList<>();
      Set<Integer> visited = new HashSet<>();
      List<Integer> path = new ArrayList<>();
      path.add(0);
      visited.add(0);
      dfs(0, path, visited, graph);
      return res;
  }
  
  public void dfs(int node, List<Integer> path, Set<Integer> visited, int[][] graph) {
      if(node == graph.length-1) {
          res.add(new ArrayList<>(path));
          return;
      }
      for(int next: graph[node]) {
          if(visited.contains(next)) continue;
          visited.add(next);
          path.add(next);
          dfs(next, path, visited, graph);
          visited.remove(next);
          path.remove(path.size()-1);
      }
  }
}
