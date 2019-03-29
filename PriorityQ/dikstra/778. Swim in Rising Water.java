/*
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example 1:

Input: [[0,2],[1,3]]
Output: 3
Explanation:
At time 0, you are in grid location (0, 0).
You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.

You cannot reach point (1, 1) until time 3.
When the depth of water is 3, we can swim anywhere inside the grid.
Example 2:

Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
Output: 16
Explanation:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

The final route is marked in bold.
We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
Note:

2 <= N <= 50.
grid[i][j] is a permutation of [0, ..., N*N - 1].
*/

/*
    dea is similar to Dijkstra's algorithm. Each iteration, 
    we will select the neighbouring cell which is having least water level. 
    Once we selected it, we will push all of its neighbours, 
    that have not visited to the priority queue. Complexity will be O(N2 log N)
*/

class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  public int swimInWater(int[][] grid) {
      PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (grid[a[0]][a[1]] - grid[b[0]][b[1]]));
      pq.offer(new int[]{0, 0});
      int n = grid.length, level = 0;
      boolean[][] visited = new boolean[n][n];
      while(!pq.isEmpty()) {
          int[] cur = pq.poll();
          level = Math.max(level, grid[cur[0]][cur[1]]);
          if(cur[0] == n-1 && cur[1] == n-1) {
              return level;
          }
          for(int[] d: dirs) {
              int[] next = new int[]{cur[0]+d[0], cur[1]+d[1]};
              if(next[0] < 0 || next[1] < 0 || next[0] >=n || next[1] >=n || visited[next[0]][next[1]]) continue;
              pq.offer(next);
              visited[next[0]][next[1]] = true;
          }
      }
      return level;
  }
}
