/**
 Given an array of integers nums sorted in ascending order, 
 find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
 */

class Solution {
  public int[] searchRange(int[] nums, int target) {
      if(nums.length==0)  return new int[]{-1, -1};
      int lo = 0, hi = nums.length-1;
      while(lo < hi) {
          int mid = (lo+hi)/2;
          if(nums[mid] < target) {
              lo = mid+1;
          } else {
              hi = mid;
          }
      }
      if(nums[hi] != target)  return new int[]{-1, -1};
      int start = hi, end = hi;
      while(start >=0 && nums[start]== target) start--;
      while(end < nums.length && nums[end]== target) end++;
      return new int[]{start+1, end-1};
  }
}