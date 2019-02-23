/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.
*/

/*
   "eceba"
   map[e]=0 map[e]=1 count++
   map[c]=0 map[c]=1 count++
   map[e]!=0 map[e]=2 
   map[b]=0 map[b]=1 count++=3>2 . move j, map[j]--=1  map[c]=--=0 count--=2 get length 
   
   ...
*/

class Solution {
  public int lengthOfLongestSubstringKDistinct(String s, int k) {
      char[] str = s.toCharArray();
      int count=0, maxL = 0;
     int[] map = new int[256];
      for(int i=0, j=0; i<str.length; i++) {
          if(map[str[i]] ==0) {
              count++;
          }
          map[str[i]]++;
          while(count > k) {
              map[str[j]] --;
              if(map[str[j]] ==0) count--;
              j++;
          }
          maxL = Math.max(maxL, i-j+1);
      }
      return maxL;
  }
}