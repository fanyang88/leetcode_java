/*
Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation: 
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
*/

class Solution {
  public int findShortestSubArray(int[] nums) {
      // find the one with most freq
      // find the start and end of that element with most freq
      int degree = 0, range = nums.length;
      Map<Integer, int[]> map = new HashMap<>();
      for(int i=0; i<nums.length; i++) {
          if(map.containsKey(nums[i])) {
              int[] temp =map.get(nums[i]);
              temp[0]++;
              temp[2] = i;
          } else {
              // first is freq, sec is starting index, third is ending index
              map.put(nums[i], new int[] {1, i, i}); 
          }
      }
      for(int key: map.keySet()) {
          if(degree <= map.get(key)[0]) {
              if(degree < map.get(key)[0]) range = map.get(key)[2] - map.get(key)[1]+1;
              else range = Math.min(range, map.get(key)[2] - map.get(key)[1]+1);
              degree = map.get(key)[0];
          }
      }
      return range;
  }
}
