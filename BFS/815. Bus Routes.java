/*
We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.

We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.

Example:
Input: 
routes = [[1, 2, 7], [3, 6, 7]]
S = 1
T = 6
Output: 2
Explanation: 
The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Note:

1 <= routes.length <= 500.
1 <= routes[i].length <= 500.
0 <= routes[i][j] < 10 ^ 6.
*/

/*
    use BFS, we can't go to same bus, also can't go to the same bus station
     we need to construct a map to show current stop to next stops
 e.g: [[1, 2, 7], [3, 6, 7]]
 we need to construct a map with key is the stop and value is the buses can reach to this stop
 map  1: [0]  2: [0] 7: [0, 1] 3: [1]  6: [1]
 use BFS, start at S=1
    visited = [1]
    Q=[1]  pop Q, curStop=1, 1 is from bus 0, we add bus 0 to be visited
    for each stop of bus 0, to push to Q, Q=[2,7] since 1 has visited
    step++=1
    Q=[2,7] pop Q, curStop=2, 2 is from bus 0, bus 0 has visited, do nothing
            pop Q, curStop=7, 7 is from bus 0 and bus 1, bus 0 has visited, do nothing
                              we need to add bus 1 stops to Q, [3,6] since 7 has visited
    step++;
    Q=[3,6] pop Q, curStop=3, 3 is from bus 1, bus 1 has visited, do nothing
            pop Q, curStop=6= T, return step
   we need two sets, one to record which stops is visited, the other to record which bus is visited.
    

*/

class Solution {
  public int numBusesToDestination(int[][] routes, int S, int T) {
      int step=0;
      Set<Integer> stopVisited = new HashSet<>(), busVisited = new HashSet<>();
      Map<Integer, List<Integer>> map = new HashMap<>();
      Queue<Integer> q = new LinkedList<>();
      for(int i=0; i<routes.length; i++) {
          int[] r = routes[i];
          for(int stop : r) {
              if(!map.containsKey(stop)) map.put(stop, new ArrayList<>());
              map.get(stop).add(i);
          }
      }
      
      stopVisited.add(S);
      q.offer(S);
      while(!q.isEmpty()) {
          int size = q.size();
          for(int i=0; i<size; i++) {
              int curStop = q.poll();
              if(curStop == T) return step;
              for(int bus: map.get(curStop)) {
                  if(busVisited.contains(bus)) continue;
                  busVisited.add(bus);
                  for(int stop: routes[bus]) {
                      if(stopVisited.contains(stop)) continue;
                      stopVisited.add(stop);
                      q.offer(stop);
                  }
              }
          }
          step++;
      }
      return -1;
  }
}
