/*
A string S of lowercase letters is given.  Then, we may make any number of moves.

In each move, we choose one of the first K letters (starting from the left), remove it, and place it at the end of the string.

Return the lexicographically smallest string we could have after any number of moves.

 

Example 1:

Input: S = "cba", K = 1
Output: "acb"
Explanation: 
In the first move, we move the 1st character ("c") to the end, obtaining the string "bac".
In the second move, we move the 1st character ("b") to the end, obtaining the final result "acb".
Example 2:

Input: S = "baaca", K = 3
Output: "aaabc"
Explanation: 
In the first move, we move the 1st character ("b") to the end, obtaining the string "aacab".
In the second move, we move the 3rd character ("c") to the end, obtaining the final result "aaabc".
 

Note:

1 <= K <= S.length <= 1000
S consists of lowercase letters only.

*/

/*
The main idea is the following:
if K == 1, then we can only rotate the string. Hence we can just rotate it at every position and get the lexicographically smallest string from that.
if K > 1, we can achieve swap of adjacent characters using the following steps:
Assume the string has the following characters: c[0], c[1], c[2],...,c[n-1] and 
we want to swap some position i (i >= 0 && i < n - 1) with position i+1, or swap c[i] and c[i+1]. 
We can start by pushing the first character at the end until c[i-1], 
after that we can push c[i+1] to the back and then push c[i] to the back and then can again keep pushing first characters to the end till we see rotate the string completely. After we do one full rotation we would have effectively swapped c[i] & c[i+1].
For example:
say n = 5, k = 2 and we want to swap c[2], c[3]
Initial state: c[0], c[1], c[2], c[3], c[4]
c[1], c[2], c[3], c[4], c[0]
c[2], c[3], c[4], c[0], c[1]
c[2], c[4], c[0], c[1], c[3]
c[4], c[0], c[1], c[3], c[2]
c[0], c[1], c[3], c[2], c[4]

Thus we have achieved swapping of chars c[2] and c[3] without disturbing ordering of other characters 
(similarly this can be done for any pair of adjacent indices). Swapping adjacent characters is enough to sort the whole string (using bubble sort). 
Hence we can just sort the input and return the smallest string.
*/

class Solution {
  public String orderlyQueue(String S, int K) {
      char[] chrs = S.toCharArray();
      if(K > 1) {
          Arrays.sort(chrs);
          return new String(chrs);
      } 
      // for K==1
      String res = S;
      for(int i=0; i<chrs.length; i++) {
          String t = S.substring(i) + S.substring(0, i);
          if(res.compareTo(t) > 0) res=t;
      }
      return res;
  }
}
