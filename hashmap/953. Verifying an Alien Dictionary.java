/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.

 

Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 

Note:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are english lowercase letters.
*/

/*
    Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
    worldabcef g h i  j  k mnpqstuvxyz
    12345678910111213 14 15
    word: 1 2 3 5
    world: 1 2 3 4 5
     word > world, so it unsorted
     
*/

class Solution {
  public boolean isAlienSorted(String[] words, String order) {
      int[] map = new int[26];
      for(int i=0; i<order.length(); i++) map[order.charAt(i)- 'a'] = i+1;
      
      for(int i=0; i<words.length-1; i++) {
          if(compare(words[i], words[i+1], map) > 0) return false;
      }
      return true;
  }
  
  public int compare(String a, String b, int[] map) {
      int cmp = 0;
      for(int i=0; i<Math.min(a.length(), b.length()); i++) {
          cmp = map[a.charAt(i) - 'a']  - map[b.charAt(i) - 'a'];
          if(cmp!=0) break;
      }
      return cmp ==0 ? a.length() - b.length() : cmp;
  } 
}
