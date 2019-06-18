/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

*/

/*
    dfs
    need to be aware if there is a value in the position, need to compare with current value
    if current value < value exist, update

*/

class Solution {

  public void wallsAndGates(int[][] rooms) {
      if(rooms.length ==0)  return;
      int m = rooms.length, n=rooms[0].length;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(rooms[i][j] == 0) {
                  dfs(i, j, 0, rooms, m, n);
              }
          }
      }
  }
  
  public void dfs(int i, int j, int v, int[][] rooms, int m, int n) {
      rooms[i][j] = v;
      if(i-1>=0 && rooms[i-1][j] > v+1) dfs(i-1, j, v+1, rooms, m, n);
      if(i+1<m  && rooms[i+1][j] > v+1) dfs(i+1, j, v+1, rooms, m, n);
      if(j-1>=0 && rooms[i][j-1] > v+1) dfs(i, j-1, v+1, rooms, m, n);
      if(j+1<n  && rooms[i][j+1] > v+1) dfs(i, j+1, v+1, rooms, m, n);
  }
}