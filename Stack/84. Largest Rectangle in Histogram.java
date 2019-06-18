/*
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

 


Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

 


The largest rectangle is shown in the shaded area, which has area = 10 unit.

 

Example:

Input: [2,1,5,6,2,3]
Output: 10
*/


/*
index: 0,1,2,3,4,5, 6
arr=  [2,1,5,6,2,3, 0]  // add 0 to the end

i=0 st=[] push 2's index=0 st=[0]
i=1 since arr[1]<arr[st.peek] st.pop=0 maxArea = arr[0]*(1)=2  st push i=1 st=[1]
i=2 since arr[2]>arr[st.peek] st keep push st=[1,2]
i=3 since arr[3]>arr[st.peek] st keep push st=[1,2,3]
i=4 since arr[4] < arr[st.peek] st.pop=3 maxArea = arr[3]*(4-2-1)=6 
    still arr[4] < arr[st.peek] st.pop=2 maxArea = max(6, arr[2]*(4-1-1))=10
    st.push i=4 st=[1, 4]
i=5 since arr[5] >arr[st.peek] st keep push st=[1,4, 5]
i=6 since arr[6] < arr[st.peek] st.pop=5 maxArea = max(10, arr[5]*(6-currentTop-1)) = 10
    since arr[6] < arr[st.peek] st.pop=4 maxArea = max(10, arr[4]*(6-1-1)) = 10
    since arr[6] < arr[st.peek] st.pop=1 since st is empty now, means all elements before index(1) are larger than arr[1]
                                             maxArea = max(10, arr[1]*6) = 10, this is the KEY!!!
The key is if current height is larger than previous, we can keep push
    otherwise, we should compute the area with previous height
    then push current height
    
the core, if nums[i] > nums[i-1] st.push(i)
          else we need to keep pop st, say index= st.pop() 
            if(after pop st is empty) {
                means all elements before this popped index are larger than the value popped index point to
                area = i*arr[index]
            } else {
                we caculate the area from cur index to the popped index, using height as arr[index] 
                since all elements after it are larger
                area = arr[index]*(i-currentTop-1)  currentTop is the peek value of current stack
                                                    since all values after currentTop and before index are larger than index, 
                                                    so we need to include, this is the KEY!!!
            }

need to think about both left and right, left to the current top, right to the current i.

*/
class Solution {
  public int largestRectangleArea(int[] heights) {
      // add a 0 at the end of int[]
      Stack<Integer> st = new Stack<Integer>();
      int maxArea = 0;
      for(int i=0; i<=heights.length; i++) {
          int h = (i==heights.length ? 0 : heights[i]);
          while(!st.isEmpty() && h < heights[st.peek()]) {
              int index = st.pop();
              if(st.isEmpty()) {
                  maxArea = Math.max(maxArea, i*heights[index]);
              } else {
                  maxArea = Math.max(maxArea, heights[index]*(i-st.peek()-1));
              }
          }
          st.push(i);
      }
      return maxArea;
  }
}
