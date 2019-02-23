/**
 * Given a 2D binary matrix filled with 0's and 1's, 
 * find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
 */

 /*
  if(matrix[i][j] ===0)  dp[i][j] = 0;
else dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1],dp[i-1][j-1])
get the maxV from dp, and the answer is maxV*maxV

e.g:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

0 0 0 0 0 0
0 1 0 1 0 0 
0 1 0 1 1 1
0 1 1 1 2 2
0 1 0 0 1 0

*/

class Solution {
  public int maximalSquare(char[][] matrix) {
      if(matrix.length ==0) return 0;
      int m = matrix.length, n = matrix[0].length;
      int[][] dp= new int[m+1][n+1];
      int maxV = 0;
      for(int i=1; i<=m; i++) {
          for(int j=1; j<=n; j++) {
              if(matrix[i-1][j-1] == '1') {
                  dp[i][j] = 1+ Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                  maxV = Math.max(maxV, dp[i][j]);
              }
              
          }
      }
      return maxV*maxV;
  }
}
