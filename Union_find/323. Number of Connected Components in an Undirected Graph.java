/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

/*
    uninon find
    [[0, 1], [1, 2], [3, 4]]
    root[0] = 0 root[1]=1 . root[0]=1
    root[1]=1 root[2]=2 root[1]=2 
    root[3]=3 root[4] = 4 root[3]=4
    roots:[1, 2, 2, 4, 4]
    
    root[0]=1 root[1]=2 root[2]=2 root[0]=2
    
*/

class Solution {
  public int countComponents(int n, int[][] edges) {
      int[] roots = new int[n];
      Set<Integer> set = new HashSet<>(); 
      for(int i=0; i<n; i++)  roots[i] = i;
      
      for(int[] edge: edges) {
          int root0 = find(edge[0], roots);
          int root1 = find(edge[1], roots);
          if(root0 != root1) {
              roots[root0] = root1;
          }
      }
      
      for(int i=0; i<n; i++) {
          roots[i] = find(i, roots);
          set.add(roots[i]);
      }
      return set.size();
      
  }
  
  public int find(int node, int[] roots) {
      if(roots[node] == node) return node;
      return find(roots[node], roots);
  }
}
