/**
 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', 
 determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true
 */

 // use stack
class Solution {
  public boolean isValid(String s) {
      Stack<Character> st = new Stack<Character>();
      for(int i=0; i<s.length(); i++) {
          char chr = s.charAt(i);
          if(chr == '[' || chr == '(' || chr == '{') {
              st.push(chr);
          } else {
              if(chr == ']' && (st.isEmpty() || st.peek() != '[')) return false;
              if(chr == '}' && (st.isEmpty() || st.peek() != '{')) return false;
              if(chr == ')' && (st.isEmpty() || st.peek() != '(')) return false;
              st.pop();
          }
      }
      return st.isEmpty();
  }
}