/**
 * 
 * Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */


class Solution {
    public boolean isPalindrome(String s) {
        int n = s.length(), i=0, j = n-1;
        while(i < j) {
            while(i<n && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while(j>=0 && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if(i>=n || j<0)  return true; // e.g: ".,"
            if(Character.toLowerCase(s.charAt(j)) != Character.toLowerCase(s.charAt(i)))  return false;
            i++;
            j--;
        }
        return true;
    }
}