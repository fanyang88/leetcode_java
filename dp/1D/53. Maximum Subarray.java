/**
 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

 /*
[-2,    1,      -3,4,-1,2,1,-5,4]
 -2
      (-1,1)
sum = Math.max(nums[i] + sum, nums[i]);
maxV = Math.max(maxV, sum);
*/
class Solution {
  public int maxSubArray(int[] nums) {
      int maxV =nums[0], sum=nums[0];
      
      for(int i=1; i<nums.length; i++) {
          sum = Math.max(nums[i] + sum, nums[i]);
          maxV = Math.max(maxV, sum);
      }
      return maxV;
  }
}