/*
Given a string containing digits from 2-9 inclusive, 
return all possible letter combinations that the number could represent.
A mapping of digit to letters (just like on the telephone buttons) is given below. 
Note that 1 does not map to any letters.

Example:
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
*/
class Solution {
    
  private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
  public List<String> letterCombinations(String digits) {
      List<String> res= new ArrayList<String>();
      if(digits.length() == 0) return res;
      dfs(0, digits, "", res);
      return res;
  }
  
  public void dfs(int level, String digits, String cur, List<String> res) {
      if(level == digits.length()) {
          res.add(cur);
          return;
      }
      String letters =KEYS[digits.charAt(level) - '0'];
      for(int i=0; i<letters.length(); i++) {
          dfs(level+1, digits, cur+letters.charAt(i), res);
      }
  }
}
