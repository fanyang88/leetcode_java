/**
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

 */

 /*
        25525511135
        /          |    |    \   ...
    2,5525511135
    /      \
   5,525511135
*/

class Solution {
  public List<String> restoreIpAddresses(String s) {
      // split the string at every posible position
      List<String>  res= new ArrayList<String>();
      dfs(s, 0, res, "", 0);
      return res;
  }
  
  public void dfs(String s, int index, List<String> res, String cur, int step) {
      if(step > 3) {
          if(index == s.length())  res.add(cur.substring(0, cur.length()-1));
          return;
      }
      for(int len=1; len<=3; len++) {
         if(index + len > s.length()) break;
          String sub = s.substring(index, index+len);
          if(sub.charAt(0) == '0' && len > 1) continue;
          if(Integer.parseInt(sub) > 255) continue;
          dfs(s, index+len, res, cur+sub+'.', step+1);
      }
  }
}
