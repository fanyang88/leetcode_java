/**
 * 
 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
 */

 /*
    e.g: n=5
    when n=0 dp[0] = 0 the least numbers is 0
    when n=1 dp[1] = dp[1-1*1]+1= 1 the least number is 1
    when n=2 dp[2] = dp[2-1*1]+1= 2 the least number is 2: 1+1
    when n=3 dp[3] = dp[3-1*1]+1= 3 the least number is 2: 1+1+1
    when n=4 dp[4] = dp[4-1*1]+1= 4: 1+1+1+1 
             dp[4] = dp[4-2*2]+1= 1: 2+2  the least number is 1
    when n=5 dp[5] = dp[5-1*1]+1= 5: 1+1+1+1+1
             dp[4] = dp[5-2*2]+1= 2: 4+1  the least number is 2
    
    
*/

class Solution {
  public int numSquares(int n) {
      int[] dp = new int[n+1];
      for(int i=1; i<=n; i++) {
          dp[i] = Integer.MAX_VALUE;
          for(int j=1; j*j <= i; j++) {
              dp[i]= Math.min(dp[i], dp[i-j*j]+1);
          }
      }
      return dp[n];
  }
}
