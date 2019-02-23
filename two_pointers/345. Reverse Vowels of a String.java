/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
*/


/*
  vowels are: list = ['a', 'e', 'i', 'o','u', 'A', 'E', 'I', 'O','U'];
*/
class Solution {
  public String reverseVowels(String s) {
      Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o','u', 'A', 'E', 'I', 'O','U'));
      char[] str = s.toCharArray();
      for(int i=0, j=str.length-1; i<j; i++) {
          if(set.contains(str[i]) && set.contains(str[j])) {
              char temp = str[i];
              str[i] = str[j];
              str[j] = temp;
              j--;
          } else if(set.contains(str[i])) {  // i point to a vowel, i stay the same, j move
              i--;
              j--;
          } else if(set.contains(str[j])){  // j point to vowel, j stay the same, i move
              //do nothing
          } else  { 
              j--;
          }
          
      }
      return new String(str);
  }
}
