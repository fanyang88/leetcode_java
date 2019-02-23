/*
Given an absolute path for a file (Unix-style), simplify it. 

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
path = "/a/../../b/../c//.//", => "/c"
path = "/a//b////c/d//././/..", => "/a/b/c"

In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path. Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was. For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".

*/


/*
use stack
/a//b////c/d//././/..

st: [a,b,c,d] .. pop [a,b,c]

/a/../../b/../c//.//
st: [a]
    [] pop a
    [] empty no pop
    [b] push b
    [] pop b
    [c] push c
    
*/
class Solution {
  public String simplifyPath(String path) {
      Stack<String> st = new Stack<String>();
      StringBuilder sb = new StringBuilder();
      String[] parts = path.split("/");
      for(String e : parts) {
          if(e.length() == 0 || e.equals(".")) continue;
          if(e.equals("..")) {
              if(!st.isEmpty()) st.pop();
          } else {
              st.push(e);
          }
      }
      if(st.isEmpty()) return "/";
      for(String e : st) {
          sb.append('/').append(e);
      }
      return sb.toString();
  }
}