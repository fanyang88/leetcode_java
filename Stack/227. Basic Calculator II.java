/**
 * Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.

 */

 /*
  3/2
  i=0 num=3
  i=1 curSign=+, push 3 to st: st=[3] num=0, cursign = /
  i=2 num=2
  last one: st pop 3, st push 3/2, 
  
  3+5/2
  i=0 num=3 
  i=1 st=[+3] num=0 sign='+'
  i=2 num=5
  i=3 st=[3, 5] num=0 sign='/'
  i=4 num=2 since i=len=1 st.push(st.pop/2)=[3,2]
  
  now we go over st that add all numbers in st
  
  use: Character.isDigit
*/

class Solution {
  public int calculate(String s) {
      if(s.length()==0)  return 0;
      int num=0, n=s.length(), sum=0;
      char op = '+';
      Stack<Integer> st= new Stack<>();
      for(int i=0; i<n;i++) {
          if(Character.isDigit(s.charAt(i))) {
              num = num*10 + s.charAt(i)-'0';
          } else {
              if(s.charAt(i) == ' ') continue;
              push(st, num, op);
              num=0;
              op = s.charAt(i);
          }
      }
      // handle the last one
      push(st, num, op);
      
      // add all number in st
      while(!st.isEmpty()) {
          sum += st.pop();
      }
      return sum;
  }
  
  public void push(Stack<Integer> st, int num, char op) {
      if(op == '-') {
          st.push(-num);
      } else if(op=='+') {
          st.push(num);
      } else if(op=='*') {
          st.push(st.pop() * num);
      } else {
          st.push(st.pop() / num);
      }
  }
}
