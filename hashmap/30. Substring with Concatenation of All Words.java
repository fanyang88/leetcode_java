/**
 You are given a string, s, and a list of words, words, that are all of the same length. 
 Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once 
 and without any intervening characters.

Example 1:

Input:
  s = "barfoothefoobarman",
  words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoor" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.
Example 2:

Input:
  s = "wordgoodgoodgoodbestword",
  words = ["word","good","best","word"]
Output: []

 */

 /*
 We use an unordered_map<string, int> counts to record the expected times of each word and another unordered_map<string, int> seen to record the times we have seen. 
 Then we check for every possible position of i. Once we meet an unexpected word or the times of some word is larger than its expected times, we stop the check. If we finish the check successfully, push i to the result indexes.
*/

class Solution {
  public List<Integer> findSubstring(String s, String[] words) {
      List<Integer> res = new ArrayList<>();
      if(words.length == 0)  return res;
      final Map<String, Integer> map = new HashMap<String, Integer>();
      for(String word: words) {
          map.put(word, map.getOrDefault(word, 0) + 1);
      }
      
      int n = s.length(), num = words.length, len = words[0].length();
      
      for(int i=0; i<= n - num*len; i++) {
          Map<String, Integer> seen = new HashMap<String, Integer>();
          String sub = s.substring(i, i+num*len);
          int k=0;
          // check in sub string see if all words in the map
          for(; k<sub.length(); k+=len) {
              String word = sub.substring(k, k+len);
              if(map.containsKey(word)) {
                  seen.put(word, seen.getOrDefault(word, 0) + 1);
                  if(seen.get(word) > map.get(word))  
                      break;
              } else {
                  break;
              }
          }
          if(k== num*len) { // reach to the end
              res.add(i);
          }
      }
      return res;
  }
}