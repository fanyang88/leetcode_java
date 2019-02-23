/*
Given a 2D binary matrix filled with 0's and 1's, 
find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

class Solution {
  public int maximalRectangle(char[][] matrix) {
      if(matrix.length ==0)  return 0;
      int m = matrix.length, n= matrix[0].length, maxArea = 0;
      int[] heights = new int[n+1];
      
      for(int i=0; i<m; i++){
          for(int j=0; j< n; j++){
              if(matrix[i][j] =='0') {
                  heights[j] = 0;
              } else {
                  heights[j] ++;
              }
          }
          maxArea = Math.max(maxArea, getArea(heights));
      }
      return maxArea;
  }
  
  // Refer LC84
  public int getArea(int[] arr) {
      Stack<Integer> st = new Stack<Integer>();
      int maxArea = 0;
      for(int i=0; i<arr.length; i++) {
          while(!st.isEmpty() && arr[i] < arr[st.peek()]) {
              int index = st.pop();
              if(!st.isEmpty()) {
                  maxArea = Math.max(maxArea, arr[index]*(i - st.peek() - 1));
              } else {
                  maxArea = Math.max(maxArea, arr[index]*i);
              }
          }
          st.push(i);
      }
      return maxArea;
  }
}
