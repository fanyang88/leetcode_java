/*
Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.
*/


/*
    aaadbbcc, k = 3
 a: 3  b: 2   c: 2 
init:   next= [0,0,0]  # next position available for char at index i
        count= [3,2,2]  # number of elements left
 i=0, since count max = 3=count[0] and next[0] <= i count[0]--=2, next[0]= i+k= 3  res= 'a'
 i=1, since count max = 2=count[1] and next[1] <= i count[1]--=1, next[1]= i+k= 4  res= 'ab'
 i=2, since count max = 2=count[2] and next[2] <= i count[2]--=1, next[2]= i+k= 5  res= 'abc'
 i=3, since count max = 2=count[0] and next[0] <= i count[0]--=1, next[0]= i+k= 6  res= 'abca'
 i=4, since count max = 1=count[1] and next[1] <= i count[1]--=0, next[1]= i+k= 7  res= 'abcab'
 i=5, since count max = 1=count[2] and next[2] <= i count[2]--=0, next[2]= i+k= 8  res= 'abcabc'
 i=6, since count max = 1=count[0] and next[0] <= i count[0]--=0, next[0]= i+k= 9  res= 'abcabca'
    
*/
class Solution {
  public String rearrangeString(String s, int k) {
      String res = "";
      char[] arr = s.toCharArray();
      int[] count = new int[26], next = new int[26];
      for(char c : arr) {
          count[c-'a']++;
      }
      for(int i=0; i<arr.length; i++) {
          int index = getMaxIndex(count, next, i);
          if(index ==-1)  return "";
          res+= (char)(index+'a');
          count[index] --;
          next[index] = i+k;
      }
      return res;
  }
  
  public int getMaxIndex(int[] count, int[] next, int index) {
      int maxV = Integer.MIN_VALUE, candidate=-1;
      for(int i=0; i<26; i++) {
          if(count[i] > 0 && count[i] > maxV && index >=next[i]) {
              maxV = Math.max(maxV, count[i]);
              candidate = i;
          }
      }
      return candidate;
  }
}

