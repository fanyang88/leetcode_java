/*
A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

*/

/*
     use a map to store, and a count to store new element, once map[chr]--=0 count--, 
     once count = 0, start a new partition
          012345678
     e.g: ababcbacadefegde
     a=4 b=3 c=2 ...
     i=0 set doesn't have a , set add a count++=1 map[a]--=3
     i=1 set doesn't have b , set add b count++=2 map[b]--=2
     i=2 set has a, map[a]--=2
     i=3 set has b, map[b]--=1
     i=4 set doesn't have c , set add c count++=3 map[c]--=1
     i=5 set has b, map[b]--=0 count--=2
     i=6 set has a, map[a]--=1 
     i=7 set has c, map[c]--=0 count--=1
     i=8 set has a, map[a]--=0 count--=0 this is a divide, i-j+1=9 since j=0 move j to 9
     
*/

class Solution {
  public List<Integer> partitionLabels(String S) {
      List<Integer> res = new ArrayList<>();
      int[] map = new int[26];
      Set<Character> set = new HashSet<>();
      for(char c: S.toCharArray()) map[c- 'a'] ++;
      int count=0;
      for(int j=0,i=0; i<S.length(); i++) {
          char c = S.charAt(i);
          if(!set.contains(c)) {
              set.add(c);
              count++;
          }
          map[c - 'a'] --;
          if(map[c- 'a']==0)  count--;
          if(count==0) {
              res.add(i-j+1);
              j=i+1; // j is the previous split pos
          }
      }
      return res;
  }
}
