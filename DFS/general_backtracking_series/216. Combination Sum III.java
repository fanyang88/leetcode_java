/**
 * Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

class Solution {
  public List<List<Integer>> combinationSum3(int k, int n) {
      List<List<Integer>> res = new LinkedList<>();
      dfs(n, res, new ArrayList<>(), 0, 1, k);
      return res;
  }
  
    public void dfs(int target, List<List<Integer>> res, List<Integer> cur, int sum, int index, int k) {
      if(cur.size()==k) {
          if(sum == target)  res.add(new ArrayList<>(cur));
          return;
      }
      for(int i=index; i<=9; i++) {
          cur.add(i);
          dfs(target, res, cur, sum+i , i+1, k);// not use the same element
          cur.remove(cur.size() - 1);
      }
    }
}
