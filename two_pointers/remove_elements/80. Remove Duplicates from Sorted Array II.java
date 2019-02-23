/**
 Given a sorted array nums, remove the duplicates in-place 
 such that duplicates appeared at most twice and return the new length.

Do not allocate extra space for another array, 
you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,1,2,2,3],

Your function should return length = 5, 
with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,1,2,3,3],

Your function should return length = 7, 
with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.

It doesn't matter what values are set beyond the returned length.
 */


/*
 
  [1,1,1,2,2,3],
       i
       j
i=2, j=2 num[i] == nums[j-2] continue
i=3, j=2 num[i] != nums[j-2] num[j] = num[i]  
[1,1,2,2,2,3],
       i
       j 
i=4 j=3 since num[i] != nums[j-2] nums[j]=2 j++=4
[1,1,2,2,2,3],
         i
         j
i=5 since num[i] != nums[j-2] nums[j]=3 j++=5 which is the answer
*/

class Solution {
  public int removeDuplicates(int[] nums) {
      int j=2;
      for(int i=2; i<nums.length; i++) {
          if(nums[i] != nums[j-2]) {
              nums[j] = nums[i];
              j++;
          }
      }
      return j;
  }
}

