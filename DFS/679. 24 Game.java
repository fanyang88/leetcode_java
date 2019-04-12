/*
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

*/

 /*
              [4,1,8,7]
        [4+1]     [4/1]    [1/4]  [1*4] [1-4]  [4-1]
        [5,8,7]   [4,8,7]  [0.25, 8, 7] [4, 8, 7]  [-3, 8, 7] [3, 8, 7]
        [5+8]| \[5-8] ....
        [13, 7]  [-3, 7] ....
        
        
        任意的两个数字之间都可能进行加减乘除，其中加法和乘法对于两个数字的前后顺序没有影响，但是减法和除法是有影响的，
        而且做除法的时候还要另外保证除数不能为零。
        我们要遍历任意两个数字，然后对于这两个数字，尝试各种加减乘除后得到一个新数字，
        将这个新数字加到原数组中，记得原来的两个数要移除掉，然后调用递归函数进行计算，
        我们可以发现每次调用递归函数后，数组都减少一个数字，那么当减少到只剩一个数字了，
        就是最后的计算结果，所以我们在递归函数开始时判断，如果数组只有一个数字，且为24，说明可以算出24，结果res赋值为true返回。
        
        这里我们的结果res是一个全局的变量，如果已经为true了，就没必要再进行运算了，所以第一行应该是判断结果res，为true就直接返回了。
        我们遍历任意两个数字，分别用p和q来取出，然后进行两者的各种加减乘除的运算，将结果保存进数组临时数组t，记得要判断除数不为零。
        然后将原数组nums中的p和q移除，遍历临时数组t中的数字，将其加入数组nums，然后调用递归函数，
        记得完成后要移除数字，恢复状态，这是递归解法很重要的一点。最后还要把p和q再加回原数组nums，这也是还原状态，参见代码如下：
     
        
*/


class Solution {
  boolean res = false;
  public boolean judgePoint24(int[] nums) {
      List<Double> list = new ArrayList<>();
      for(int n: nums) list.add((double)n);
      double eps = 0.001;
      dfs(list, eps);
      return res;
  }
  
  public void dfs(List<Double> list, double eps) {
      if(res) return; // it is true, return directly
      if(list.size()==1) { // this is the result
           if(Math.abs(list.get(0) - 24)< eps) res = true;
           return;
      }
      // compare i and j where i:0~size j=0~i
      for(int i=0; i<list.size(); i++) {
          for(int j=0; j<i; j++) {
              double p = list.get(i), q= list.get(j);
              List<Double> next = new ArrayList<>();
              next.addAll(Arrays.asList(p+q, p-q, q-p, p*q));
              if(Math.abs(p) > eps) next.add(q/p);
              if(Math.abs(q) > eps) next.add(p/q);
              list.remove(i);  // remove
              list.remove(j);
              for(double n: next) {
                  list.add(n); // add one
                  dfs(list, eps);
                  list.remove(list.size()-1); // recover, remove one
              }
              list.add(j, q); // recover add back
              list.add(i, p);
          }
      }
  }
}