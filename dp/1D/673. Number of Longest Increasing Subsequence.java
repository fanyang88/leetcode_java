/*
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
*/

/*
    DP
    use two arrays, one record the max length to nums[i]
    the other record how many max length to nums[i]
    [1,3,5,4,7]
    i=0 dp[0]=1
    i=1 since nums[0] < nums[1] dp[1] = dp[0]+1=2
    i=2 since nums[2] < nums[1] dp[2] = dp[1]+1=3
        since nums[2] < nums[0] dp[2] = dp[0]+1=2 =3
    i=3 ...
    so if nums[j] < nums[i] and dp[j]+1 >= dp[i], dp[i] = dp[j]+1

    the trick part is:
    if nums[j] < nums[i] and dp[j]+1 > dp[i]
        say if dp[j] = 3 dp[i] = 3 means the longest to i is 4, 
        if count[j]=2 means there are 2 longest up to i as well, count[i] = 2

    if nums[j] < nums[i] and dp[i] = dp[j]+1
        say if dp[j] = 2 dp[i] = 3 means the longest to i is 3,
        if count[j]=2 means there are 2 longest up to i as well, should plus what it already has:
        count[i] = count[i] + count[j]  
        there is alreay other sequence from k to i has same length as j to i, we need to add in such case
*/

class Solution {
  public int findNumberOfLIS(int[] nums) {
      int n = nums.length, res=0, max = 1;
      if(n==0) return res;
      int[] count = new int[n], dp = new int[n];
      Arrays.fill(count, 1);
      Arrays.fill(dp, 1);
      
      for(int i=1; i<n; i++) {
          for(int j=0; j<i; j++) {
              if(nums[j] >= nums[i] || dp[j] + 1 < dp[i])  continue;
              count[i] = (dp[i] < dp[j]+1)? count[j] : count[i] + count[j]; // // This is the key
              dp[i] = dp[j]+1;
              
          }
          max = Math.max(dp[i], max);
      }
      for(int i=0; i<n; i++) {
          if(dp[i] == max) res+=count[i];
      }
      return res;
  }
}
