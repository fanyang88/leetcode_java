/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 */

 /*
s = 7, nums = [2,3,1,2,4,3]
i=0 j=0 sum=2 <7 i++
i=1 j=0 sum=5< 7 i++
i=2 j=0 sum=6< 7 i++
i=3 j=0 sum=8> 7 while(sum>=7) j++ sum-=num[j]=6
i=4 j=1 sum=10>7 while(sum>=7) j++ sum-=num[j]=7 j++ sum-=num[j]=6
i=5 j=3 sum=9>7  while(sum>=7) j++ sum-=num[j]=7 j++ sum-=num[j]=6
*/

class Solution {
  public int minSubArrayLen(int s, int[] nums) {
      if(nums.length==0)  return 0;
      int sum = 0, minL = Integer.MAX_VALUE;//, j=0;
      for(int i=0, j=0; i<nums.length; i++) {
          sum += nums[i];
          while(sum >= s) {
              minL = Math.min(minL, i-j+1);
              sum -= nums[j];
              j++;
          }
      }
      return minL== Integer.MAX_VALUE ? 0 : minL;
  }
}



PYTHON VERSION:

class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        minlen = len(nums)+1
        right = 0
        left = 0
        t_sum = 0
        while right < len(nums):
            t_sum = t_sum + nums[right]
            # shrink window
            while t_sum >= target:
                minlen = min(minlen, right-left+1)
                t_sum = t_sum - nums[left]
                left = left+ 1
            
            right = right +1
        
        #corner case: target = 11, nums = [1,1,1,1,1,1,1,1] output 0
        if minlen == len(nums)+1:
            return 0
        return minlen
        
        
        
# # thoughts:
# [2,3,1,2,4,3] target =7
# 2: sum=2<7 right++
# 3: sum=5<7 right++
# 1: sum=6<7 right++
# 2: sum=8>7 update minlen, left++ sum=6 right++
# 4: sum=6+4=10 update minlen, left++ sum=10-3=7 update minlen left++ sum=7-1=6 right++
# 3: sum=6+3=9 update  minlen, left++ sum=9-2=7 update minlen left++ sum=7-4=3 right++ exit
    
