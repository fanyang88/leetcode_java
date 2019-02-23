/*
Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.

Example 1:
Input: [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
    After flipping, the maximum number of consecutive 1s is 4.
Note:

The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
Follow up:
What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?
*/

/*
        Two pointer
        [1,0,1,1,0, 1],  when get length i point to 0 or the out of bountry, j point to the first valid 1
        i=0 j=0  
        i=1 j=0 since nums[i]=0 count++=1
        i=2 j=0 
        i=3 j=0 
        i=4 j=0 since nums[i]=0 count++=2  get length
            count should <=1 we move j forward till count=1 j=2
        i=5 j=2 count=1 continue
        after loop i=6 i-j=4 
*/

class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
      int i=0, j=0, count=0, maxL=0;
      for(; i<nums.length; i++) {
          if(nums[i] ==0)  count++;
          maxL = Math.max(maxL, i-j);
          while(count > 1) {
              if(nums[j]==0) count--;
              j++;
          }
      }
      maxL = Math.max(maxL, i-j);
      return maxL;
  }
}
