/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
 use bfs to get the distance from each 1 to all 0 and record the result in a map
 map: x y of 0: distance to building1, building2 ...
 caculate the one with smallest value

*/

class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  public int shortestDistance(int[][] grid) {
      if(grid.length==0)  return 0;
      Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
      
      int m = grid.length, n = grid[0].length, minV = Integer.MAX_VALUE, count=0;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j] == 1) {
                  count++;
                  bfs(i, j, m, n, map, grid);
              }
          }
      }
      for(int key: map.keySet()) {
          if(map.get(key).size() == count) {
              minV = Math.min(minV, getSum(map.get(key)));
          }
      }
      return minV==Integer.MAX_VALUE ? -1: minV;
  }
  
  public int getSum(List<Integer> list) {
      int sum = 0;
      for(int i:list) sum+=i;
      return sum;
  }
  
  public void bfs(int i, int j, int m, int n, Map<Integer, List<Integer>> map, int[][] grid) {
      Set<Integer> set = new HashSet<Integer>();
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      Queue<Integer> q= new LinkedList<>();
      q.add(i*n+j);
      int level = 1;
      
      while(!q.isEmpty()) {
          int size = q.size();
          for(int k=0; k<size; k++) {
              int cur = q.poll();
              for(int[] d: dirs) {
                  int x = cur/n + d[0];
                  int y = cur%n + d[1];
                  if(x < 0 || x>=m || y<0 || y>=n || grid[x][y] != 0 || set.contains(x*n+y))  continue;
                  if(map.get(x*n+y)==null) map.put(x*n+y, new ArrayList<Integer>());
                  map.get(x*n+y).add(level);
                  q.offer(x*n+y);
                  set.add(x*n+y);
              }
          }
          level++;
      }
  }
}