/**
 Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */

 /*
use union find
 [1, 2], [2, 3], [1, 3]
 1 has root 2, 2 has root 3,(1's root is 3 as well) 1 has root 3, 
 now there is another edge point 1 to 3 means 1 to 3 has two path which is not a graph
 if edge[1] === root already, return false, it is a graph
*/

class Solution {
  public boolean validTree(int n, int[][] edges) {
      // Basic check
      if(edges.length != n-1) return false;
      
      int[] roots = new int[n];
      for(int i=0; i<n ;i++) roots[i] = i;
      for(int[] edge : edges) {
          int superRoot0 = find(edge[0], roots);
          int superRoot1 = find(edge[1], roots);
          if(superRoot0 == superRoot1) return false; // the key
          roots[superRoot0] = superRoot1;
      }
      return true;
  }
  
  public int find(int node, int[] roots) {
      if(roots[node] == node) return node;
      return find(roots[node], roots);
  }
}
