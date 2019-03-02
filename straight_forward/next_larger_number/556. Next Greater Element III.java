/*
Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1
*/

/*
At first, lets look at the edge cases -

If all digits sorted in descending order, then output is always “Not Possible”. For example, 4321.
If all digits are sorted in ascending order, then we need to swap last two digits. For example, 1234.
For other cases, we need to process the number from rightmost side (why? because we need to find the smallest of all greater numbers)
Now the main algorithm works in following steps -

I) Traverse the given number from rightmost digit, keep traversing till you 
find a digit which is smaller than the previously traversed digit. For example, 
if the input number is “534976”, we stop at 4 because 4 is smaller than next digit 9. 
If we do not find such a digit, then output is “Not Possible”.

II) Now search the right side of above found digit ‘d’ for the smallest digit greater than ‘d’. 
For “534976″, the right side of 4 contains “976”. The smallest digit greater than 4 is 6.

III) Swap the above found two digits, we get 536974 in above example.

IV) Now sort all digits from position next to ‘d’ to the end of number. 
The number that we get after sorting is the output. 
For above example, we sort digits in bold 536974. We get “536479” which is the next greater number for input 534976.
*/

class Solution {
  public int nextGreaterElement(int n) {
      char[] strs= (""+n).toCharArray();
      int pos = -1, len= strs.length, pos2=-1;
      for(int i=len-1; i>=1; i--) {
          if(strs[i-1] < strs[i]) {
              pos = i-1;
              break;
          } 
      }
      if(pos == -1)  return -1; 
      // pos now point to the first number need to swap, 
      // we need to find the smallest number larger than first in range pos+1, n-1
      for(int i=len-1; i>=pos+1; i--) {
          if(strs[i] > strs[pos]) {
              pos2=i;
              break;
          }
      }
      // swap pos2 with pos
      char t = strs[pos];
      strs[pos] = strs[pos2];
      strs[pos2] = t;
      
      // sort number from pos to n-1
      Arrays.sort(strs, pos+1, len);
      long val = Long.parseLong(new String(strs));
      return (val <= Integer.MAX_VALUE) ? (int) val : -1;
  }
}
