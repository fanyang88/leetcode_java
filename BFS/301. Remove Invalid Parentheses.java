/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/

/*
    The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid, if found valid ones on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.

The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.
()())()
st= ["()())()"]
cur = ()())()  next: )())(), (())()-valid, ()))(), ()()()-valid, ()())), ()())(

there are two valid, put a stop at this level, and add all valid ones to the queue

*/

class Solution {
  public List<String> removeInvalidParentheses(String s) {
      List<String> res = new ArrayList<String>();
       if (s == null) return res;
      Set<String> visited = new HashSet<>();
      Queue<String> q = new LinkedList<>();
      boolean found = false;
      
      visited.add(s);
      q.add(s);
      
      while(!q.isEmpty()) {
          s = q.poll();
          if(isValid(s)) {
              res.add(s);
              found=true;
          }
          if(found)  continue; // stop at this level
          for(int i=0; i<s.length(); i++) {
              if(s.charAt(i) != '(' &&  s.charAt(i) != ')') continue;
              String sub = s.substring(0, i) + s.substring(i+1);
              if(visited.contains(sub)) continue; 
              q.add(sub);
              visited.add(sub);
          }
      }
      return res;
  }
  
  public boolean isValid(String s) {
      int count=0;
      for(int i=0; i<s.length(); i++) {
          if(s.charAt(i) == '(') count++;
          if(s.charAt(i) == ')') count--;
          if(count < 0)  return false;  // this is the key, should inside the loop
      }
      return count==0;
  }
}
