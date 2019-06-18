/**
 Given a string, we can "shift" each of its letter to its successive letter, 
 for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, 
group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output: 
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
 */

 // we can use the difference of s[i]- s[i-1] to be key
 // e.g: abc => 1:1  bcd:1:1    az=> 25   ba=>-1+26=25
    

class Solution {
  public List<List<String>> groupStrings(String[] strings) {
      List<List<String>> res = new ArrayList<List<String>>();
      HashMap<String, List<String>> map = new HashMap<String, List<String>>();
      for(String s: strings) {
          String key = getKey(s);
          if(!map.containsKey(key)) map.put(key, new ArrayList<String>());
          map.get(key).add(s);
      }
      for(String key: map.keySet()) {
          List<String> list = map.get(key);
          Collections.sort(list);
          res.add(list);
      }
      return res;
  }
  
  public String getKey(String s) {
      String res = "";
      for(int i=1; i<s.length(); i++) {
          int diff = s.charAt(i) - s.charAt(i-1);
          if(diff < 0) diff+=26;
          res += "."+diff;
      }
      return res;
  }
}
