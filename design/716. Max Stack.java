/*
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
*/

/*
    have two stack: st and maxSt
    push 5: st=[5]  maxSt= [5]
    push 1: st=[5, 1]  maxSt= [5]
    push 5: st=[5, 1, 5]  maxSt= [5, 5]
    
    popMax: st=[5, 1]  maxSt= [5]
    pop: st=[5]  maxSt= [5]

    when pop, if st.peek() == maxSt.peek() pop both, otherwise, only pop st
    when push, if maxSt.peek() ==val push to maxSt as well
    when popMax, e.g: push 5: st=[5, 1, 5, 1, 2]  maxSt= [5, 5]
        we need to pop st to a temp [2,1] pop both, then push temp back
        
   whe popMax st= [5, 1, -5] maxSt=[5]
       pop st=[5] temp[-5, 1] pop st, maxSt
       push temp back: st=[1, -5] maxSt=[1]
        
*/

class MaxStack {
  Stack<Integer> st, maxSt;
  public MaxStack() {
      st = new Stack<>();
      maxSt = new Stack<>();
  }
  
  public void push(int x) {
      if(maxSt.isEmpty() || maxSt.peek() <=x) maxSt.push(x);
      st.push(x);
  }
  
  public int pop() {
      int val= st.pop();
      if(maxSt.peek() == val) maxSt.pop();
      return val;
  }
  
  public int popMax() {
      Stack<Integer> temp = new Stack<>();
      int max = maxSt.peek();
      
      while(!st.isEmpty() && st.peek() !=max) temp.push(st.pop());
      st.pop();
      maxSt.pop();
      while(!temp.isEmpty()) {
          int val = temp.pop();
          this.push(val);// This is the key!!!
      }
      return max;
  }
}

/**
* Your MaxStack object will be instantiated and called as such:
* MaxStack obj = new MaxStack();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.top();
* int param_4 = obj.peekMax();
* int param_5 = obj.popMax();
*/
