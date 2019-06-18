/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */

class MinStack {

  Stack<Integer> st, minSt;
  public MinStack() {
      st = new Stack<>();
      minSt = new Stack<>();
  }
  
  public void push(int x) {
      st.push(x);
      if(minSt.isEmpty() || minSt.peek() >= x) {
          minSt.push(x);
      }
  }
  
  public void pop() {
      int res = st.pop();
      if(!minSt.isEmpty() && res == minSt.peek()) {
          minSt.pop();
      }
  }
  
  public int top() {
      return st.peek();
  }
  
  public int getMin() {
      return minSt.peek();
  }
}

/**
* Your MinStack object will be instantiated and called as such:
* MinStack obj = new MinStack();
* obj.push(x);
* obj.pop();
* int param_3 = obj.top();
* int param_4 = obj.getMin();
*/