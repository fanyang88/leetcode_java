/*
Given two sequences pushed and popped with distinct values, return true if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

 

Example 1:

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
Explanation: We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
Example 2:

Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
Output: false
Explanation: 1 cannot be popped before 2.
 

Note:

0 <= pushed.length == popped.length <= 1000
0 <= pushed[i], popped[i] < 1000
pushed is a permutation of popped.
pushed and popped have distinct values.

*/

/*
     We push elements from push[i] into the stack, and pop them while the top of the stack equals pop[j]. In the end, if the stack is empty then the sequence is valid.

pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
stack[1,2,3,4] since 4 is = popped[0] pop 4, push 5, pop 5, pop 3, pop 2, pop 1
pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
stack[1,2,3,4] since 4 is = popped[0] pop 4, pop 3, 
next push 5,since 5 is = popped[0] pop 5, stack still have 1 and 2 left.
*/

class Solution {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
      
      Stack<Integer> st = new Stack<>();
      int pointer= 0; // point to popped
      for(int i=0; i<pushed.length; i++) {
          st.push(pushed[i]);
          while(st.size() > 0 && pointer < popped.length && popped[pointer] == st.peek()) {
              st.pop();
              pointer++;
          }
      }
      return st.size()==0;
  }
}
