/*
Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].
*/

/*
        backtracking
        we use a count to denote:
        if there is a left (, count++
        if there is a right ), count--, if count < 0 return false
        since * can be ( or ) or "", we need to check those situations for * as well, can be done by backtracking
        

*/

class Solution {
  public boolean checkValidString(String s) {
      return dfs(s, 0,0,0);
  }
  
  public boolean dfs(String s, int left, int right, int index) {
      if(left < right)  {
          return false;
      }
      if(index == s.length()) return left ==right;
      char c= s.charAt(index);
      if(c == '(') {
          return dfs(s, left+1, right, index+1);
      } else if(c==')') {
              return dfs(s, left, right+1, index+1);
      } else {
          return dfs(s, left+1, right, index+1) ||  // * as (
                 dfs(s, left, right+1, index+1) ||  // * as )
                 dfs(s, left, right, index+1);      // * as ""
      }
  }
}