/**
 * The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, 
it returns 3 if there is only 3 characters left in the file.

By using the read4 API, 
implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1: 

Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""
Example 2: 

Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""
 */

 /* The read4 API is defined in the parent class Reader4.
      int read4(char[] buf); */
/*
  157 is focus on read the whole file. e.g:
    Input: buf = "abcde", n = 5 
    Output: "abcde"
    In 157, everytime we try to read as many as chars as we can till we hit n 
    
    this question is focus on call read mutiple times, each time we put certain chars in buf, till we hit n, everytime, we should return the buf length, not the whole file
    read4() has its own file pointer, much like FILE *fp in C.
    file is "abc", initially fp points to 'a'
    read(1) // returns buf = "a", now fp points to 'b'
    read(1) // returns buf = "b", now fp points to 'c'
    read(2) // returns buf = "c", now fp points to end of file
    char *buf is destination not source, similar to that of scanf("%s", buf), OJ outputs this buf value.

    Each time read() is called, we need to provide a new buf to store read characters, 
    therefore, the return value of int read() is simply the length of buf.
*/


public class Solution extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
      char[] cache = new char[4];
      int readPos = 0, writePos=0;
  // we need to define readPos and writePos to be outside of the function, since next read call would continue use the last readPos and writePos to execute.
  
  public int read(char[] buf, int n) {
      int total = 0;
      while(total < n) {
          if(readPos == writePos) {  // need to call read4 again to get more chars
              writePos = read4(cache);
              readPos = 0;
              if(writePos == 0) return total; // no more chars can be read
          }
          buf[total++] = cache[readPos++];
      }
      return n;
  }
}