/**
 Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */

 /*
     [3,5,2,1,6,4]
 i=0 j=1 no problem proceed
 i=1 j=2 no problem proceed
 i=2 j=3 since nums[2] should < nums[3]  swap nums[3] with nums[2]

*/

class Solution {
  public void wiggleSort(int[] nums) {
      boolean isUp = true;
      for(int i=1; i<nums.length; i++) {
          if(isUp) {
              if(nums[i] < nums[i-1]) swap(i, i-1, nums);
          } else {
              if(nums[i] > nums[i-1]) swap(i, i-1, nums);
          }
          isUp = isUp ? false: true;
      }
  }
  
  public void swap(int i, int j, int[] nums) {
      int temp= nums[i];
      nums[i] = nums[j];
      nums[j] = temp;
  }
}
