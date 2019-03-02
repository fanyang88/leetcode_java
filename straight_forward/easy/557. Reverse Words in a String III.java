/*
Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
*/

class Solution {
  public String reverseWords(String s) {
      char[] str = s.toCharArray();
      int n = str.length;
      for(int i=0; i<n; i++) {
          int j = i;
          while(j<n && s.charAt(j) != ' ') j++;
          reverse(str, i, j-1);
          i=j;
      }
      return new String(str);
  }
  
  public void reverse(char[] str, int i, int j) {
      while(i < j) {
          char t = str[i];
          str[i] = str[j];
          str[j] =t;
          i++;
          j--;
      }
  }
}
