/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */

 /*
    once there is '1' count++
    use dfs to traverse all surrounding '1' and turn them to 0
*/

class Solution {
  public int numIslands(char[][] grid) {
      if(grid.length==0)  return 0;
      int m = grid.length, n= grid[0].length, count=0;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j] =='1') {
                  count++;
                  dfs(i, j, grid, m, n);
              }
          }
      }
      return count;
  }
  
  public void dfs(int i, int j, char[][] grid, int m, int n) {
      grid[i][j]='0';
      if(i+1<m && grid[i+1][j]=='1') dfs(i+1, j, grid, m, n);
      if(j+1<n && grid[i][j+1]=='1') dfs(i, j+1, grid, m, n);
      if(i-1>=0 && grid[i-1][j]=='1') dfs(i-1, j, grid, m, n);
      if(j-1>=0 && grid[i][j-1]=='1') dfs(i, j-1, grid, m, n);
      
  }
}
