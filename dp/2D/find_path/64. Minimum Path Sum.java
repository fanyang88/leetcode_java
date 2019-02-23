/*
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.
*/

/*
    [1,3,1],        [1,4,5]
    [1,5,1], ->     [2,5,6]
    [4,2,1]         [5,8,7]
    
    
*/
class Solution {
  public int minPathSum(int[][] grid) {
      int m = grid.length, n = grid[0].length;
      int[][] dp = new int[m][n];
      dp[0][0] = grid[0][0];
      for(int i =0; i<m; i++) {
          for(int j =0; j<n; j++) {
              dp[i][j] = grid[i][j];
              int left = j-1>=0 ? dp[i][j-1] : Integer.MAX_VALUE;
              int up = i-1>=0 ? dp[i-1][j] : Integer.MAX_VALUE;
              if(j-1<0 && i-1<0)  continue;
              dp[i][j] += Math.min(left, up);
          }
      }
      return dp[m-1][n-1];
  }
}
