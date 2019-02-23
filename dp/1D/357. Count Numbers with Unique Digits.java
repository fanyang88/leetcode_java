/*
Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91 
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, 
             excluding 11,22,33,44,55,66,77,88,99
*/

/*
  n=1 dp[1]=10
  n=2 dp[2]=9*9 + dp[1]=91  tens digit canve 1~9 last digit can be 0~9 exclude ten's one=9
  n=3 dp[3]=9*9*8 + dp[2]
  n=4 dp[4]=9*9*8*7 + dp[3]
  ....
  
*/

class Solution {
  public int countNumbersWithUniqueDigits(int n) {
      if(n==0)  return 1;
      int[] dp=new int[n+1];
      dp[1]=10;
      int sum=81, count=8;
      
      for(int i=2; i<=n; i++) {
          dp[i] = dp[i-1] + sum;
          sum = sum*count;
          count--;
      } 
      return dp[n];
  }
}
