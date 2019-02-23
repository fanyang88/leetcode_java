/**
 Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
 Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
 */

 // sort first, then one 
class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
      int n = nums.length;
      List<List<Integer>> res = new LinkedList<>(); 
      Arrays.sort(nums);
      for(int i=0; i<n-2; i++) {
          int c = nums[i], l=i+1, r=n-1;
          while(l < r) {
              if(c+nums[l]+nums[r] == 0) {
                  // record result
                  res.add(Arrays.asList(c, nums[l], nums[r]));
                  l++;
                  r--;  // This is the key
                  // if current one equals previous one
                  while(nums[l] == nums[l-1] && l<r) l++;
                  while(nums[r] == nums[r+1] && l<r) r--;
              } else if(c+nums[l]+nums[r] < 0) {
                  l++;
              } else {
                  r--;
              }
          }
          while(nums[i] == nums[i+1] && i+1<n-2) i++;
      }
      return res;
  }
}
