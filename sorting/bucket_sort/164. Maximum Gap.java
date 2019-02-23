/**
 * 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

Return 0 if the array contains less than 2 elements.

Example 1:

Input: [3,6,9,1]
Output: 3
Explanation: The sorted form of the array is [1,3,6,9], either
             (3,6) or (6,9) has the maximum difference 3.
Example 2:

Input: [10]
Output: 0
Explanation: The array contains less than 2 elements, therefore return 0.
Note:

You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
Try to solve it in linear time/space.
 */

 /*
 use bucket sort, for example, [3,6,9,1] = > [1, 3, 6, 9]
 min= 1 max = 9 gap = ceil(max-min/ len-1) = ceil(8/3) = 3
 use two arrays to store the max and min in each range
 e:g 1 belong to bucket[0] since (i-min)/gap = 0, put in bucket 0
     3 belong to bucket[0]
     6 belong to bucket[1]
     9 belong to bucket[2]
bucket #   0    1    2 
max        3    6    9
min        1    6    9     we use previous max - current min to get the max gap
1 compare to 1, then 3 compare to 6, then 6 coompare to 9, at last 9 compare to 9
  
*/

class Solution {
  public int maximumGap(int[] nums) {
      if (nums == null || nums.length < 2)
      return 0;
      
      int maxV=Integer.MIN_VALUE, minV=Integer.MAX_VALUE;
      int[] maxBucket = new int[nums.length], minBucket = new int[nums.length];
      Arrays.fill(maxBucket, Integer.MIN_VALUE);
      Arrays.fill(minBucket, Integer.MAX_VALUE);
      
      for(int num: nums) {
          maxV = Math.max(maxV, num);
          minV = Math.min(minV, num);
      }
      if(minV == maxV)  return 0; // corner case: [1,1,1,1]
      
      int gap =(int)Math.ceil((double)(maxV - minV)/(nums.length - 1));
  
      for(int num: nums) {
          int index = (num - minV)/gap;
          maxBucket[index] = Math.max(maxBucket[index], num);
          minBucket[index] = Math.min(minBucket[index], num);
      }
      
      int res = Integer.MIN_VALUE, pre = minV;
      for(int i=0; i<nums.length; i++) {
          if(maxBucket[i] == Integer.MIN_VALUE) { // empty bucket, skip
              continue;
          }
          //  min in current bucket -  max in previous bucket
          res = Math.max(res, minBucket[i] - pre);
          pre = maxBucket[i];
      }
      res = Math.max(res, maxV - pre);
      return res;
  }
}
