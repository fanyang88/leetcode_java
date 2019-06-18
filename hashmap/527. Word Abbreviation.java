/*
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.

Begin with the first character and then the number of characters abbreviated, which followed by the last character.
If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
If the abbreviation doesn't make the word shorter, then keep it as original.
Example:
Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
Note:
Both n and the length of each word will not exceed 400.
The length of each word is greater than 1.
The words consist of lowercase English letters only.
The return answers should be in the same order as the original array.
*/

/*
    Make abbreviation for each word.
Then, check each word, if there are some strings which have same abbreviation with it, increase the prefix.
 
 .eg: there is w113, w223, w333
  we make it to be: w23, w23, w23  first
  for w23, since word[1], word[2] as the same, set= (0,1,2)
     add prefix for all words in set: become, w113, w213, w313, 
     next round, since set is empty, means no word same as w113, break
  check w223, set is empty, means no word same as w113, break
  check w313, set is empty, means no word same as w113, break
  ans= [w113, w213, w313]

*/


class Solution {
  public List<String> wordsAbbreviation(List<String> dict) {
      int[]  prefix = new int[dict.size()];
      String[] ans = new String[dict.size()];
      for(int i=0; i<dict.size(); i++) {
          ans[i] = getAbbr(dict.get(i), 1); // keeo (0,1) and last char, abbr everything from 1~n-2
          prefix[i] = 1;
      }
      
      for(int i=0; i<dict.size(); i++) {
          while(true) {
              Set<Integer> set = new HashSet<>(); // store all same words indexes
              for(int j=i+1; j<dict.size(); j++) {
                  if(ans[i].equals(ans[j])) set.add(j);
              }
              if(set.isEmpty()) break;
              set.add(i);
              for(int k: set) {
                  ans[k] = getAbbr(dict.get(k), ++prefix[k]); // keep (0,1) and last char, abbr everything from 1~n-2
              }
          }
      }
      return Arrays.asList(ans);
  }
  
  public String getAbbr(String s, int prefix) {
      if(prefix +2 >= s.length())  return s;
      String res = s.substring(0, prefix) + (s.length() -1 - prefix) + s.charAt(s.length()-1);
      return res;
  }
}
