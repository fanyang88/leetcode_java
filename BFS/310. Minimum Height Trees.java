/*
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
*/

/*
[[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
 
 0:[3]
 1:[3]
 2:[3]
 3: [0, 1, 2, 4]
 4:[3, 5]
 5: [4]
 
 find all nodes only have one neighbor and push onto stack, they are the leave nodes
 st: [0, 1,2 5]
 cur = 0 st:[1,2,5]  0 has neibhbor 3, delete 0 in 3's neighbor list
 cur = 1 st:[2,5]  1 has neibhbor 3,   delete 1 in 3's neighbor list
 cur = 2 st:[5]  2 has neibhbor 3,  delete 2 in 3's neighbor list, now 3 only have one nost in list, push to st
 cur = 5 st:[]  5 has neibhbor 4,  delete 5 in 4's neighbor list, now 4 only have one nost in list, push 4 onto st, 
 st = [3, 4]  since each of the neighbor is only one, all leave node, this would be the answer
 
*/


class Solution {
  public List<Integer> findMinHeightTrees(int n, int[][] edges) {
      if (n == 1) return Collections.singletonList(0);
      List<Set<Integer>> map = new ArrayList<>(n);
      for(int i=0; i<n; i++) map.add(new HashSet<>());
      for(int[] edge: edges) {
          map.get(edge[0]).add(edge[1]);
          map.get(edge[1]).add(edge[0]);
      }
      List<Integer> leaves = new ArrayList<>();
      // get the leaves first, which would be those has hashset size=1
      for(int i=0; i<n; i++) {
          if(map.get(i).size() == 1)leaves.add(i);
      }
      
      while(n > 2) {
          n = n-leaves.size();
          List<Integer> newLeaves = new ArrayList<>();
          for(int i : leaves) {
              int j = map.get(i).iterator().next(); // node j connected to i
              map.get(j).remove(i);
              if(map.get(j).size()==1) newLeaves.add(j);
          }
          leaves = newLeaves;
      }
      return leaves;
  }
}

