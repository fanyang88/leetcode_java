/*
S and T are strings composed of lowercase letters. In S, no letter occurs more than once.

S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. More specifically, if x occurs before y in S, then x should occur before y in the returned string.

Return any permutation of T (as a string) that satisfies this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
Explanation: 
"a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs.
 

Note:

S has length at most 26, and no character is repeated in S.
T has length at most 200.
S and T consist of lowercase letters only.
*/

/*
e.g: S="cbad"  T="abcdabcdeeff"
answer should be "ccbbaaddeeff"

we use a map to count in T: a:2 b:2 c:2 d:2 e:2 f:2
go through S
c has 2, cc
b has 2, ccbb
a has 2, ccbbaa
d has 2, ccbbaadd
what's left in T is e, f
go through T to deal with rest

*/

class Solution {
  public String customSortString(String S, String T) {
      int[] map = new int[26];
      String res = "";
      for(char c : T.toCharArray())  map[c - 'a'] ++;

      for(char c : S.toCharArray()) {
          while(map[c- 'a'] > 0) {
              res += c;
              map[c-'a'] --;
          }
      }
      for(char c : T.toCharArray()) {
          while(map[c- 'a'] > 0) {
              res += c;
              map[c-'a'] --;
          }
      }
      return res;
  }
}
