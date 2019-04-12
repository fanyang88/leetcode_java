/*
Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the string is "0".

 

Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
Example 2:

Input: 3
Output: "111"
Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
Example 3:

Input: 4
Output: "100"
Explantion: (-2) ^ 2 = 4
 

Note:

0 <= N <= 10^9
*/

/*

   Suppose we're working with 8 bit quantities (for simplicity's sake) and suppose we want to find how -28 would be expressed in two's complement notation. First we write out 28 in binary form.
00011100
Then we invert the digits. 0 becomes 1, 1 becomes 0.
11100011
Then we add 1.
11100100

e.g: -1= 01 invert=10 + 1= 11

   100000
    01100
    -----
    10100
    2-> 110 = (-2) ^ 2 + (-2) ^ 1
    3-> 111 = (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0
    4-> 100 = (-2) ^ 2 
    5-> 101 = (-2) ^ 2 + (-2) ^ 0
    
    3 3&1=1 N=-(N>>1)=-1
    -1= 11 & 1=1 N =  -(N>>1)=1
    N=1 N&1=1 N =  -(N>>1)=0
    
    e.g: 4
    N=4 =100 N & 1=0 N = -(N>>1) =-2
    N=-2= 101+1=110 N & 1=0 res=00  N = -(N>>1) = 1
    N=1 N & 1=1 N=0
    
    O(logN)
*/

class Solution {
  public String baseNeg2(int N) {
      String res = "";
      while(N !=0) {
          res = Integer.toString(N & 1) + res;
          N = -(N >> 1);
      }
      return res=="" ? "0" : res;
  }
}