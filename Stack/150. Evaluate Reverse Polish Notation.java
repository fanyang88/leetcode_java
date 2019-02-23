/**
 * 
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. 
That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation: 
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 */

/*
["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 st= [10]
 st= [10,6]
 st= [10,6,9]
 st= [10,6,9,3]
 i is +: pop 2 elements 9+3=12 st= [10,6,12]
 st= [10,6,12, -11]
 i is *: pop 2 elements 12*-11=-132 st= [10,6,-132]
 i is /: pop 2 elements 6/-132=0 st= [10,0]
 ...
 
 if it is number, keep push,
 else pop two numbers from st, do calculation then push the result into st
*/

class Solution {
  public int evalRPN(String[] tokens) {
      Stack<Integer> st = new Stack<>();
      for(String token: tokens) {
          if(token.equals("-")) {
              int v2 = st.pop();
              int v1= st.pop();
              st.push(v1 - v2);
          } else if(token.equals("+")) {
              st.push(st.pop() + st.pop());
          } else if(token.equals("*")) {
              st.push(st.pop() * st.pop());
          } else if(token.equals("/")) {
              int v2 = st.pop();
              int v1= st.pop();
              st.push(v1 / v2);
          } else {
              st.push(Integer.parseInt(token));
          }
      }
      return st.pop();
  }
}
