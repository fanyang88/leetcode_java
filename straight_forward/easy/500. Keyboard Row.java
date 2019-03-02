/*
Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.

 



 
Example:

Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
 

Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

*/

class Solution {
  public String[] findWords(String[] words) {
     // construct a map
      List<String> res = new LinkedList<>();
      int[] map = {1, 2, 2, 1, 0, 1, 1, 1, 0, 1, 1, 1, 2, 2, 0, 0, 0, 0,1,0, 0, 2, 0, 2, 0, 2};
      for(String word: words) {
          String w = word.toUpperCase();
          int index = map[w.charAt(0) - 'A'];
          if(w.equals("")) continue;
          for(char c: w.toCharArray()) {
              if(index != map[c-'A']) {
                  index = -1;
                  break;
              }
          }
          if(index!=-1) res.add(word);
      }
      return res.toArray(new String[0]);
  }
}
