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


PYTHON ***************************************************************

class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()
        res = nums[0]+nums[1]+nums[2]
        
        for i in range(0, len(nums)-2):
            if i>0 and nums[i]==nums[i-1]: continue
            l, r = i+1, len(nums)-1
            while l<r:
                csum = nums[l]+nums[r]+nums[i]
                if csum == target:
                    return target
            
                if csum < target:
                    l+=1
                else:
                    r-=1
                if abs(target-csum) < abs(target - res):
                    res = csum
                
        return res

        
# Thoughts similar to 3 sum, but we need to use diff to get the min difference 



