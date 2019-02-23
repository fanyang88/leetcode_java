/**
 Given a string S and a string T, 
 find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

 */

 /*
        0 1 2 3 4 5 6 7 8 9 10 11 12  T=ABC
        A D O B E C O D E B A  N  C 
        
count=3
i=0 map[A]=1 counter--=2 map[A]--=0
i=1
i=2
i=3 map[B]=1 counter--=1 map[B]--=0
i=4 
i=5 map[C]=1 counter--=0 map[C]--=0 since counter==0 len=i-j+1, map[j]->map[A]=1 counter++=1 j++, break the inner loop
        0 1 2 3 4 5 6 7 8 9 10 11 12  T=ABC
        A D O B E C O D E B A  N  C 
                  i
          j
i=6 
i=7
i=8
i=9 map[B]=0 so counter not changed still =1 map[B]--=-1
i=10 since map[A]=1 counter--=0  map[A]--=0 since counter==0 len=i-j+1, when move to B,s ince map[B]=-1 count not changed map[B]++=0
     0 1 2 3 4 5 6 7 8 9 10 11 12  T=ABC
     A D O B E C O D E B A  N  C 
                         i
                 j
*/

class Solution {
  public String minWindow(String s, String t) {
      if(t.length() > s.length())  return "";
      int minLen= Integer.MAX_VALUE, begin=0, counter = t.length();
      int[] map = new int[128];
      for(int i=0; i<t.length(); i++) {
          map[t.charAt(i)- '0'] ++;
      }
      
      for(int i=0, j=0; i<s.length(); i++) {
          char chr = s.charAt(i);
          if(map[chr - '0'] > 0) { // check before change value
              counter--;
          } 
          map[chr - '0']--;
          while(counter==0) {
              if(i-j+1 < minLen) {
                  minLen = i-j+1;
                  begin = j;
              }
              char left = s.charAt(j);
              if(map[left- '0'] == 0) counter++;
              map[left- '0']++;
              j++;
          }
      }
      return minLen == Integer.MAX_VALUE ? "" : s.substring(begin, minLen+begin);
  }
}


