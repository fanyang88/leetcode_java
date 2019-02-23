/*
Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.

Example:

nums = [1, 2, 3]
target = 4

The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
 

Follow up:
What if negative numbers are allowed in the given array?
How does it change the problem?
What limitation we need to add to the question to allow negative numbers?
*/


/*
  coin change II like problem
  
  e.g: [1,2,3]   target =4
  dp[0] = 1
  dp[1] = dp[1-nums[0]]=dp[0]=1
  dp[2] = dp[2-nums[0]]=dp[2-1]=1 + 
          dp[2-nums[1]]=dp[2-2]=1 =2  means 2 can be consisit of 1+1 & 2
  dp[3] = dp[3-nums[0]]=dp[3-1]=2 +    (1,1,1),(1,2)
          dp[3-nums[1]]=dp[3-2]=1 +    (2,1)
          dp[3-nums[2]]=dp[3-3]=1 =4   (3)
  
  return dp[4]
*/
class Solution {
  public int combinationSum4(int[] nums, int target) {
      Arrays.sort(nums);
      int[] dp = new int[target+1];
      dp[0] = 1;
      for(int i=0; i<=target; i++) {
          for(int j=0; j<nums.length; j++) {
              if(nums[j] > i)  break;
              dp[i] +=dp[i-nums[j]];
          }
      }
      return dp[target];
  }
}
