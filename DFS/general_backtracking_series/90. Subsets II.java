/**
 Given a collection of integers that might contain duplicates, nums, 
 return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
 */

class Solution {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
      // same as LC78 except skip dups
      List<List<Integer>> res = new LinkedList<>();
      res.add(new ArrayList<>());
      if(nums.length==0)  return res;
      
      Arrays.sort(nums); // remember to sort
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
        while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
    }
  }
}
