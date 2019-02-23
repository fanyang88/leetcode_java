/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

 /*
    -2,3,-4
    curMax = Math.max(curMax*nums[i], curMin*nums[i], nums[i]);
    curMin = Math.min(curMax*nums[i], curMin*nums[i], nums[i]);
    i=0 curMax=-2 curMin=-2
    i=1 curMax=3  curMin=-6
    i=2 curMax=24  curMin=-12  24 is the answer
*/

class Solution {
  public int maxProduct(int[] nums) {
      int curMax = nums[0], curMin = nums[0], maxV = nums[0];
      for(int i=1; i<nums.length; i++) {
          int tempMax = Math.max(curMax*nums[i], Math.max(curMin*nums[i], nums[i]));
          curMin = Math.min(curMax*nums[i], Math.min(curMin*nums[i], nums[i]));
          maxV = Math.max(maxV, tempMax);
          curMax = tempMax;
      }
      return maxV;
  }
}