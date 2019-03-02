/*
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example 1:
Input: [0,1]
Output: 2
Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
Example 2:
Input: [0,1,0]
Output: 2
Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
Note: The length of the given binary array will not exceed 50,000
*/

/*
    turn all 0 to be -1
    so the question change to find the longest subarray has sum = 0
    
    e.g: [0,1,0]
    map[0] = -1
    i=0 sum=-1 map[-1]=0 
    i=1 sum=0 since map[0] exist, means from map[0] to current i, (-1, 1) there is a subarray sum equals 0, len=2
    i=2 sum=-1 since map[-1] exist, means from map[-1] to current i, (0, 2) there is a subarray sum equals 0, len=2
        because (0, map[-1]) = val, (map[-1], i) = val means the inbetween sum =0
    

*/

class Solution {
  public int findMaxLength(int[] nums) {
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);
      int maxL = 0, sum=0;
      for(int i=0; i<nums.length; i++) {
          sum += nums[i] == 0 ? -1 : nums[i];
          if(map.containsKey(sum)) {
              maxL = Math.max(maxL, i - map.get(sum));
          } else {
              map.put(sum, i);
          }
      }
      return maxL;
  }
}
