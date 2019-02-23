/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

 /*
 topologic sort:  
 step1: first we init degree for each node, 
 step2: we can now do BFS based on the map and degree. 
    find the degree =0 in degree array, this would be the entry of the graph, put it in the queue, and pop it out, 
    for all the nodes linked to this node, we reduce their degree by 1, if there is a node among them ===0, push into q
 step3: after the queue is cleared, check if all node degree changed to 0, if not, return false;
 e.g: [1, 0] degree: 1->0   (node)0->1(degree)
 push 1 to Queue, pop 1, get neighbor is 0, neigbor 0 degree reduce to 0, push to Queue, pop 0
*/

class Solution {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
      Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
      int[] degree = new int[numCourses];
      Queue<Integer> q = new LinkedList<>();
      
      for(int i=0; i<prerequisites.length; i++) {
          degree[prerequisites[i][0]] ++; // it depend on 1
          if(!map.containsKey(prerequisites[i][1])) {
              map.put(prerequisites[i][1], new ArrayList<>());
          }
          map.get(prerequisites[i][1]).add(prerequisites[i][0]);
      }
      
      for(int i=0; i<degree.length; i++) {
          if(degree[i] ==0) q.offer(i);
      }
      
      //start from courses has no dependence
      while(!q.isEmpty()) {
          int cur = q.poll();
          if(!map.containsKey(cur))  continue;
          for(int next : map.get(cur)) {
              degree[next]--;
              if(degree[next]==0)  q.offer(next);
          }
      }
      
      for(int i=0; i<degree.length; i++) {
          if(degree[i] !=0) return false;
      }
      return true;
  }
}
