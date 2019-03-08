/*

Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable x and its coefficient.

If there is no solution for the equation, return "No solution".

If there are infinite solutions for the equation, return "Infinite solutions".

If there is exactly one solution for the equation, we ensure that the value of x is an integer.

Example 1:
Input: "x+5-3+x=6+x-2"
Output: "x=2"
Example 2:
Input: "x=x"
Output: "Infinite solutions"
Example 3:
Input: "2x=x"
Output: "x=0"
Example 4:
Input: "2x+3x-6x=x+2"
Output: "x=-1"
Example 5:
Input: "x=x+2"
Output: "No solution"

*/

/*
    x+5-3+x=6+x-2
    split into 2:
    x+5-3+x
    integer = 2  x-param= 2 
    6+x+-2
    integer = 4 x-param=1
    
*/

class Solution {
  public String solveEquation(String equation) {
      String p1 = equation.substring(0, equation.indexOf("="));
      String p2 = equation.substring(equation.indexOf("=")+1);
      int[] res1 = process(p1);
      int[] res2 = process(p2);
      int integerPart = res2[0] - res1[0];
      int x_param = res1[1] - res2[1];
      if(x_param ==0) return integerPart ==0 ?  "Infinite solutions" : "No solution";
      return "x=" + (integerPart / x_param);
  }
  
  public int[] process(String s) {
      s = s.replaceAll("-", "+-");
      int x=0, i=0;
      for(String e: s.split("\\+")) {
          if(e.equals("x")) {
              x+=1;
          } else if(e.equals("-x")) {
              x-=1;
          } else if(e.contains("x")) {
              x += Integer.parseInt(e.substring(0, e.indexOf("x")));
          } else {
              i+= e.equals("") ? 0 : Integer.parseInt(e);
          }
      }
      return new int[]{i, x};
  }
}
