/*
A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from S to Goat Latin. 

 

Example 1:

Input: "I speak Goat Latin"
Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
Example 2:

Input: "The quick brown fox jumped over the lazy dog"
Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 

Notes:

S contains only uppercase, lowercase and spaces. Exactly one space between each word.
1 <= S.length <= 150.
*/

class Solution {
  public String toGoatLatin(String S) {
      int count=1, i=0, j=0;
      String res= "";
      for(; i<S.length(); i++) {
          if(S.charAt(i) == ' ') {
              res += process(S.substring(j, i), count) +" ";
              j=i+1;
              count++;
          }
      }
      res += process(S.substring(j, i), count);
      return res;
  }
  
  public String process(String s, int count) {
      char c = s.charAt(0);
      if(c== 'A' || c=='a' ||c=='e' ||c=='E' || c=='I' ||c=='i'||c=='o' ||c=='O'|| c=='u'||c=='U') {
          s+="ma";
      } else {
          s=s.substring(1)+c+"ma";
      }
      while(count-->0) {
          s+="a";
      }
      return s;
  }
}
