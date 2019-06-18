/**
 * 
 Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
 */

 /*
    This question is easy to go overflow even if use long, so we need to use collections sort
    sort all numbers first: [9, 5, 34, 3, 30] by adding a+b
    e.g: [3,30,34,5,9]
    3+30 =330 > 303 use 330
    330+34=33034 < 34+330=34330 use 34330
    34330+5=343305 < 5+34330 use 534330
    9+534330 > 534330+9 use 9534330 which is answer
*/

class Solution {
  public String largestNumber(int[] nums) {
      List<String> list= new ArrayList<String>();
      String res="";
      for(int num: nums)  list.add(Integer.toString(num));
      
      Collections.sort(list, (a, b)->(int)(Long.parseLong(b+a)- Long.parseLong(a+b)));
      for(String str: list)  res += str;
      
      int i=0;
      while(i<res.length() && res.charAt(i)=='0') i++;
      return i==res.length() ? "0" : res.substring(i);
  }
}