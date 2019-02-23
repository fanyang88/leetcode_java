/**
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).

You are given a target value to search. If found in the array return true, otherwise return false.

Example 1:

Input: nums = [2,5,6,0,0,1,2], target = 0
Output: true
Example 2:

Input: nums = [2,5,6,0,0,1,2], target = 3
Output: false
Follow up:

This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
Would this affect the run-time complexity? How and why?
 */

 /*
    there is two type: 
    1.[4,5,6,7,0,1,2], sepecial case: [3, 1] should belong to this case
    2.[5,1,2,3,4],
    for type 1:  since A[s] < A[mid], if A[s] <= target < A[mid] search on left, otherwise search on right 
    for type 2:  since A[s] > A[mid], if A[mid] < target =< A[e] search on right, otherwise search on right 
 
*/

class Solution {
  public boolean search(int[] nums, int target) {
      if(nums.length==0)  return false;
      // same as seach in roated sort array, except when nums[lo] = nums[mid] = nums[hi]   lo++, hi--
      int lo= 0, hi=nums.length-1;
      while(lo < hi) {
          int mid = (lo+hi) /2;
          if(nums[mid] == target) return true;
          if(nums[lo] == nums[mid] && nums[hi] == nums[mid]) {
              lo++;
              hi--;
          } else if(nums[lo] <= nums[mid]) {
              if(nums[lo]<= target && target < nums[mid]) { // search on left
                  hi = mid;
              } else {
                  lo = mid+1;
              }
          } else {
              if(nums[mid]< target && target <= nums[hi]) {
                  lo = mid+1;
              } else {
                  hi = mid;
              }
          }
      }
      return nums[hi] == target; // hi point to potential target
  }
}
