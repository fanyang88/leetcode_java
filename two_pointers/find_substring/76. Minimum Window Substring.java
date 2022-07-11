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
      for(int i=0; i<t.length(); i++)  map[t.charAt(i)- '0'] ++;
      
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


PYTHON ***********************************************

class Solution:
    def minWindow(self, s: str, t: str) -> str:
        #corner case: s = "a", t = "aa" output: ""
        if len(s)< len(t):
            return ""
        
        minlen = len(s)+1
        hmap = {}
        res = s
        j = 0
        i=0
        total = len(t)
        
        for char in t:
            hmap[char] = hmap[char] +1 if char in hmap else 1
      
        while i< len(s):
            hmap[s[i]] = hmap[s[i]] -1 if s[i] in hmap else -1
            if hmap[s[i]] >=0: # we find one char
                total -= 1
            
            while total ==0: # find all chars in T, we can shrink window
                # update res first
                if i-j+1 < minlen:
                    minlen = i-j+1
                    res = s[j: i+1]
                
                # update left pointer then move
                hmap[s[j]] = hmap[s[j]] +1 
                if hmap[s[j]] > 0: #we recovered one char, stop shrink
                    total += 1
                j+=1
            i+=1 
            
        return "" if minlen == len(s)+1 else res;
        

# Thoughts:
#     s = "DAOBCODEBANC", t = "ABC"
# map: {A: 1, B: 1, C: 1}
# count= 3
# i=0 map {A: 1, B: 1, C: 1, D: -1} do nothing, since < 0 not exist anyway
# i=1 update map {A: 0, B: 1, C: 1, D: -1} since A:0 count --=2 find one
# i=2 map {A: 0, B: 1, C: 1, D: -1 O:-1} do nothing, since < 0 not exist anyway
# i=3 update map {A: 0, B: 0, C: 1, D: -1 O:-1} since B:0 count --=1 find one
# i=4 update map {A: 0, B: 0, C: 0, D: -1 O:-1} since C:0 count --=0 find one
#     now we see if we can shrink window
#     j=0 update map {A: 0, B: 0, C: 0, D: 0, O:-1} since D:0 means we didn't recover any c in T,     otherwise, it would > 0 keep shrinking, update len = j+1~i
#     j=1 update map {A: 1, B: 0, C: 0, D: 0, O:-1} since A:1 means we recover one char in T stop shrink
        
# i=5....

# we keep expanding the window till we found all chars in T, then we do a while loop to shrink window till we recovered one char in T
    
