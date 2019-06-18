/*
Given a string of numbers and operators, 
return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1:

Input: "2-1-1"
Output: [0, 2]
Explanation: 
((2-1)-1) = 0 
(2-(1-1)) = 2
Example 2:

Input: "2*3-4*5"
Output: [-34, -14, -10, -10, 10]
Explanation: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10

*/

/*
    ways("2-1-1")
    case 1: ways("2") X ways("1-1") = 2-0=2
    case 2: ways("2-1") X ways("1") = 1-1=0
    whenever we meet a operator, we break the string to 2 parts
    
    "2*3-4*5"
    case 1: ways(2) * ways(3-4*5)
    ways(3-4*5) = ways(3) - ways(4*5) = -17
                  ways(3-4) * ways(5) = -5
    list1 = {2}
    list2 = {-17, -5} res.add(2*(-17), 2*(-5))
    
    list1 = dfs(sub1);
    list2 = dfs(sub2);
    for(i1: list1) {
        for(i2: list2) {
            res.add(i1 * i2);
        }
    }
    
      [34,-10]                 2*3-4*5
                    /*         |-          \*
      [2]        p1: 2         p1:2*3        p1: 2*3-4
    [17,-5]   p2: 3-4*5     p2:4*5        p2: 5
                  /      \
                 /         \
        [3]     /-[20]       \*
        p21:3 p22:4*5    p21:3-4[-1]   p22:5[5]
        |      /   \      /    \      
        3     4     5    3      4
        list 
    is empty
    return [3]
    

*/

class Solution {
  public List<Integer> diffWaysToCompute(String input) {
      List<Integer> res = new ArrayList<Integer>();
      if(isNumber(input)) {
          res.add(Integer.parseInt(input));
          return res;
      } 
      for(int i=0; i<input.length(); i++) {
          char chr = input.charAt(i);
          if(chr == '*' || chr == '-' && chr == '+') { // we split to two parts
            List<Integer> l1 = diffWaysToCompute(input.substring(0, i));
            List<Integer> l2 = diffWaysToCompute(input.substring(i+1));
            for(int i1 : l1) {
              for(int i2: l2) {
                  if(chr == '+') {
                      res.add(i1+i2);
                  } else if(chr == '-') {
                      res.add(i1-i2);
                  } else {
                      res.add(i1*i2);
                  }
              }
            } 
          }
      }
      return res;
  }
  
  public boolean isNumber(String s) {
      for(int i=0; i<s.length(); i++) {
          if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i) == '*')  return false;
      }
      return true;
  }
}