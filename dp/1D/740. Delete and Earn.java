/*
Given an array nums of integers, you can perform operations on the array.

In each operation, you pick any nums[i] and delete it to earn nums[i] points. 
After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
You start with 0 points. Return the maximum number of points you can earn 
by applying such operations.

Example 1:

Input: nums = [3, 4, 2]
Output: 6
Explanation: 
Delete 4 to earn 4 points, consequently 3 is also deleted.
Then, delete 2 to earn 2 points. 6 total points are earned.
 

Example 2:

Input: nums = [2, 2, 3, 3, 3, 4]
Output: 9
Explanation: 
Delete 3 to earn 3 points, deleting both 2's and the 4.
Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
9 total points are earned.
 

Note:

The length of nums is at most 20000.
Each element nums[i] is an integer in the range [1, 10000].
 
*/

/*
Same as house robber: in house robber [0,2,3,4] we can either robber 0,3 or 2,4
 in this one, if we take nums[i] we can't take nums[i]+1 and nums[i]-1
 [0, 2*2, 3*3, 4*1]  we can either take 0+3*3 or 2*2+4*1
 [1,3,5]
 [1*1, 2*0, 3*1, 4*0, 5*1]  
 dp[0]=1  
 dp[1] = max(1, 2*0)=1 
 dp[2] = max(dp[0]+nums[2], dp[1])=3
 dp[3] = max(dp[1]+nums[3], dp[2])=3
 dp[4] = max(dp[2]+nums[4], dp[3])=8
*/

class Solution {
  public int deleteAndEarn(int[] nums) {
      int[] map = new int[10001];
      for(int n: nums) map[n] += n;
      
      int[] dp = new int[map.length];
      dp[0] = map[0];
      dp[1] = Math.max(map[0], map[1]);
      for(int i=2; i<map.length; i++) {
          dp[i] = Math.max(dp[i-2]+map[i], dp[i-1]);
      }
      return dp[dp.length-1];
  }
}
