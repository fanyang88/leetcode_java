/**
 * 
 Given an array nums of n integers and an integer target, 
 are there elements a, b, c, and d in nums such that a + b + c + d = target? 
 Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

class Solution {
  public List<List<Integer>> fourSum(int[] nums, int target) {
      int n = nums.length;
      List<List<Integer>> res = new LinkedList<>(); 
      Arrays.sort(nums);
      for(int i=0; i<n-3; i++) {
          int c = nums[i];
          for(int j=i+1; j<n-2; j++) {
              int d = nums[j], l=j+1, r=n-1;
              while(l < r) {
                  if(c+d+nums[l] + nums[r] == target) {
                      res.add(Arrays.asList(c, d, nums[l], nums[r]));
                      l++;
                      r--;
                      while(nums[l]== nums[l-1] && l<r) l++;
                      while(nums[r]== nums[r+1] && l<r) r--;
                  } else if(c+d+nums[l] + nums[r] < target) {
                      l++;
                  } else {
                      r--;
                  }
              }
              while(nums[j] == nums[j+1] && j+1 < n-2) j++;
          }
          while(nums[i] == nums[i+1] && i+1 < n-3) i++;
      }
      return res;
  }
}