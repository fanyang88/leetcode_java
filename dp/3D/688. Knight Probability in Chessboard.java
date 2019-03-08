/*
On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

 



 

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.

 

Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.
 

Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.
*/


/*
similar to LC 576
 initially, p = 1 and knight is at (r, c)
 next move, it can go to (r+1, c+2) , (r-1, c+2) .... each with p = 1/8 
 we create a new state matrix and record next state which is the 8 position has p=1/8  next[newr][newc] = dp[r][c] * (1/8)
 till k steps, we caculate sum of all the next[i][j] 
 
 the probability that the knight remains on the board after it has stopped moving. = sum of all dp[i][j]
 we can use a dp[i][j][k] to denote the probability of knight at (i, j) when we reach to k steps
 
 since dp[i][j][k]= dp[i][j][k-1] + dp[i][j][k-1]/8 
 we can reduce it to 2D using a temp to record the state
 
 */
class Solution {
  public double knightProbability(int N, int K, int r, int c) {
      int[][] dirs = {{-1, 2}, {1,2}, {-1, -2}, {1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
      double sum = 0.0;
      double[][] dp = new double[N][N];
      
      dp[r][c] = 1;
      for(int k=0; k<K; k++) {
          double[][] temp = new double[N][N];
          for(int i=0; i<N; i++) {
              for(int j=0; j<N; j++) {
                  if(dp[i][j] == 0)  continue;
                  for(int[] d: dirs) {
                      int x = i+d[0], y=j+d[1];
                      if(x < 0 || x >=N || y<0 || y>=N)  continue;
                      temp[x][y] += (dp[i][j] / 8);
                  }
              }
          }
          dp = temp;
      }
      for(int i=0; i<N; i++) {
          for(int j=0; j<N; j++) {
              sum += dp[i][j];
          }     
      }
      return sum;
  }
}
