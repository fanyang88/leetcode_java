/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1:

Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".
Example 2:

Input: buf = "abcde", n = 5 
Output: "abcde"
Note:
The read function will only be called once for each test case.
 */

 /* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */

/*
the read() method just does two things:
It returns an int to show how long the file is.
After we pass char[] to the read() method, and the char[] is filled with characters extracted from the files by read().
Since we cannot return multiple values, we just return an int to show the length of the file. The content of the file is stored in the char[]. The trick is we could actually visit the char[] after we use read() to get the content of the file, which does not require return statement to prevent multiple return values.
*/

public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
 char[] cache = new char[4];
  public int read(char[] buf, int n) {
      
      int total = 0; // indicate how many chars in buf
      while(total < n) {
          int len = read4(cache);
          if(len==0)  break; // no more chars can be read
          for(int i=0; i<len;i++) {
              if(total == n) break;  // case such as n=0
              buf[total++] = cache[i];   
          }
      }
      return total;
  }
}