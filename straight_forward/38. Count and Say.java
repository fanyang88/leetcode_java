/**
 The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

 

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"
 */

 /*
 1:  1  
 2:  11   since case 1 is one 1s
 3:  21   since case 2 is two 1s
 4:  1211   since case 3 is one 2 one 1
 just read how many 1 and how many 2 in previous case is the current result
*/

class Solution {
  public String countAndSay(int n) {
      StringBuilder res=new StringBuilder("1");
      for(int i=1; i<n; i++) {
          char cur = res.charAt(0);
          StringBuilder output = new StringBuilder();
          int count = 0;
          for(int j=0; j<res.length(); j++) {
              if(res.charAt(j) == cur) {
                  count++;
              } else {
                  output.append(count).append(cur);
                  cur = res.charAt(j);
                  count=1;
              }
          }
          // the last round
          output.append(count).append(cur);
          res = output;  
      }
      return res.toString();
  }
}