/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
 */
/*
          aab
     /          \
    a, {ab}      aa,{b}
      /   \
     a    ab,{}
     
we can partition from each position, substring(index, i+1) if first half is parlinfrome, we can continue to partition
the core:
    for(int i=index;i<length; i++) {
        sub1 = (index, i+1); 
        if(sub1 is palindrome) 
            continue dfs from i
    }
    otherwise if index==length come to the end, res add this combination
    
            0~1             0~2       0~3
index=1    /  \           /    \
          1~2 1~3 ...   2~3 2~4 ...
    

*/

class Solution {
  public List<List<String>> partition(String s) {
      List<List<String>> res = new ArrayList<List<String>>();
      dfs(0, new ArrayList<>(), res, s);
      return res;
  }
  
  public void dfs(int index, List<String> path, List<List<String>> res, String s) {
      if(index == s.length()) {
          res.add(new ArrayList<>(path));
      } else{
          for(int i=index; i<s.length(); i++) {
              String sub = s.substring(index, i+1);
              if(!isPalindrome(sub))  continue;
              path.add(sub);
              dfs(i+1, path, res, s);
              path.remove(path.size()-1);
          }
      }
  }
  
  public boolean isPalindrome(String str) {
      int i=0, j=str.length()-1;
      while(i < j) {
          if(str.charAt(i) != str.charAt(j)) return false;
          i++;
          j--;
      }
      return true;
  }
}

