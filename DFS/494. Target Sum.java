/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

    /*
             [1, 1, 1, 1, 1]
               
                     0
               /            \
              0+1           0-1
            /      \       /    \
          0+1-1  0+1+1  0-1-1   0-1+1
          ....
              
    
    */

    class Solution {
      int res=0;
      public int findTargetSumWays(int[] nums, int S) {
          int[] res = new int[1]; 
          dfs(nums, 0, 0, S, res);
          return res[0];
      }
      
      public void dfs(int[] nums, int index, int sum, int S, int[] res) {
          if(index == nums.length) {
              if(sum==S)  res[0] ++;
          } else {
              dfs(nums, index+1, sum+nums[index], S, res);
              dfs(nums, index+1, sum-nums[index], S, res);
          }
      }

    public void dfs(int[] nums, int index, int sum, int S) {
        if(index == nums.length) {
            if(sum==S)  count ++;
        } else {
            dfs(nums, index+1, sum+nums[index], S);
            dfs(nums, index+1, sum-nums[index], S);
        }
    }
  }
  