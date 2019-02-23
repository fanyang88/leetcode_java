/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
 */

 /*
  extending from house robber, either can rob 0~n-2 or 1~n-1
*/

class Solution {
  public int rob(int[] nums) {
      if(nums.length==0)  return 0;
      if(nums.length==1)  return nums[0];
      if(nums.length==2)  return Math.max(nums[0], nums[1]);
      
      int robber1 = dp(nums, 0, nums.length-2);
      int robber2 = dp(nums, 1, nums.length-1);
      return Math.max(robber1, robber2);
  }
  
  public int dp(int[] nums, int lo, int hi) {
      int dp0 = nums[lo];
      int dp1= Math.max(nums[lo], nums[lo+1]);
      for(int i=lo+2; i<=hi; i++) {
          int cur = Math.max(dp1, dp0+nums[i]);
          dp0 = dp1;
          dp1= cur;
      }
      return Math.max(dp0, dp1);
  }
}
