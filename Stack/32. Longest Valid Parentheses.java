/*
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

Example 1:
Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/

/*
e.g: )()()), we record the position where is not a valid Parentheses
i=0, st.push(0)
i=1, st.push(1)
i=2, since st.peek=(, pop, st=[0]
i=3, st.push(3)
i=4, since st.peek=(, pop, st=[0]
i=5, st.push(5) st=[0,5]
the maxlen = 5-0-1

e.g: (())())()))
i=0, st.push(0)
i=1, st.push(1)
i=2, since st.peek=(, pop, st=[0]
i=3, since st.peek=(, pop, st=[]
i=4, st.push(4)
i=5, since st.peek=(, pop, st=[]
i=6, st.push(6)
i=7, st.push(7)
i=8, since st.peek=(, pop, st=[6]
i=9, st.push(9)
i=10, st.push(10)
st = [6,9,10]
change to [-1,6,9,10] max gap = 6-(-1)-1=6 is the answer
*/
class Solution {
  public int longestValidParentheses(String s) {
      Stack<Integer> st= new Stack<Integer>();
      int n = s.length(), res=0;
      for(int i=0; i<n; i++) {
          if(s.charAt(i) == ')' && !st.isEmpty() && 
             s.charAt(st.peek()) == '(') {
              st.pop();
          } else {
              st.push(i);
          }
      }
    
      int start = -1, end = n;
      while(!st.isEmpty()) {
          int index = st.pop();
          res = Math.max(res, end - index-1);
          end = index;
      }
      res = Math.max(res, end - start-1);
      return res;
  }
}