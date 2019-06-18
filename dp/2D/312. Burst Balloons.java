/*
Given n balloons, indexed from 0 to n-1. 
Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. 
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167

*/


/*
 dp[i][j] means the maximum sum to burst bool from i to j
 
 i-1 i, .....  k-1, k, k+1, .... j, j+1
     |-----------|     |---------|
      dp[i][k-1]       dp[k+1][j]
 to get the max from i to j, we first burst i to k-1 and k+1 to j
 then we burst k, so the point is the find the k that make the dp[i][j] to be max
 dp[i][j] = max(dp[i][k-1] + nums[i-1]*nums[k]*nums[j+1] + dp[k+1][j]);   i=<k<=j
 len = 1 to N
 
 [3,1,5,8] 
 
     0  1   2   3
0    3 30
1      15
2           40
3               40
 
when len=1 if the last burst is {0} cost=0+0+1*3*1=3
           if the last burst is {1} cost=0+0+3*1*5=15
           if the last burst is {2} cost=0+0+1*5*8=40
           if the last burst is {3} cost=0+0+5*8*1=40
when len=2 [3,1] if burst 1 then burst 3: cost=0(since it is empty on left of 3)+dp[1][1](burst 1 cost)+1*3*5=30
                 if burst 3 then burst 1: cost=dp[0][0] + 0 + 1*1*5 = 8, so max=30
                 ....
          

*/
class Solution {
  public int maxCoins(int[] nums) {
      int n = nums.length;
      if(n==0) return 0;
      int[][] dp = new int[n][n];
      for(int len=1; len<=nums.length; len++) {
          int i=0, j=len-i-1;
          for(;j<nums.length; i++, j++) {
              for(int k=i; k<=j; k++) {
                  int val =(k-1<0 ? 0 : dp[i][k-1]) + (k+1 >=n ? 0 : dp[k+1][j]) + 
                      (i-1<0 ? 1 : nums[i-1])*nums[k]*(j+1>=n ? 1 : nums[j+1]);
                  dp[i][j] = Math.max(dp[i][j], val);
              }
              
          }
      }
      return dp[0][n-1];
  }
}