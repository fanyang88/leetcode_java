/*
There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.

Example 1:
Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.
Example 2:
Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.
Note:
n will be in the range [1, 4].
k will be in the range [1, 10].
k^n will be at most 4096.
*/


/*
e.g: n=2, k=2
 there are 4 possible passwords in total, they are 00, 01, 10, 11
 we need to find a path to include all the 4 password.
 we start from 00. the goal is to find a path that we visited all 4 passwords.
                               00 - prefix 0
                            /      \      
                           00       01 (prefix 1)
                     exist        /      \
                                10         11
                              /    \      /   \
                            00     01   10      11(exist)
                      (exist)   (exist)  |
                                         answer: 00110
    
    the total combination of password = pow(k, n)   since each postion has k possiblities
    
    first digit has k possiblity , sec has k possiblity ...
    k*k*k*k ...
    ----n-----        = k^n
    

*/

class Solution {
  public String crackSafe(int n, int k) {
      Set<String> visited = new HashSet<>();
      int total = (int) Math.pow(k, n);
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<n ;i++) sb.append("0");
      visited.add(sb.toString());
      
      dfs(sb, visited, n, k, total);
      return sb.toString();
  }
  
  public boolean dfs(StringBuilder sb, Set<String> visited, int n, int k, int total) {
      if(visited.size() == total) return true;
      // we get the last n-1 first
      String prefix = sb.substring(sb.length() - (n-1), sb.length());
      
      for(int i=0; i<k; i++) {
          String next = prefix + i;
          if(visited.contains(next)) continue;
          visited.add(next);
          sb.append(i);
          if(dfs(sb, visited, n, k, total))  return true;
          visited.remove(next);
          sb.delete(sb.length()-1, sb.length());
      }
      return false;
  }
}
