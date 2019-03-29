/*
In a N x N grid representing a field of cherries, each cell is one of three possible integers.

 

0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.
 

Your task is to collect maximum number of cherries possible by following the rules below:

 

Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 

 

Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation: 
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
 

Note:

grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 
*/

/*
    trip goes from (0, 0) -> (n-1, n-1) to (0, 0) equals
    two person both go from (0,0) -> (n-1, n-1) *2
    if two person end up in same place with cherry, only add 1
    two person can get to same place or different place
    
    since A, B both start from (n-1, n-1)
    if A is (x1, y1) B is (x2, y2) x1+y1=x2+y2 since they both go left/up and each has one step at a time
    so we can use (x1, y1, x2) to repsent a state, since y2 = x1+y1-x2
    dp(x1, y1, x2) denotes the max cherries we can get from (x1, y1)(x2, y2) to (0,0)
    
   
     Go from (0, 0) -> (n-1, n-1) -> (0, 0) can be opt to two men go from (0, 0) -> (n-1, n-1) together, 
     but when they go into the same cell, the cur state can only be added 1 (use once)
Using DP to solve the problem:
    1.  dp[x1][y1][x2] to represent the largest ans we can get when first guy (marked as A) at(x1, y2) and second guy(marked as B) at (x2, x1 + y1 - x2)
        * because we can only go right and down, so we have x1 + y1 = x2 + y2
    2.  Induction: every time we calculate the maximum of :
        * dp[x1 - 1][y1][x2] : A down, B right
        * dp[x1][y1 - 1][x2] : A right, B right
        * dp[x1 - 1][y1][x2 - 1]: A down, B down
        * dp[x1][y1 - 1][x2 - 1]: A right, B down
        if the Max of these values is negative, then we don't have a path to this point
        else we have: dp[x1][y1][x2] = Max + grid[x1 - 1][y1 - 1] + grid[x2 - 1][y2 - 1](if x1 != x2 && y1 != y2) else we
        only add once.
    3.  Base case;
        we use dp[][][]from 1 - n, so we have:
            dp[1][1][1] = 1 and all other values are MIN_VALUE
    4.  Ans:
        dp[n][n][n]
    5.  Direction:
        from top left -> bottom right
    6.  Time:
        O(n^3)
        Space:
        O(n^3)
        
        
        if we use dfs:
        we need the answer at (n-1, n-1) so we dfs to get the answer
        
*/

class Solution {
  public int cherryPickup(int[][] grid) {
      int n = grid.length;
      int[][][] map = new int[n][n][n];
      for(int i=0; i<n; i++) {
          for(int j=0; j<n; j++)
              Arrays.fill(map[i][j], Integer.MIN_VALUE);
      }
      return Math.max(0, dfs(n-1, n-1, n-1, grid, map));
  }
  
  public int dfs(int x1, int y1, int x2, int[][] grid, int[][][] map) {
      int y2 = x1 + y1 - x2;
      if(x1<0 || y1 < 0 || x2 < 0 || y2 < 0)  return -1;
      if(grid[x1][y1] < 0 || grid[x2][y2] < 0)  return -1;
      if(x1 == 0 && y1 == 0) { // at the starting point
          return grid[x1][y1];
      }  
      if(map[x1][y1][x2] != Integer.MIN_VALUE)  return map[x1][y1][x2];
      int ans = Math.max(dfs(x1-1, y1, x2-1, grid, map), 
                         Math.max(dfs(x1-1, y1, x2, grid, map), 
                                  Math.max(dfs(x1, y1-1, x2-1, grid, map), 
                                           dfs(x1, y1-1, x2, grid, map))));
      if(ans < 0) {
          map[x1][y1][x2] = -1;
          return -1;
      }
      ans += grid[x1][y1];
      if(x1!=x2) ans += grid[x2][y2];
      map[x1][y1][x2] = ans;
      return ans;
  }
}
