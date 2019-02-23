/**
 * 
 Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
 */

 /*
              ''
            /  |  \
           1   2    3
         /  \  | \  | \
        2   3  1  3 2  1
        |   |  |  | |  |
        3   2  3  2 1  2
*/

class Solution {
  public List<List<Integer>> permute(int[] nums) {
      List<List<Integer>> res = new LinkedList<>();
      dfs(nums, new ArrayList<>(), res);
      return res;
  }
  
  public void dfs(int[] nums, List<Integer> cur, List<List<Integer>> res) {
      if(cur.size() == nums.length) {
          res.add(new ArrayList<>(cur));
          return;
      }
      for(int i=0; i<nums.length; i++) {
          if(cur.contains(nums[i]))  continue;
          cur.add(nums[i]);
          dfs(nums, cur, res);
          cur.remove(cur.size()-1);
      }
  }
}