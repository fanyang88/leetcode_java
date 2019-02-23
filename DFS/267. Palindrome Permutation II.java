/**
 Given a string s, return all the palindromic permutations (without duplicates) of it. 
 Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []

 */

class Solution {
  public List<String> generatePalindromes(String s) {
      List<String> res = new ArrayList<String>();
      Map<Character, Integer> map = new HashMap<>();
      int count=0;
      char mid = ' ';
      String cur = "";
      for(char c : s.toCharArray()) {
          map.put(c, map.getOrDefault(c, 0) +1);
      }
      for(char key: map.keySet()) {
          if(map.get(key) % 2==1) {
              count++;
              mid = key;
          }
      }
      if(count > 1)  return res;
      if(mid != ' ') {
          cur= ""+mid;
          map.put(mid, map.get(mid)-1);
      }
      dfs(map, cur, res, s.length());
      return res;
  }
  
  public void dfs(Map<Character, Integer> map, String cur, List<String> res, int len) {
      if(cur.length() == len) {
          res.add(cur);
          return;
      }
      for(char key: map.keySet()) {
          if(map.get(key) ==0)  continue;
          map.put(key, map.get(key)-2);
          cur = ""+key+cur+key;
          dfs(map, cur, res, len);
          map.put(key, map.get(key)+2);
          cur = cur.substring(1, cur.length()-1);
      }
  }
}
