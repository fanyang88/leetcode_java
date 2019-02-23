/*
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
*/

class Solution {
  public List<Integer> findAnagrams(String s, String p) {
      List<Integer> res = new ArrayList<Integer>();
      if(s.length() < p.length()) return res;
      int[] map = new int[26];
      
      for(int i=0; i<p.length(); i++) {
          map[p.charAt(i)-'a']--;
          map[s.charAt(i)-'a']++;
      }
      if(isAllZero(map)) res.add(0);
      for(int i=p.length(), j=0; i<s.length(); i++, j++) {
          map[s.charAt(j)-'a']--;
          map[s.charAt(i)-'a']++;
          if(isAllZero(map)) res.add(j+1);
      }
      return res;
  }
  
  public boolean isAllZero(int[] map) {
      for(int i: map) {
          if(i!=0)  return false;
      }
      return true;
  }
}
