/*
In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
*/

/*
        this quesion means to find all nodes that is not in a cycle
        e.g: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        |-----------------------|
        |       |---------------|
        V       |               V
        0       1 ----> 2       3       4-----> 5       6
        | .     ^       ^ |                     ^
        |-------|       | |---------------------|
        |---------------|
        
        node 0 is in a cycle: 0->1->3->0
        node 1 is in a cycle: 1->3->0->1
        node 2 is not in cycle: 2->5
        same as 4, 5, 6
        node 3 is in the cycle
        
        we can use a inCycle[] to denote each node, 
        inCycle==0 means unvisited
        inCycle =1 means in a cycle
        inCycle =-1 means not in a cycle
        
        start from 0, since inCycle[0] = 0 unvisited, we set it as 1 first
        then dfs to each node
             0  F
            / \ 
         F 1   2 T
         /  \    \
     T  2    3 F  5 T
       /      \ 
    T 5        0 F
     
     since 0->1->3->0 since incycle[0]==1 return false so inCycle[3]=1->incycle[1]=1 -> incycle[0]=1
     
*/

class Solution {
  public List<Integer> eventualSafeNodes(int[][] graph) {
      List<Integer> res = new ArrayList<Integer>();
      int[] inCycle = new int[graph.length];
      for(int i = 0; i<graph.length; i++) {
          if(notInCycle(i, graph, inCycle)) res.add(i);
      }
      return res;
  }
  
  public boolean notInCycle(int i, int[][] graph, int[] inCycle) {
      if(inCycle[i] !=0) return inCycle[i]==-1;
      inCycle[i] = 1;// assume it is in cycle first
      for(int next: graph[i]) {
          if(!notInCycle(next, graph, inCycle)) return false;
      }
      inCycle[i] = -1;
      return true;
  } 
}