/*
Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
*/

class Solution {
  public String reverseStr(String s, int k) {
      int len =0;
      String res= "";
      while(len + 2*k <= s.length()) {
          String temp = reverse(s.substring(len, len+k)) +  s.substring(len+k, len+2*k);//s.substring(len, len+t);
          res = res + temp;
          len = len + 2*k;
      }
    
      if(s.length() - res.length() < k) {
          res = res + reverse(s.substring(res.length()));
      } else if(s.length() - res.length() < 2*k){
          res = res + reverse(s.substring(res.length(), res.length()+k)) + s.substring(res.length()+k);
      } else {
          res = res + s.substring(res.length());
      }
      return res;
  }
  
  public String reverse(String s) {
      int i=0, j = s.length()-1;
      char[] chrs= s.toCharArray();
      while(i<j) {
          char t = chrs[i];
          chrs[i] = chrs[j];
          chrs[j] = t;
          i++;
          j--;
      }
      return new String(chrs);
  }
  
}
