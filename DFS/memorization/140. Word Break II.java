/**
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

 /*
    The key is to search in an returned array
                    
                        catsanddog
                        / |   \
                       c  ca   cat,{sanddog} 
                               /  \    \    \
                              s   sa   san   sand,{dog}
                                              / |   \
                                             d  do  dog return back
*/
class Solution {
  public List<String> wordBreak(String s, List<String> wordDict) {
      HashMap<String, List<String>> map = new HashMap<>();
      List<String> empty = new ArrayList<String>();
      empty.add("");
      map.put("", empty);  // This is the key
      return dfs(s, wordDict, map);
  }
   
  public List<String> dfs(String s, List<String> wordDict, HashMap<String, List<String>> map) {
      if(map.containsKey(s))  return map.get(s);
      
      List<String> ans = new ArrayList<String>();
      for(String word: wordDict) {
          if(!s.startsWith(word)) continue;
          String restString = s.substring(word.length());
          List<String> last = dfs(restString, wordDict, map);// e.g contain dog
          for(String str: last) {
              if(str=="")  
                  ans.add(word);// it is empty
              else 
                  ans.add(word + ' ' + str); // 'sand' + 'dog'
          }
      }
      map.put(s, ans); // cache
      return ans;
  }
}