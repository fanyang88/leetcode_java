/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.

*/

/*
    Two pointers:
    [2, 6, 4, 8, 10, 9, 15]
    pos1 go from left to right, max is 2would point to the last one that not equal to max which is 9, since at 9, the max is 10
        while 9!=max so pos1=>9
    pos2 would point to the last one from right that not equal to min which is 4, since at 4, 
        min=8, while 4!=min, so pos2=>4
*/
    
class Solution {
  public int findUnsortedSubarray(int[] nums) {
      int pos1=-1, pos2=0, max = Integer.MIN_VALUE, min= Integer.MAX_VALUE;
      
      for(int i=0, j=nums.length-1; i<nums.length; i++, j--) {
          min = Math.min(nums[j], min);
          if(nums[j] != min) pos2=j;
          
          max = Math.max(nums[i], max);
          if(nums[i] != max) pos1=i;
      }
      
      return pos1 - pos2 +1;
  }
}
