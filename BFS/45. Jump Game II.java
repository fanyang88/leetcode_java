/**
 Given an array of non-negative integers, 
 you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.
 */

 /*
BFS
[2,3,1,1,4]
start from 0, step=1, maxend = (2+0) = 2 start = 1, end = 2
start from 1 and 2, step++=2: from 1: maxEnd= max(2, 1+3)=4 
                    from 2: maxEnd = max(2+1, 4)=4 since 4 is the last index, return step


*/
class Solution {
  public int jump(int[] nums) {
      int step=0, n=nums.length, end=0, start=0;
      while(end < n-1) {
          step++;
          int maxEnd = end+1;
          for(int i=start; i<=end; i++) {
              if(nums[i] + i >= n-1) return step;
              maxEnd = Math.max(maxEnd, nums[i] + i);
          }
          // This is the key!
          start = end+1; // start marks the next place if take 1 step
          end =maxEnd;  // end marks the largest place it can go
      }
      return step;
  }
}