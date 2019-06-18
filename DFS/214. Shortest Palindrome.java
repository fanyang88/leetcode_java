/*
Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

Example 1:

Input: "aacecaaa"
Output: "aaacecaaa"
Example 2:

Input: "abcd"
Output: "dcbabcd"
*/

/*

The intuition: We know that it is possible to turn any string into a palindrome by simply concatenating it with its reverse: Ex. aabba + abbaa = aabbaabbaa. But this is not the shortest palindrome. If we could find a palindrome that is a prefix of aabba we could place it in the middle. By placing it in the middle we no longer would need to reverse it and we could only need to reverse the remainder of the string.
So the longest prefix of aabba that is a palindrome is aa. We can place aa in the middle and perform the reverse trick around it: abb + aa + bba = abbaabba which is the shortest palindrome.

    for (int i = s.length() - 1; i >= 0; i--) {
        if (s.charAt(i) == s.charAt(j)) { j += 1; }
    }
         0123
    e.g: abcd
    i=3 j=0 i--=2
    i=2 j=0 i--=1
    i=1 j=0 i--=0
    i==0 j==0 j++=1 so the suffix is substring(1)=bcd
This loop is tricky. Since the goal is to reduce the number of elements that you have to reverse and concatenate we calculate j as the index which splits the array into two parts.

s.substring(j) is the suffix that (from the calculation of equal elements) has to be reversed in order to create a palindrome
s.substring(0,j) is the prefix which may or may not be a palindrome, but is passed to the next recursive call in order to determine.
Because i goes from len-1 to 0 and j goes from 0 to len-1 they will always cross and s.charAt(i) will equal s.charAt(j). This means j will always be greater than 0, causing the recursion to terminate and guaranteeing a worse case of O(N^2)

      01234567
      aacecaaa
      i=7 j=0 0==7 i=6 j=1
      i=6 j=1 1==6 i=5 j=2
      i=5 j=2 i!=j i=4 j=2
      i=4 j=2 2==4 i=3 j=3
      i=3 j=3 3==3 i=2 j=4
      i=2 j=4 2==4 i=1 j=5
      i=1 j=5 1==5 i=0 j=6
      i=0 j=6 0==6 i=-1 j=7
      suffix = (7) = a  
      a+aacecaa + a
      
      next loop, since aacecaa is a palindrome, return itself
      the answer is aaacecaaa
*/

class Solution {
  public String shortestPalindrome(String s) {
      int j=0;
      for(int i=s.length()-1; i>=0; i--) {
          if(s.charAt(i) == s.charAt(j)) j++;
      }
         if(j==s.length()) return s; // j come to the end, the string is a panlindrome already
      String suffix = s.substring(j, s.length());
      
      return new StringBuilder(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
  }
}

