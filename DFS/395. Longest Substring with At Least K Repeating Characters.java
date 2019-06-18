/*
Find the length of the longest substring T of a given string 
(consists of lowercase letters only)
 such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/

/*
 divide and conquer
 for any string s:  first caculate each char frequency, e.g: 'a' < k, b<'k'
 then loop from a-z
 check each char in the string s
 if we meet a 'a', since we know it can't be >k, we need to exclude it out, we can split the string left and right based on 'a'
 cacluate the left and right part and get the max
 however if all chars in s are >k we return end - start
 
 e.g:  
 ababbcd . k=2
      s=0  e=6  caculate the freq: a:2 b:3 c:1, since c=1 < 2 we should split the string here: ababb   & . cd
                s=0 e=4  no element <k return 4-0+1    
                s=5 e=6  in cd, c=1<k, split to be c & d
                        s=5 e=5 since e-s<k return 0
                        s=6 e=6 since e-s <k return 0
      
 
*/

class Solution {
  public int longestSubstring(String s, int k) {
      return dfs(0, s.length(), s, k);
  }
  
  public int dfs(int s, int e, String str, int k) {
      if(e-s < k) return 0;  // no enough chars
      int[] map = new int[26];
      for(int i=s; i<e; i++) {
          map[str.charAt(i)-'a'] ++;
      }
      // check each element
      for(int i=0; i<26; i++) {
          if(map[i] >= k)  continue; // no need to split in char i+'a'
          for(int j=s; j<e;j++) {
             if(str.charAt(j)== (char)(i+'a')) {  // char equals
                 //prepare to split string here, there could be ...aaab... if k=4, we should split it to '...' and 'b...'
                 int next=j;
                 while(next< str.length() && str.charAt(next) == (char)(i+'a')) next++;
                 return Math.max(dfs(s, j, str, k), dfs(next, e, str, k));
             } 
          }
      }
      return e-s;
  }
}
