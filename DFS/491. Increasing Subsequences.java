/*
Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
*/

class Solution {
  public List<List<Integer>> findSubsequences(int[] nums) {
      Set<List<Integer>> res = new HashSet<List<Integer>>();
      dfs(0, new ArrayList<Integer>(), res, nums);
      return new ArrayList<>(res);
  }
  
  public void dfs(int index, List<Integer> cur, Set<List<Integer>> res, int[] nums) {
      if(cur.size() >=2) {
          res.add(new ArrayList<>(cur));
      }
      for(int i=index; i<nums.length; i++) {
          if(cur.size()==0 || cur.get(cur.size()-1) <= nums[i]) {
              cur.add(nums[i]);
              dfs(i+1, cur, res, nums);
              cur.remove(cur.size()-1);
          }
      }
  }
}
