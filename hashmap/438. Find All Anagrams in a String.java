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

/**
 
s: "abab" p: "ab"
i=2 j=0 s[2]=a s[0]=a map is still 0, res add j+1=1
i=3 j=1 s[3]=b s[1]=b map is still 0, res add j+1=2
    0123456789
s: "cbaebabacd" p: "abc"
i=3 j=0 s[3]=e s[0]=c e--=-1 c++=1 map not all zero
i=4 j=1 s[4]=b s[1]=b b=0 map not all zero
i=5 j=2 s[5]=a s[2]=a a=0 map not all zero
i=6 j=3 s[6]=b s[3]=e b--=-1 e++=0 
i=7 j=4 s[7]=a s[4]=b a--=-1 b=0 
i=8 j=5 s[8]=c--=0 s[5]=a++=0 all zero res add j+1=6
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
