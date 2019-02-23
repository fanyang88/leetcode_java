/*
Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has, please find out a way you can make one square by using up all those matchsticks. You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.
*/

/*
    
                                        []
          /                   |                      |                      \
       a[0] ...            .a[0]..                 ..a[0].                   ...a[0]
  /    |   |     \       /    |   |     \      /    |   |     \          /    |   |     \
a[0]   .   .      .   a[1]   a[0]  .      .   .   a[1]  a[0]     .      .     .  a[1]  a[0]     
+a[1]
...

once we have sum1, sum2, sum3, sum4 === len, we find one, if there is any sum1-4 > len, stop search from this oppssibility

*/

class Solution {
  public boolean makesquare(int[] nums) {
      if(nums.length==0) return false;
      int sum = 0;
      Arrays.sort(nums);  // This is the key!!!
      for(int n : nums) sum +=n;
      if(sum % 4 !=0)  return false;
      return dfs(0, 0, 0, 0, 0, sum/4, nums);
  }
  
  public boolean dfs(int index, int sum0, int sum1, int sum2, int sum3, int target, int[] nums) {
      if(sum0 > target || sum1 > target ||sum2 > target || sum3 > target)  return false;
      if(nums.length == index) {
          if(sum0==target && sum1==target && sum2==target)  return true;
          return false;
      } else {
             return dfs(index+1, sum0+nums[index], sum1, sum2, sum3, target, nums) ||
                  dfs(index+1, sum0, sum1+nums[index], sum2, sum3, target, nums) ||
                  dfs(index+1, sum0, sum1, sum2+nums[index], sum3, target, nums) ||
                  dfs(index+1, sum0, sum1, sum2, sum3+nums[index], target, nums);
      }
  }
}