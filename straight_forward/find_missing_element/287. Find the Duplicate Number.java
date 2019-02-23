/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

/*
[1,3,4,2,2]
 i=0, since nums[0] = 1, and nums[abs(1)] = 3 > 0 we make nums[1] to be neg = -3
 i=1, since nums[1] = -3, and nums[abs(-3)] = 2 > 0 we make nums[3] to be neg = -2  [1,-3,4,-2,2]
 i=2, since nums[2] = 4, and nums[abs(4)] = 2 > 0 we make nums[4] to be neg = -2  [1,-3,4,-2,-2]
 i=3, since nums[3] = -2, and nums[abs(-2)] = 4 > 0 we make nums[2] to be neg = -4  [1,-3,-4,-2,-2]
 i=4, since nums[4] = -2, and nums[abs(-2)] = -4 < 0 we found the dup, since there is already a 2 before to make this one tobe -4
 
 The point is to use each number as index, and make the pointed number to be neg, if it is already a neg, there is dup, and the dup is the index, since this index has been visited.
*/

class Solution {
  public int findDuplicate(int[] nums) {
      for(int i=0; i<nums.length; i++) {
          int index = Math.abs(nums[i]); 
          if(nums[index] < 0) return index;
          nums[index] = -nums[index];
      }
      return -1;
  }
}