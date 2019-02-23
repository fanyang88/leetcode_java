/*
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/

class Solution {
  Map<Integer, List<Integer>> map;
  public Solution(int[] nums) {
      map = new HashMap<>();
      for(int i=0; i<nums.length; i++) {
          if(!map.containsKey(nums[i])) {
              map.put(nums[i], new ArrayList<>());
          }
          map.get(nums[i]).add(i);
      }
  }
  
  public int pick(int target) {
      if(map.get(target)==null)  return -1;
      int rand = (int)(Math.random() * map.get(target).size());
      return map.get(target).get(rand);
  }
}

/**
* Your Solution object will be instantiated and called as such:
* Solution obj = new Solution(nums);
* int param_1 = obj.pick(target);
*/
