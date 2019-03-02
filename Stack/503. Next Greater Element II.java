/*
Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.
*/

/*
extend the origin to twice: [1,2,1] => [1,2,1,1,2,1]
i=0 st=[0]
i=1 next[0]=2 st=[1]
i=2 since 1< 2 st=[1,2]
i=3 st=[1,2,3%3]
i=4 2> 1 next[3%3]=2 next[2]=2 st=[1, 4%3]
i=5 st=[1,4%3, 5%3]

*/

class Solution {
  public int[] nextGreaterElements(int[] nums) {
      int n = nums.length;
      int[] res = new int[n*2];
      Arrays.fill(res, -1);
      Stack<Integer> st = new Stack<>();
      
      for(int i=0; i<2*n; i++) {
          int num= nums[i % n];
          while(!st.isEmpty() && nums[st.peek()] < num) {
              int ind = st.pop();
              res[ind] = num;
          }
          st.push(i % n);
      }
      return Arrays.copyOfRange(res, 0, n);
  }
}
