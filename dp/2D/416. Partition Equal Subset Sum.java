/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100.
The array size will not exceed 200.
Example 1:

Input: [1, 5, 11, 5]

Output: true

Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: [1, 2, 3, 5]

Output: false

Explanation: The array cannot be partitioned into equal sum subsets.
*/

/*
knap-sack problem
The key is to find a subset of numbers in the array that could sum to sum(nums)/2

 dp[i][0] = true, dp[0][j] = false; dp[0][0] = true 
 dp[i][j] means if there is a subset exist from 0-i that could sum to j
 dp[i][j] = dp[i-1][j] or  // without add nums[i]
 dp[i][j] = dp[i-1][j-nums[i]]  // with add nums[i]
 
      0      1      2       3       4       5       6       7       8      9     10     11
  0  true, false,  false, false, false,  false,  false,  false,  false, false,  false, false
  1  true,  true,  false, false, false,  false,  false,  false,  false, false,  false, false
  5  true,  true,  false, false, false,  true,   true,   false,  false, false,  false, false
 11  true,  true,  false, false, false,  true,   true,   false,  false, false,  false, true
  5  true,  true,  false, false, false,  true,   true,   false,  false, false,  true, true
  
*/

class Solution {
  public boolean canPartition(int[] nums) {
      int n = nums.length, sum=0;
      for(int num: nums) sum+=num;
      if(sum % 2!=0)  return false;
      
      sum = sum/2;
      boolean[][] dp = new boolean[n+1][sum+1];
      for(int i=0; i<=n; i++) dp[i][0]=true;
      
      for(int i=1; i<=n; i++) {
          for(int j=1; j<=sum; j++) {
              if(j>=nums[i-1]) {
                  dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
              }
          }
      }
      return dp[n][sum];
  }
}
