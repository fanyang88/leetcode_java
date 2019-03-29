/*
In a 2D grid of 0s and 1s, we change at most one 0 to a 1.

After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).

Example 1:

Input: [[1, 0], [0, 1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
Example 2:

Input: [[1, 1], [1, 0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
Example 3:

Input: [[1, 1], [1, 1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.
 

Notes:

1 <= grid.length = grid[0].length <= 50.
0 <= grid[i][j] <= 1.

*/
/*
  for every grid[i][j]==0 we change it to 1, and get the area out of it
  the max area is the answer
  meanwhile if the area == M*N, we don't need to continue;
  use dfs to get area
*/

class Solution {
  public int largestIsland(int[][] grid) {
      int m = grid.length, n= grid[0].length, max = 0;
      boolean hasZero = false;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j]==1) continue;
              hasZero = true;
              grid[i][j]=1;
              int area = dfs(i, j, grid, new boolean[m][n], m, n);
              if(area== m*n) return m*n;
              max = Math.max(area, max);
              grid[i][j]=0;
          }
      }
      return hasZero? max : m*n;
  }
  
  public int dfs(int i, int j, int[][] grid, boolean[][] visited, int m, int n) {
      if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0 || visited[i][j]) return 0;
      visited[i][j]=true;
      return 1+ dfs(i-1, j, grid, visited, m, n) +
          dfs(i+1, j, grid, visited, m, n) + 
          dfs(i, j-1, grid, visited, m, n) + 
          dfs(i, j+1, grid, visited, m, n);
  }
}
