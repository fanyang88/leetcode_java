/**
 Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
 */

class Solution {
  public List<List<Integer>> permuteUnique(int[] nums) {
      List<List<Integer>> res = new LinkedList<>();
      Arrays.sort(nums);
      boolean[] seen = new boolean[nums.length];
      dfs(res, nums, new ArrayList<>(), seen);
      return res;
  }
  
  public void dfs(List<List<Integer>> res, int[] nums, List<Integer> cur, boolean[] seen) {
      if(cur.size() == nums.length) {
          res.add(new ArrayList<>(cur));
      }
      for(int i=0; i<nums.length; i++) {
          if(seen[i]) continue;
          seen[i] = true;
          cur.add(nums[i]);
          dfs(res, nums, cur, seen);
          cur.remove(cur.size()-1);
          seen[i] = false;
          while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
      }
  }
}
