/*
On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 
*/

/*
    [1,0,0,0],
    [0,0,0,0],
    [0,0,2,-1]
    
    empty=10
    (0,0)=-2 empty--=9 
    (0,1)=-2 empty--=8 
    (0,2)=-2 empty--=7  
    (0,3)=-2 empty--=6 
    (1,3)=-2 empty--=5 
    (1,2)=-2 empty--=4 
    (1,1)=-2 empty--=3 
    (1,0)=-2 empty--=2 
    (2,0)=-2 empty--=1 
    (2,1)=-2 empty--=0 
    (2,2) empty is 0, so the count++;
    
    

*/

class Solution {
  int empty=1, count=0;
  public int uniquePathsIII(int[][] grid) {
      int m = grid.length, n = grid[0].length, sx=-1, sy=-1, ex=-1, ey=-1;
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              if(grid[i][j]==0) empty++;
              if(grid[i][j]==1) {
                  sx = i;
                  sy = j;
              }
              if(grid[i][j]==2) {
                  ex = i;
                  ey= j;
              }
          }
      }
      dfs(sx, sy, ex, ey, grid, m, n);
      return count;
  }
  
  public void dfs(int x, int y, int ex, int ey, int[][] grid, int m, int n) {
      if(x < 0 || y < 0 || x>=m || y>=n || grid[x][y] <0) return;
      if(x ==ex && y==ey) {
          if(empty==0) count++;
          return;
      }
      empty--;
      grid[x][y] = -2;
      dfs(x+1, y, ex, ey, grid, m, n);
      dfs(x-1, y, ex, ey, grid, m, n);
      dfs(x, y-1, ex, ey, grid, m, n);
      dfs(x, y+1, ex, ey, grid, m, n);
      empty++;
      grid[x][y] = 0;
  }
}
