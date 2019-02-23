/**
 * 
 Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 */

class Solution {
  public List<List<Integer>> getFactors(int n) {
      List<List<Integer>> res = new ArrayList<List<Integer>>();
      if(n<2) return res;
      dfs(n, 2, res, new ArrayList<Integer>());
      return res;
  }
  
  public void dfs(int n, int factor, List<List<Integer>> res, List<Integer> cur) {
      if(n==1) {
          if(cur.size() > 1) 
              res.add(new ArrayList<Integer>(cur));  // not only contain itself
          return;
      }
      for(int i=factor; i<=n ;i++) {
          if(n % i !=0 ) continue;
          cur.add(i);
          dfs(n/i, i, res, cur);
          cur.remove(cur.size()-1);
      }
  }
}