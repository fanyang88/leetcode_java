/*
Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 

Example 1:

Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
Output: True
Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 

Note:

1 <= k <= len(nums) <= 16.
0 < nums[i] < 10000.
*/

/*
    DP:
    [4, 3, 2, 3, 5, 2, 1]  sum = 
    
*/

class Solution {
  public boolean canPartitionKSubsets(int[] nums, int k) {
      if(k==1)  return true;
      int s = 0;
      for(int n: nums) s +=n;
      if(s % k !=0) return false;
      
      int[] sums = new int[k];
      return dfs(0, sums, nums, s/k, k);
  }
  
  public boolean dfs(int index, int[] sums, int[] nums, int target, int k) {
      if(index == nums.length) {
          for(int s: sums) {
              if(s != target)  return false;
          }
          return true;
      }
      
      int cur = nums[index];
      for(int i=0; i<k; i++) {
          if(sums[i] + cur > target) continue;  // pruning
          sums[i] += cur;
          if(dfs(index+1, sums, nums, target, k)) return true;
          sums[i] -= cur;
      }
      return false;
  }
}
