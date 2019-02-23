/**
 Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
 */

class Solution {
  public List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> res = new LinkedList<>();
      res.add(new ArrayList<>());
      int n = nums.length;
      if(n==0)  return res;
      dfs(0, nums, new ArrayList<>(), res);
      return res;
  }
  
  public void dfs(int index, int[] nums, List<Integer> cur, List<List<Integer>> res) {
      if(index >= nums.length) 
          return;
      
      for(int i=index; i<nums.length; i++) {
          cur.add(nums[i]);
          res.add(new ArrayList<>(cur));
          dfs(i+1, nums, cur, res);
          cur.remove(cur.size() -1);
      }
  }
}
