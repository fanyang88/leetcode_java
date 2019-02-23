/*
Given a binary array, find the maximum number of consecutive 1s in this array.

Example 1:
Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
*/

/*
simple dp
    if nums[i] = 0 dp[i] = 0
    else dp[i] = dp[i-1]+1
    max Dp is the answer
*/

class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
      int n = nums.length, maxV = 0;
      if(n==0)  return 0;
      int[] dp = new int[n];
      dp[0] = (nums[0] == 0) ? 0 : 1;
      for(int i=1; i<n; i++) {
          if(nums[i] !=0) {
              dp[i] = dp[i-1]+1;
          }
      }
      for(int num: dp) {
          maxV = Math.max(maxV, num);
      }
      return maxV;
  }
}
