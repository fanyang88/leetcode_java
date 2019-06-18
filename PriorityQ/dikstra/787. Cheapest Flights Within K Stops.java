/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and flights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
*/

//It happen to be the same idea of Dijkstra's algorithm, but we need pruning the path.

class Solution {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
      Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
      for(int[] f: flights) {
          if(!map.containsKey(f[0])) map.put(f[0], new HashMap<>());
          map.get(f[0]).put(f[1], f[2]);
      }
      
      pq.add(new int[]{0, src, K+1});
      while(!pq.isEmpty()) {
          int[] cur = pq.poll();
          // cur[0] is price, cur[1] is city, cur[2] is stop;
          if(cur[1] == dst) return cur[0];
          int stop = cur[2];
          if(stop==0)  continue;  // this is the Key!!!, we don't need this path anymore
          Map<Integer, Integer> adj= map.getOrDefault(cur[1], new HashMap<>());
          for(int key: adj.keySet()) {
              pq.offer(new int[]{cur[0] + adj.get(key), key, stop-1});
          }
      }
      return -1;
  }
}
