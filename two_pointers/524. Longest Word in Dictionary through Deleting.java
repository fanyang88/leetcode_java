/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output: 
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
*/

/*
    check if the str is a subsequence of s
    by compareing deirectly, the time complexity is O(n) n is s.length
    so the total is O(nk)
*/

class Solution {
  public String findLongestWord(String s, List<String> d) {
      String res= "";
      for(String sub: d) {
          if(isSubSequence(sub, s) && 
             (sub.length() > res.length() || (sub.length() == res.length() && res.compareTo(sub) > 0))) {
              res = sub;
          }
      }
      return res;
  }
  
  public boolean isSubSequence(String t, String s) {
      int j=0;
      for(int i=0; i<s.length() && j<t.length(); i++) {
          if(s.charAt(i) == t.charAt(j)) j++;
      }
      return j==t.length();
  }
}
