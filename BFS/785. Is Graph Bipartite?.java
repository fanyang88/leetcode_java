/*
Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
*/

/*
    use BFS
  we use color method, for example 1: 0 => [1, 3] we color 0 to 'B', 1, 3 color to 'R', 1 => [0, 2] 
 since 0 is diff with 2, np, 2 color to be 'B', 2 => [1, 3], 1,3, are diff with 2 ... return true, 
 no two adjecent node has same color
 but if we check exmaple 2: 0 => [1,2,3] 0 to be 'B', 1,2,3 to be 'R', 1=> [0,2] since 1 is same with 2 return false.
 
*/

class Solution {
  public boolean isBipartite(int[][] graph) {
      int[] color = new int[graph.length];
      for(int i=0; i<graph.length; i++) {
          if(graph[i].length==0) continue; //no edge connected
          if(color[i] !=0) continue; // already visited this node
          Queue<Integer> q = new LinkedList<>();
          q.offer(i); //check node i
          color[i] = 2;
          while(!q.isEmpty()) {
              int cur = q.poll();
              int curColor = color[cur];
              for(int next: graph[cur]) {
                  // we should color next to be oppsite color
                  if(color[next] !=0) {
                      if(color[next] == curColor) return false;
                      continue;
                  }
                  color[next] = -curColor;
                  q.offer(next);
              }
          } 
      }
      return true;
  }
}