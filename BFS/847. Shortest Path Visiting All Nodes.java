/*
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, 
you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Note:

1 <= graph.length <= 12
0 <= graph[i].length < graph.length
*/

/*
        since start from any node, init state for each node can be: state = 1<< node
        the ideal state should be (1 << n) -1 which means all n digits are visited
        we need to record if the path has visited with which node
        if visited, shouldn't go again
        the new State should be oldState | (1 << nextNode)
*/

class Solution {
  public int shortestPathLength(int[][] graph) {
      int n = graph.length, ideal = (1<<n) -1, steps=0;
      Set<String> set = new HashSet<>();
      Queue<int[]> q = new LinkedList<>();
      for(int i=0; i<n; i++) {
          int state = (1<< i);
          q.offer(new int[]{i, state});
          set.add(""+state + ":" + i);
      }
      while(!q.isEmpty()) {
          int size = q.size();
          for(int k=0; k<size; k++) {
              int[] cur = q.poll();
              if(cur[1] == ideal) return steps;
              for(int next: graph[cur[0]]) {
                  int nextState = cur[1] | (1<< next);
                  if(set.contains(nextState+":"+next)) continue; // path visited
                  set.add(nextState+":"+next);
                  q.offer(new int[]{next, nextState});
              }
          }
          steps++;
      }
      return -1;
  }
}

