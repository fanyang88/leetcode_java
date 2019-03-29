/*
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].

*/

/*
count letter appearance and store in hash[i]
find the letter with largest occurence.
put the letter into even index numbe (0, 2, 4 ...) char array
put the rest into the array

e.g: aaabbcc
0 2 4 
a a a 

0 2 4 6
a a a b
i=1
0 2 4 6
abacacb

*/

class Solution {
  public String reorganizeString(String S) {
      int n = S.length(), maxCount=-1;
      char maxLetter = 'a';
      char[] res= new char[n];
      int[] map = new int[26];
      
      for(char c: S.toCharArray()) {
          map[c - 'a'] ++;
          if(maxCount < map[c - 'a']) {
              maxCount = map[c - 'a'];
              maxLetter = c;
          }
      }
      if(maxCount > (n+1)/2)  return "";
      
      int i=0;
      while(map[maxLetter - 'a'] > 0) {
          res[i] = maxLetter;
          i+=2;
          map[maxLetter - 'a'] --;
      }
      
      // put the rest into array
      for(int j=0; j<26; j++) {
          while(map[j] > 0) {
              if(i >= n) i = 1; // This is the key!!!
              res[i] = (char)(j + 'a');
              map[j] --;
              i=i+2;
          }
      }
      return new String(res);
  }
}
