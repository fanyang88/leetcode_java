/*
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
*/

/*
T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
using stack:
i=0, st=[0] 
i=1 since 74>73 pop 0, res[0] = 1-0=1 st=[1]
i=2 since 75>74 pop 1, res[1] = 2-1=1 st=[2]
i=3 since 71<75 push st=[2,3]
i=4 since 69<71 push st[2,3,4]
i=5 since 72>69 pop 4, res[4]=5-4=1 since 72>71 pop 3, res[3]=5-3=2 
i=6 since 76>75 pop 2, res[2] = 6-2=4  st=[6]
i=7 st=[6, 7] 
pop 7, res[7]=0  res[6]=0

*/

class Solution {
  public int[] dailyTemperatures(int[] T) {
      Stack<Integer> st = new Stack<>();
      int[] res = new int[T.length];
      for(int i=0; i<T.length; i++) {
          while(!st.isEmpty() && T[st.peek()] < T[i]) {
              int index = st.pop();
              res[index] = i - index;
          }
          st.push(i);
      }
      return res;
  }
}
