/*
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4 
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2 
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
*/



/*
two pointers:
 [1, -1, 5, -2, 3]  k=3
 [1, 0, 5, 3, 6]
 map[1] =0
 map[0-3] not exist map[0]=1
 map[5-3]=2 not exist map[5]=2
 map[3-3]=map[0] exist len=3-0=3  map[3]=3
 map[6-3]=map[3] exist len=4-3=1  
 
the range sum in constant time.
sum[i] means the sum from 0 to i inclusively
the sum from i to j is sum[j] - sum[i - 1] except that from 0 to j is sum[j].

j-i is equal to the length of subarray of original array. we want to find the max(j - i)
for any sum[j] we need to find if there is a previous sum[i] such that sum[j] - sum[i] = k
Instead of scanning from 0 to j -1 to find such i, we use hashmap to do the job in constant time.
However, there might be duplicate value of of sum[i] we should avoid overriding its index as we want the max j - i, so we want to keep i as left as possible.

e.g: [1, 1, 2, -1, 0]  k=1
     [1, 2, 4, 3, 3]
     map[0] = -1
     i=0 map[1-1] =map[0] exist len=0-(-1)=1   map[1]=0
     i=1 map[2-1] =map[1] exist len=(1-0)=1    map[2]=1
     i=2 map[4-1] not exist                    map[4]=2
     i=3 map[3-1]=map[2] exist 3-1=2           map[3]=3
     i=4 map[3-1]=map[2] exist 4-1=3           map[3] already exist, not update
*/

class Solution {
  public int maxSubArrayLen(int[] nums, int k) {
      if(nums.length ==0)  return 0;
      int sum = nums[0], maxL= 0;
      Map<Integer, Integer> map = new HashMap<>();
      for(int i=1; i<nums.length; i++) {
          sum+= nums[i];
          nums[i] = sum;
      }
      // // add this fake entry to make sum from 0 to j consistent
      map.put(0, -1); 
      for(int i=0; i<nums.length; i++) {
          if(map.containsKey(nums[i] - k)) {
              maxL = Math.max(maxL, i-map.get(nums[i] - k));
          }
          // keep only 1st duplicate as we want first index as left as possible, THE KEY!!!
          if(!map.containsKey(nums[i]))  map.put(nums[i], i);
      }
      return maxL;
  }
}
