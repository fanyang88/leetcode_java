/*
Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.

Example 1:

Input: num = "123", target = 6
Output: ["1+2+3", "1*2*3"] 
Example 2:

Input: num = "232", target = 8
Output: ["2*3+2", "2+3*2"]
Example 3:

Input: num = "105", target = 5
Output: ["1*0+5","10-5"]
Example 4:

Input: num = "00", target = 0
Output: ["0+0", "0-0", "0*0"]
Example 5:

Input: num = "3456237490", target = 9191
Output: []
*/

/*

    1 + 2 + 3
    1 + 2 - 3
    1 + 2 * 3
    1 + 2   3
    1 - 2 + 3
    1 - 2 - 3
    1 - 2 * 3
    1 - 2   3
    ...
    
    total possiblity= 4^(2) =16
    if nums=1234 total = 4*4*4=4^3
    
    complexity= O(4^(n-1))
    
    exp    newVal  op  prev cur  new_exp  new_prev .       new_cur
    1+2      3 .   -    2 .  3    1+2-3    -3               0
    1+2      3 .   * .  2 .  3 .  1+2*3 .  prev*newVal      cur-prev + prev*newVal
    
    dfs(pos, cur, prev, exp) {
        n= substring(pos, pos+i)
        dfs(pos+i, cur+n, n, exp+"+"+n)
        dfs(pos+i, cur-n, -n, exp+"-"+n)
        dfs(pos+i, cur-prev+prev*n, prev*n, exp+"*"+n)
        
        corner case:
        substring[0]='0' & substring.length>1, break
        if num > max_value, break
        if pos=0, it is the first element, not +/-/*
    }

*/

class Solution {
  public List<String> addOperators(String num, int target) {
      List<String> res = new ArrayList<>();
      dfs(0, 0, 0, "", num, target, res);
      return res;
  }
  
  public void dfs(int pos, long cur, long prev, String exp, String num, int target, List<String> res) {
      if(pos == num.length()) { // come to the end
          if(cur==target) {
              res.add(exp);
          }
          return;
      }

      for(int l = 1; l <= num.length()-pos; l++){
          if(l>1 && num.charAt(pos) == '0') break;
          long n = Long.parseLong(num.substring(pos, pos+l));
          if(pos == 0){
              dfs(pos+l, n, n, exp+n, num, target, res);
          }
          else{
              dfs(pos+l, cur+n, n, exp+"+"+n, num, target, res);
              dfs(pos+l, cur-n, -n, exp+"-"+n, num, target, res);
              dfs(pos+l, cur-prev+prev*n, prev*n, exp+"*"+n, num, target, res);
          }
      }
  }
}


