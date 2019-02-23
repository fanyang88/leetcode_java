/**
 Given an array nums of n integers and an integer target, 
 find three integers in nums such that the sum is closest to target. 
 Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */

// similar with 3 sum, difference is we need to record it everytime
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE, n=nums.length, res= 0;
        Arrays.sort(nums);
        for(int i=0; i<n-2; i++) {
            int c = nums[i], l=i+1, r=n-1;
            while(l<r) {
                int sum = c+nums[l]+nums[r];
                if(sum == target) {
                    return target;
                } else if(sum < target) {
                    l++;
                } else {
                    r--;
                }
                if(diff > Math.abs(sum-target)) {
                    diff = Math.abs(sum-target);
                    res = sum;
                }
            }
        }
        return res;
    }
}