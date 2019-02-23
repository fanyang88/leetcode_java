/**
 A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3 
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.
 */

class Solution {
  public int strobogrammaticInRange(String low, String high) {
      // use 247
      int count=0;
      List<String> res = new ArrayList<String>();
      for(int i=low.length(); i<=high.length(); i++) {
          res.addAll(dfs(i, i));
      }
      for(String num: res) {
          if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||
             (num.length() == high.length() && num.compareTo(high) > 0)) continue;
      count++;
      }
      return count;
  }
  
  public List<String> dfs(int level, int n) {
      if(level==0) return new ArrayList<String>(Arrays.asList(""));
      if(level==1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
      List<String> list = dfs(level-2, n);
      List<String> res = new ArrayList<String>();
      for(String s: list) {
          if(level!=n) res.add("0"+s+"0");
          res.add("1"+s+"1");
          res.add("8"+s+"8");
          res.add("6"+s+"9");
          res.add("9"+s+"6");
      }
      return res;
  }
}