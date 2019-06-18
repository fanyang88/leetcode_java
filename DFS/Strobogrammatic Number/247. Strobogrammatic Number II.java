/**
 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */

 /*
  n=0 there is ""
  n=1 there is 0, 1, 8
  n=2 there is 11, 88, 69, 96, which is list n=0 is empty string +'11'/'88'/'69'/'96'
  n=3 there is : for 1-> 111, 818, 619, 916
                 for 8-> 181, 888, 689, 986
                 for 0-> 101, 808, 609, 906
    add(11, 88, 69, 96 at n=1: 0,1,8)
n=3 ---------------------------------- n=1
*/

class Solution {
  public List<String> findStrobogrammatic(int n) {
      return dfs(n, n);
  }
  
  public List<String> dfs(int level, int n) {
      if(level==0)  return new ArrayList<String>(Arrays.asList(""));
      if(level==1)  return new ArrayList<String>(Arrays.asList("0", "1", "8"));
      
      List<String> res = new ArrayList<String>();
      List<String> list = dfs(level - 2, n);
      
      for (String s : list) {
          if (n != level) res.add("0" + s + "0"); // if it is not the outmost, we can add '0' to it.
          res.add("1" + s + "1");
          res.add("6" + s + "9");
          res.add("8" + s + "8");
          res.add("9" + s + "6");
      }
      return res;
  }
}
