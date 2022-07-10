/**
 * Given a string s , find the length of the longest substring t  
 * that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.
 */

 /*
    eceba
    i=0, since ch[e]==0 count++=1  chr[e]=1 
    i=1, since ch[c]==0 count++=2 chr[c]=1
    i=2, since ch[e]==1 do nothing
    i=3, since ch[b]==0 count++=3 chr[b]=1 since count>2 chr[j]-- => chr[e]--  j++=1 count still =3
                                           since count>2 chr[j]-- => chr[c]--=0 count--=2 j++ j=>e count=2 break
    i=4 since ch[a]==0 count++=3 chr[a]=1 since count>2 chr[j]-- => chr[e]--=0  j++=3 count--=2 get length break
                
    
*/

class Solution {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
      int[] hash = new int[256];
      char[] chars = s.toCharArray();
      int maxL = 0, count=0;
      for(int i=0, j=0; i<s.length(); i++) {
          if(hash[chars[i]] ==0) {
              count++;
          }
          hash[chars[i]]++;
          while(count > 2) {
              hash[chars[j]]--;
              if(hash[chars[j]] ==0) count--;
              j++;
          }
          // caculate length
          maxL = Math.max(maxL, i-j+1);
      }
      return maxL;
  }
}


PYTHON version:
class Solution:
    def lengthOfLongestSubstringTwoDistinct(self, s: str) -> int:
        
        # define hashmap
        hmap = {}
        # define maxlen
        maxlen = 0
        # define right, left pointer
        right =0
        left = 0
        while right < len(s):
            hmap[s[right]] = hmap[s[right]] + 1 if s[right] in hmap else 1
            
            # shrink window if map length > 2
            while len(hmap) > 2:
                hmap[s[left]] = hmap[s[left]]-1
                if hmap[s[left]] == 0:
                    del hmap[s[left]]
                left=left+1
                
            maxlen = max(maxlen, right-left+1)
            right=right+1
            
        return maxlen
        
    
    
    #  thoughts, use a hashmap to record each char as key and exist as occurances
    #  e c e b a 
    # e: {e : 1}  len =1 < 2 continue expand window update maxLen
    # c: {e: 1, c: 1} len = 2 continue expand window update maxLen
    # e: {e: 2, c: 1} len = 2 continue expand window update maxLen
    # b: {e: 2, c: 1, b: 1} len = 3>2  move left, {e: 1, c: 1, b: 1} still > 2, 
    #                                  move left, {c: 1, b: 1} =2 
    # ...
    
    
