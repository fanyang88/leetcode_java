/**
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
/*
        [100, 4, 200, 1, 3, 2]
        examine each elements, and expanding the length if the set has the -1 and +1 
        for each element examine, we need to remove it from the set, as well as the -1 and +1
*/

class Solution {
  public int longestConsecutive(int[] nums) {
      int maxV = 0;
      Set<Integer> set = new HashSet<>();
      for(int i=0; i<nums.length; i++) set.add(nums[i]);
      
      for(int num : nums) {
          if(!set.contains(num))  continue;
          set.remove(num);
          int small = num-1, big = num+1;
          while(set.contains(small)) {
              set.remove(small);
              small--;
          }
          while(set.contains(big)) {
              set.remove(big);
              big++;
          }
          maxV = Math.max(maxV, big-small-1);
      }
      return maxV;
  }
}