/*
A string of '0's and '1's is monotone increasing if it consists of some number of '0's (possibly 0), followed by some number of '1's (also possibly 0.)

We are given a string S of '0's and '1's, and we may flip any '0' to a '1' or a '1' to a '0'.

Return the minimum number of flips to make S monotone increasing.

 

Example 1:

Input: "00110"
Output: 1
Explanation: We flip the last digit to get 00111.
Example 2:

Input: "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.
Example 3:

Input: "00011000"
Output: 2
Explanation: We flip to get 00000000.
 

Note:

1 <= S.length <= 20000
S only consists of '0' and '1' characters.

*/

/*
    Skip 0's until we encounter the first 1.
Keep track of number of 1's in onesCount (Prefix).
Any 0 that comes after we encounter 1 can be a potential candidate for flip. Keep track of it in flipCount.
If flipCount exceeds oneCount - (Prefix 1's flipped to 0's)
a. Then we are trying to flip more 0's (suffix) than number of 1's (prefix) we have.
b. Its better to flip the 1's instead.

`````0123456789
e.g: 0001100010
i=0 since oneCount==0 continue
i=1 since oneCount==0 continue
i=2 since oneCount==0 continue
i=3 it is 1, oneCount++
i=4 it is 1, oneCount++
i=5 since oneCount>0 this is a 0 after 1, flipCount++=1
i=6 since oneCount>0 this is a 0 after 1, flipCount++=2
i=7 since oneCount>0 this is a 0 after 1, flipCount++=3
    since flipCount > oneCount, we should flip 1 instead of 0, so flipCount=2
i=8 it is 1, oneCount++=3
i=9 since oneCount>0 this is a 0 after 1, flipCount++=3 
so the answer is 3

*/


class Solution {
  public int minFlipsMonoIncr(String S) {
      int oneCount=0, flipCount=0;
      for(char c: S.toCharArray()) {
          if(c == '0') {
              if(oneCount==0) continue;
              flipCount++;
          } else {
              oneCount++;
          }
          if(flipCount> oneCount) flipCount =oneCount; // This is the key!!!!
      }
      return flipCount;
  }
}
