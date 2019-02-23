/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2
 */


/*
        [2,2,1,1,1,2,2]
        i=0 major=2 count=1
        i=1 major=2 count=2
        i=2 major=2 count=1
        i=3 major=2 count=0
        i=4 major=1 count=1
        i=5 major=1 count=0
        i=6 major=2 count=1  major=2
*/
class Solution {
  public int majorityElement(int[] nums) {
      int major= nums[0], count=1;
      for(int i=1; i<nums.length; i++) {
          if(nums[i] == major) {
              count++;
          } else if(count==0) {
              count=1;
              major= nums[i];
          } else {
              count--;
          }
      }
      return major;
  }
}
