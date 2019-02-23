/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
*/

/*
            /  dp[i][j-1]  carry on, do nothing on jth day
 dp[i][j] = 
            \  max(dp[i-1][m] + price[j] - price[m])   sell on jth day, means buy on mth day(m=0~j-1)
            
                            0 1 2 (day)
                            3 2 6 5 0 3
(trancaction number)    0   0 0 0 0 0 0
                        1   0 0 4 4 4 4
                        2   0 0 4 4 4 7
                
when i=1 j=1 dp[1][1] = max { m=0:  dp[0][0] - price[0] + price[1] 
                        
when i=2 j=3 dp[2][3] = max { m=0:  dp[1][0] - price[0] + price[3] 
                              m=1:  dp[1][1] - price[1] + price[3] 
                              m=2:  dp[1][2] - price[2] + price[3] 
                              
when i=2 j=4 dp[2][4] = max { m=0:  dp[1][0] - price[0] + price[4] 
                              m=1:  dp[1][1] - price[1] + price[4] 
                              m=2:  dp[1][2] - price[2] + price[4] 
                              m=3:  dp[1][3] - price[3] + price[4] 
                              
assume dp[2][3] = maxV + price[3]

when j=4:
 update maxV = max(maxV, dp[1][3]-price[3])
 dp[2][4] = max(maxV + price[4], dp[2][3])
 
 
 maxV = max(maxV, dp[i-1][j-1] - price[j-1])
dp[i][j] = Math.max(dp[i][j-1], maxV + prices[j]);

*/

class Solution {
  public int maxProfit(int k, int[] prices) {
      if(prices.length==0)  return 0;
      if (k >= prices.length / 2) return quickSolve(prices);
      
      int n = prices.length, maxV = 0;
      int[][] dp = new int[k+1][n];
      for(int i=1; i<=k; i++) {
          maxV = Integer.MIN_VALUE;
          for(int j=1; j<n; j++) {
              maxV = Math.max(dp[i-1][j-1] - prices[j-1], maxV);
              dp[i][j] = Math.max(dp[i][j-1], maxV + prices[j]);
          }
      }
      return dp[k][n-1];
  }
  
  public int quickSolve(int[] prices) {
      int sum = 0;
      for(int i=1; i<prices.length; i++) {
          sum +=prices[i] - prices[i-1]  <= 0 ? 0 : prices[i] - prices[i-1] ;
      }
      return sum;
  }
}
