/*
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
*/

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 
 we need a stack to do this: e.g: [[[1, [2, 3]]], [4, 5]]
    st = [4, 5], [[1, [2, 3]]]
    to call getNext, check if top of st is interger or not, 
    if not pop(st), we get cur = [[1, [2, 3]]] we need to iterative cur, st.push([1, [2, 3]])
    st =  [4, 5], [1, [2, 3]]
    since st not empty
    pop(st), we get cur = [1, [2, 3]] we need to iterative cur, st.push([2, 3], 1)
    st =  [4, 5], [2, 3], 1]
    st not empty, call st again, this time the top is an integer, return true;

 
 */
public class NestedIterator implements Iterator<Integer> {
  Stack<NestedInteger> st;
  public NestedIterator(List<NestedInteger> nestedList) {
      st = new Stack<>();
      for(int i=nestedList.size()-1; i>=0; i--) st.push(nestedList.get(i));
  }

  @Override
  public Integer next() {
      return st.pop().getInteger();
  }

  @Override
  public boolean hasNext() {
      while(!st.isEmpty()) {
          if(st.peek().isInteger()) return true;
          List<NestedInteger> cur= st.pop().getList();
          for(int i=cur.size()-1; i>=0; i--) st.push(cur.get(i));
      }
      return false;
  }
}

/**
* Your NestedIterator object will be instantiated and called as such:
* NestedIterator i = new NestedIterator(nestedList);
* while (i.hasNext()) v[f()] = i.next();
*/
