/**
 Given a collection of candidate numbers (candidates) and a target number (target), 
 find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
 */

class Solution {
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
      List<List<Integer>> res = new LinkedList<>();
      Arrays.sort(candidates);
      dfs(candidates, target, res, new ArrayList<>(), 0, 0);
      return res;
  }
  
  public void dfs(int[] nums, int target, List<List<Integer>> res, List<Integer> cur, int sum, int index) {
      if(sum >= target) {
          if(sum == target)  res.add(new ArrayList<>(cur));
          return;
      }
      for(int i=index; i<nums.length; i++) {
          cur.add(nums[i]);
          dfs(nums, target, res, cur, sum+nums[i] , i+1);// not use the same element
          cur.remove(cur.size() - 1);
          // Skip duplicate elements
          while( i+1 < nums.length && nums[i] == nums[i+1]) i++;
      }
  }
}
