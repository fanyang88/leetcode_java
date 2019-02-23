/**
 Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 */



/*
        1           2             3            4
    /   |  \     /   |            |      
    2   3   4    3   4            4


*/
class Solution {
  public List<List<Integer>> combine(int n, int k) {
      List<List<Integer>> res = new LinkedList<>();
      dfs(1, n, k, new ArrayList<>(), res);
      return res;
  }
  
  public void dfs(int index, int n, int k, List<Integer> cur, List<List<Integer>> res) {
      if(cur.size() == k) {
          res.add(new ArrayList<>(cur));
          return;
      }
      for(int i=index; i<=n; i++) {
          cur.add(i);
          dfs(i+1, n, k, cur, res);
          cur.remove(cur.size()-1);
      }
  }
}