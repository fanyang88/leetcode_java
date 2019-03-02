/*
You are given a string representing an attendance record for a student. The record only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

You need to return whether the student could be rewarded according to his attendance record.

Example 1:
Input: "PPALLP"
Output: True
Example 2:
Input: "PPALLL"
Output: False
*/

class Solution {
  public boolean checkRecord(String s) {
      boolean MoreThan2L=false;
      int countA = 0;
      for(int i=0; i<s.length(); i++) {
          int j= i;
          while(j < s.length() && s.charAt(j) == 'L') j++;
          if(j-i>2) MoreThan2L = true;
          if(s.charAt(i) == 'A') countA++;
      }
      return countA <=1 && !MoreThan2L;
  }
}
