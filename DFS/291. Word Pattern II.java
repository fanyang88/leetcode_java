/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Example 1:

Input: pattern = "abab", str = "redblueredblue"
Output: true
Example 2:

Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
Output: true
Example 3:

Input: pattern = "aabb", str = "xyzabcxzyabc"
Output: false
Notes:
You may assume both pattern and str contains only lowercase letters.
*/

class Solution {
  public boolean wordPatternMatch(String pattern, String str) {
      int m= pattern.length(), n =str.length();
      Map<Character, String> map = new HashMap<>();
      Set<String> set = new HashSet<>();
      return dfs(0, 0, pattern, str, m, n, map, set);
  }
  
  public boolean dfs(int pp, int ps, String pattern, String str, int m, int n, Map<Character, String> map, Set<String> set) {
      if(pp==m && ps==n) return true;
      if(pp==m || ps==n) return false;
      char cur = pattern.charAt(pp);
      if(map.containsKey(cur)) {
          // get the value
          String value = map.get(cur);
          // check if str from ps start with value
          if(!str.startsWith(value, ps)) return false;
          return dfs(pp+1, ps+value.length(), pattern, str, m, n, map, set);
      } else { // we don't have this mapping, 
          for(int i=ps; i<n; i++) {
              String value = str.substring(ps, i+1);
              if(set.contains(value)) continue; // tried
              set.add(value);
              map.put(cur, value);
              if(dfs(pp+1, ps+value.length(),  pattern, str, m, n, map, set)) return true;
              set.remove(value);
              map.remove(cur);
          }
          
      }
      return false;
  }
}
