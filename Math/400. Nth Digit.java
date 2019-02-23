/*
Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...

Note:
n is positive and will fit within the range of a 32-bit signed integer (n < 231).

Example 1:

Input:
3

Output:
3
Example 2:

Input:
11

Output:
0

Explanation:
The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.

*/

/*
   e.g:  n=27 .   output=8
   
   123456789101112131415161718
   since 0-9=9   
   27-9=18 
   18/2=9 + 9=18  so it is 8
    
    个位数：1-9，一共9个,共计9个数字
 * 2位数：10-99,一共90个，共计180个数字
 * 3位数：100-999，一共900个，共计270个数字
 * 4位数，1000-9999，一共9000个，共计36000个数字
 * 以此类推，
 * 这样我们就可以首先定位到是哪个数，再找到其对应的数字
*/

class Solution {
  public int findNthDigit(int n) {
      long total = 9;
      int multiple=1, start=1;
      
      while( n > total * multiple) {
          n -= (int) total * multiple;
          total *= 10;
          multiple ++;
          start *=10;
      }
      start += (n-1)/multiple;
      String s = Integer.toString(start);
      return s.charAt((n-1) % multiple) - '0';
  }
}