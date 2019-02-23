/**
 * 
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), 
 find all unique combinations in candidates where the candidate numbers sums to target.
The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
 */
/*
      2 .  3 .   6 .  7(~)
     / \   
    2   3
   / \  
  2   3(~)
   
*/

class Solution {
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
      List<List<Integer>> res = new LinkedList<>();
      Arrays.sort(candidates);
      dfs(candidates, target, res, new ArrayList<>(), 0, 0);
      return res;
  }
  
  public void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> cur, int sum, int index) {
      if(sum >= target) {
          if(sum == target)  res.add(new ArrayList<>(cur));
          return;
      }
      for(int i=index; i<candidates.length; i++) {
          cur.add(candidates[i]);
          // not i+1 since we can reuse same elements.
          dfs(candidates, target, res, cur, sum + candidates[i], i);
          cur.remove(cur.size()-1);
      }
  }
}
