/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Note:

N will be in the range [1, 100].
K will be in the range [1, N].
The length of times will be in the range [1, 6000].
All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 
*/

/*
we can use priority queue to mimic diksjra

map: 
key:source node 
value: linked node and distance 
*/

class Solution {
  public int networkDelayTime(int[][] times, int N, int K) {
      int res = -1;
      Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
      for(int[] t: times) {
          if(!map.containsKey(t[0])) map.put(t[0], new HashMap<>());
          map.get(t[0]).put(t[1], t[2]);
      }
      // put node and distance in the pq
      Queue<int[]> pq = new PriorityQueue<>((a,b) -> (a[1] - b[1]));// sort by distance
      Set<Integer> visited = new HashSet<>();
      // we start from K, init distance=0
      pq.add(new int[] {K, 0}); 
      while(!pq.isEmpty()) {
          int[] cur = pq.poll();
          if(visited.contains(cur[0]))  continue;
          visited.add(cur[0]);
          N--;
          res = cur[1];
          if(map.containsKey(cur[0])) {
              for(int next: map.get(cur[0]).keySet()) {
                  pq.add(new int[]{next, map.get(cur[0]).get(next) + cur[1]});
              }
          }
      }
      return N==0 ? res : -1;
  }
}
