/*
Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
*/


/*
  the key is during dfs, we need to record the maxlen from this position, so next time we don't need to recacluate again
  e.g: in example 2, when we at(0,0) we get the longest len=4 and also the maxlen of each position in that path, next time when we check (0,1) we can directly return its longest path, no deep to recalc again
  
*/
class Solution {
  int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  public int longestIncreasingPath(int[][] matrix) {
      if(matrix.length ==0)  return 0;
      int m = matrix.length, n= matrix[0].length, maxL=1;
      int[][] cache = new int[m][n];
      for(int i=0; i<m; i++) {
          for(int j=0; j<n; j++) {
              maxL = Math.max(maxL, dfs(i, j, m, n, matrix, cache));
          }
      }
      return maxL;
  }
  
  public int dfs(int i, int j, int m, int n, int[][] matrix, int[][] cache) {
      if(cache[i][j] >0)  return cache[i][j];
      int maxLen=1;
      for(int[] d: dirs) {
          int x = i+d[0];
          int y = j+d[1];
          if(x < 0 || x >=m || y<0 || y>=n || matrix[x][y]<= matrix[i][j])  continue;
          maxLen = Math.max(maxLen, 1+dfs(x, y, m, n, matrix, cache));
      }
      cache[i][j] = maxLen;
      return maxLen;
  }
}