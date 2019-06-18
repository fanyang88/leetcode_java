/**
 * 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
 */

class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)  return s;
        StringBuffer[] matrix = new StringBuffer[numRows];
        int index=0, x=0;
        for(int i=0; i< numRows; i++) matrix[i] = new StringBuffer();
        
        while(index < s.length()) {
            if(x == 0) {
                while(x < numRows && index < s.length()) {
                    matrix[x++].append(s.charAt(index++));
                }
            } else {
                x-=2; // x point to last second row
                while(x > 0 && index < s.length()) {
                    matrix[x--].append(s.charAt(index++));
                }
            }
        }

        for(int i=1; i<numRows; i++) matrix[0].append(matrix[i]);
        
        return matrix[0].toString();
    }
}