/*
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
*/

/*
[1,2,5]
i=1 dp[1] = 1
i=2 dp[2] = min(dp[2-1]+1, dp[2-2]+1)=1
i=3 dp[3] = min(dp[3-1]+1, dp[3-2]+1)=2
...
dp[i] = min(dp[i-coinK]+1)  coinK = [1,2,5]

[2] 
i=1 dp[1]=0
i=2 dp[2]=d[2-2]+1=1
i=3 dp[3]=
*/

class Solution {
  public int coinChange(int[] coins, int amount) {
      int[] dp = new int[amount+1];
      Arrays.fill(dp, amount);
      dp[0]=0;
      
      for(int i=1; i<=amount; i++) {
          int min = amount+1;
          for(int coin: coins) {
              if(i-coin <0 || dp[i-coin]==-1)  continue;
              min = Math.min(min, dp[i-coin]+1);
          }
          dp[i] = min == amount+1 ? -1 : min;
      }
      return dp[amount];
  }
}
