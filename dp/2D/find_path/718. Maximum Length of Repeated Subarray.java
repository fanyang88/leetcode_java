/*
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].
Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100
*/

/*
    similar to longest common strings
       null  1 2 3 2 1
 null   0    0 0 0 0 0
  3     0    0 0 1 0 0
  2     0    0 1 0 2 0
  1     0    1 0 0 0 3
  4     0    0 0 0 0 0
  
  max is 3
  init dp[i][j] = 0
  if(A[i-1] === B[j-1])  dp[i][j] = 1+ dp[i-1][j-1] 
  maxV = max(maxV, dp[i][j])
  
*/

class Solution {
  public int findLength(int[] A, int[] B) {
      int m = A.length, n = B.length, max = 0;
      int[][] dp = new int[m+1][n+1];
      
      for(int i=1; i<=m; i++) {
          for(int j=1; j<=n; j++) {
              dp[i][j] = 0;
              if(A[i-1] == B[j-1]) {
                  dp[i][j] = dp[i-1][j-1] +1;
              }
              max = Math.max(max, dp[i][j]);
          }
      }
      return max;
  }
}
