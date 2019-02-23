/**
 Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
 */

class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
      if(strs.length ==0)  return new ArrayList<List<String>>();
      Map<String, List<String>> map = new HashMap<String, List<String>>();
      for(String s : strs) {
          char[] c_str= s.toCharArray();
          Arrays.sort(c_str);
          String val = String.valueOf(c_str);
          if(!map.containsKey(val)) {
              map.put(val, new ArrayList<String>());
          }
          map.get(val).add(s);
      }
      return new ArrayList<List<String>>(map.values());
  }
}
