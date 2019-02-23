/**
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */


/*
e.g n= 3
                (
             /      \
            (        )
          /   \      |
         (     ) .   (
         |    /  \   /  \
         )   (    )  (   )
         ...
         
when left < 3, we can add (
when left>right, we can add )
         
*/
class Solution {
  public List<String> generateParenthesis(int n) {
      List<String> res= new ArrayList<String>();
      dfs(0, 0, res, n, "");
      return res;
  }
  
  public void dfs(int left, int right, List<String> res, int n, String cur) {
      if(left==n && right == n) {
          res.add(cur);
          return; 
      }
      if(left < n)  dfs(left+1, right, res, n, cur+"(");
      if(right < left) dfs(left, right+1, res, n, cur+")");
  }
}