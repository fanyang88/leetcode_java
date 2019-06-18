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
