/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
 */

 /*
       For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 reverse the string first, [7,6,5,4,3,2,1]  
 then reverse [5,6,7] [1,2,3,4]  
        
*/

class Solution {
  public void rotate(int[] nums, int k) {
      k = k % nums.length;
      reverse(0, nums.length-1, nums);
      reverse(0, k-1, nums);
      reverse(k, nums.length-1, nums);
  }
  
  public void reverse(int lo, int hi, int[] nums) {
      while(lo < hi) {
          int temp = nums[lo];
          nums[lo] = nums[hi];
          nums[hi] = temp;
          lo++;
          hi--;
      }
  }
}
