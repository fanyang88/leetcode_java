/*

Given a nested list of integers represented as a string, implement a parser to deserialize it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Note: You may assume that the string is well-formed:

String is non-empty.
String does not contain white spaces.
String contains only digits 0-9, [, - ,, ].
Example 1:

Given s = "324",

You should return a NestedInteger object which contains a single integer 324.
Example 2:

Given s = "[123,[456,[789]]]",

Return a NestedInteger object containing a nested list with 2 elements:

1. An integer containing value 123.
2. A nested list containing two elements:
    i.  An integer containing value 456.
    ii. A nested list with one element:
         a. An integer containing value 789.
         */


         /**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 
 
 the add() method adds a NestedInteger object to the caller. e.g.:
  outer = NestedInteger() # []
  nested = NestedInteger(5)
  outer2 = nested = NestedInteger(5)
  outer.add(nested) # outer is now [5]
  outer2.add(outer) # outer2 is now [5, [5]]
  
 If encounters '[', push current NestedInteger to stack and start a new one.
If encounters ']', end current NestedInteger and pop a NestedInteger from stack to continue.
If encounters ',', append a new number to curr NestedInteger, if this comma is not right after a brackets.
Update index l and r, where l shall point to the start of a integer substring, while r shall points to the end+1 of substring.
     012345678910
e.g: [123,[456]]

i=0 it is a [, res=null res=new nestedInt(),  st=[]  j=1
i=1, i=2, i=3 do nothing
i=4 it is , create a new nestedInt() and add to current res, res=[123] j =5
i=5 it is [, res push to st={[123]}  res=new nestedInt()  j=6
i=6 i=7 i=8 do nothing
i=9 it is ], end current res, if i!=j, there is a number, res.add(number) res=[456]
             pop previous=[123] add res to previous and make it to be res=[123,[456]]

 */
class Solution {
  public NestedInteger deserialize(String s) {
      if(s.length() ==0)  return null;
      if(s.charAt(0) != '[') return new NestedInteger(Integer.valueOf(s));
      Stack<NestedInteger> st = new Stack<>();
      NestedInteger res = null;
      for(int i=0, j=0; i<s.length(); i++) {
          char c = s.charAt(i);
          if(c == '[') {
              // start a new NestedInteger
              if(res != null) st.push(res);
              res = new NestedInteger();
              j=i+1; // j point to the start of a number
          } else if(c== ']') {
              if(i-j>=1) {  // there is a number
                  res.add(new NestedInteger(Integer.valueOf(s.substring(j, i))));
              }
              if(!st.isEmpty()) {
                  NestedInteger old = st.pop();
                  old.add(res);
                  res= old;
              }
              j=i+1;
          } else if(c== ',') {
              // if previous is not ], means there is alrady a value exist, add to it
              if(s.charAt(i-1) != ']') {
                  res.add(new NestedInteger(Integer.valueOf(s.substring(j, i))));
              }
              j=i+1;
          }
      }
      return res;
  }
}
