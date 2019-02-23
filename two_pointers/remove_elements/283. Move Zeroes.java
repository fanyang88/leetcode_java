/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

*/

/*
  two pointer, j always point to 0
  i point to next non-zero
  
  [0,1,0,3,12]
 i=0 j=0
 i=0  nums[i] === 0 i++
 i=1 nums[i]!==0  swap nums[j], nums[i] j++, i++  [1,0,0,3,12]
 i=2 j=1 i++
 i=3 j=1 nums[i]!==0  swap nums[j], nums[i] j++, i++  [1,3,0,0,12]
 i=4 j=2 nums[i]!==0  wap nums[j], nums[i] j++, i++  [1,3,12,0,0]
*/

class Solution {
  public void moveZeroes(int[] nums) {
      for(int i=0, j=0; i<nums.length; i++) {
          if(nums[i] != 0) {
              int temp = nums[i];
              nums[i] = nums[j];
              nums[j] = temp;
              j++;
          }
      }
      
  }
}
