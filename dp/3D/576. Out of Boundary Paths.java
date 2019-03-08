/*
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

 

Example 1:

Input: m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:

Input: m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

 

Note:

Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].
*/
/*
    once the ball is out of boundry, can not come back again
    we need to use a matrix to record how many times each places has been stepped
    
    we can use dp[N][i][j] to denote start from out of boundray, how many way we can reach to (i, j) 
        which is equal as start from (i, j), how many way we can reach to out of boundart
    
    if(x, y) is at out of boundary, dp[*][x][y] = 1
    so to reach (i, j) from (x, y) using 1 step: dp[i][j] +=1
    if (x, y) is at not out of boundary, 
    to reach (i, j) from (x, y) using 1 step: dp[i][j] += dp[x][y] 
    

*/

class Solution {
  public int findPaths(int m, int n, int N, int i, int j) {
   
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      final int mod = 1000000007;
      int[][][] dp = new int[N+1][m][n];
      for(int k=1; k<=N; k++) {
          for(int x=0; x<m; x++) {
              for(int y=0; y<n; y++) {
                  for(int[] d: dirs) {
                      int nx = x+d[0], ny = y+d[1];
                      if(nx < 0|| nx >=m || ny<0 || ny>=n) {  // next step is out of boundray
                          dp[k][x][y] += 1;
                      } else {
                          dp[k][x][y] = (dp[k][x][y] + dp[k-1][nx][ny]) % mod ;
                      }
                  }
              }
          }
      }
      return dp[N][i][j];
  }
}


// convert to 2D, since we just need the last status
class Solution {
  int res=0;
  public int findPaths(int m, int n, int N, int i, int j) {
   
      int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
      final int mod = 1000000007;
      int[][] dp = new int[m][n];
      for(int k=1; k<=N; k++) {
          int[][] temp = new int[m][n];
          for(int x=0; x<m; x++) {
              for(int y=0; y<n; y++) {
                  for(int[] d: dirs) {
                      int nx = x+d[0], ny = y+d[1];
                      if(nx < 0|| nx >=m || ny<0 || ny>=n) {  // next step is out of boundray
                          temp[x][y] += 1;
                      } else {
                          temp[x][y] = (temp[x][y] + dp[nx][ny]) % mod ;
                      }
                  }
              }
          }
          // change dp to be temp
          dp = temp;
      }
      return dp[i][j];
  }
}

