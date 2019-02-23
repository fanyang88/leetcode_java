/**
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

 /*
[4,5,6,7,0,1,2]  if nums[mid] >= nums[start]
                    if(nums[start] <= target < nums[mid])  hi = mid; // search on left
                    else search in right, lo = mid+1
[4,5,6,0,1,2,3]  if nums[mid] < nums[start] 
                    if(nums[mid] < target <= nums[end])  lo = mid+1; // search on right
                    else search in right, hi = mid;
*/

class Solution {
  public int search(int[] nums, int target) {
      if(nums.length ==0)  return -1;
      int lo = 0, hi = nums.length-1;
      while(lo < hi) {
          int mid = lo + (hi-lo)/2;
          if(nums[mid] == target) return mid;
          // this is the key, case :[3, 1] should come to here 
          if(nums[mid] >= nums[lo]) {
              if(target < nums[mid] && target >= nums[lo]) {
                  hi = mid;// search on left
              } else {
                  lo= mid+1;
              }
          } else {
              if(target > nums[mid] && target <= nums[hi]) {
                  lo = mid+1;// search on right
              } else {
                  hi = mid;
              }
          }
      }
      return nums[hi] == target ? hi : -1;
  }
}
