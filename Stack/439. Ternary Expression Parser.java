/*
Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).

Note:

The length of the given string is ≤ 10000.
Each number will contain only one digit.
The conditional expressions group right-to-left (as usual in most languages).
The condition will always be either T or F. That is, the condition will never be a digit.
The result of the expression will always evaluate to either a digit 0-9, T or F.
Example 1:

Input: "T?2:3"

Output: "2"

Explanation: If true, then result is 2; otherwise result is 3.
Example 2:

Input: "F?1:T?4:5"

Output: "4"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
          -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
          -> "4"                                    -> "4"
Example 3:

Input: "T?T?F:5:3"

Output: "F"

Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:

             "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
          -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
          -> "F"                                    -> "F"

*/

/*
 we go from right to left, if it's not ? , continue push to stack, don't push :, till we meet ?, pop first and second, if it is T in left of ?, push first, else push second
 
 e.g: T?T?F:5:3
 st push 3 st=[3]
 st push 5 st=[3,5]
 st push F st[3, 5, F]
 it is ? , pop F, pop 5, since it is T, push F, st= [3, F]
 it is ? , pop F, pop 3, since it is T, push F
 st[0] is the answer
*/
class Solution {
  public String parseTernary(String expression) {
      Stack<Character> st = new Stack<>();
      for(int i=expression.length()-1; i>=0; i--) {
          char chr = expression.charAt(i);
          if(chr == '?') {
              char first = st.pop();
              st.pop();// pop :
              char sec = st.pop();
              if(expression.charAt(i-1) == 'T') {
                  st.push(first);
              } else {
                  st.push(sec);
              }
              i--; // next char has examined, skip it
          } else {
              st.push(chr);
          }
      }
      return ""+st.peek();
  }
}

