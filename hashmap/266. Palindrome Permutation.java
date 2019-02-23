/**Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true
 */

class Solution {
  public boolean canPermutePalindrome(String s) {
      Set<Character> set = new HashSet<>();
      for(char c : s.toCharArray()) {
          if(set.contains(c)) {
              set.remove(c);
          } else {
              set.add(c);
          }
      }
      return set.size()==0 || set.size() == 1;
  }
}