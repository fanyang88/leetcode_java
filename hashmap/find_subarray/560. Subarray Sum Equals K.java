/*
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
*/

/*
        [1,1,1] -> [1,2,3]  k = 2
        since map[0]= 1
        i=0 since map[1-k]not exist do nothing map[1] =0  
        i=1 since map[0] exist find an subarray 2-0  map[2] = 1 
        i=2 since map[3-2] = map[1] exist find another subarray map[3] =2 
        
        The key is to add count before set map
*/

class Solution {
  public int subarraySum(int[] nums, int k) {
      int count=0, sum=0;
      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      for(int i=0; i<nums.length; i++) {
          sum += nums[i];
          if(map.containsKey(sum - k)) count+= map.get(sum-k);
          map.put(sum, map.getOrDefault(sum, 0)+1);
      }
      return count;
  }
}
