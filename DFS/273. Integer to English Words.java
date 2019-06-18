/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
*/


/*
divide a number into four parts: 
          1,234,567,891
        XXX,XXX,XXX,XXX
    count = 0, num % 1000 = 891 getnumber(891)->"...", num = num/1000=1234567
    count = 1, num % 1000 = 567 getnumber(567)->"... +thousand", num = num/1000=1234
    count = 2, num % 1000 = 234 getnumber(234)-> "...Million", num = num/1000=1
    count = 3, num % 1000 = 1 getnumber(1)->"...billion", num = num/1000=0
        
*/
class Solution {
private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
private final String[] THOUSANDS = {"", " Thousand ", " Million ", " Billion "};
  public String numberToWords(int num) {
      int i=0;
      if(num==0)  return "Zero";
      String res = "";
      while(num > 0) {
          res = dfs(num % 1000) + (num %1000==0 ? "" : THOUSANDS[i])+res; // This is the key!!!
          num = num/1000; 
          i++;
      }
      return res.trim();
  }
  
  public String dfs(int n) {
      // n is at most 3 digit
      if(n <20) return LESS_THAN_20[n];
      if(n < 100) return TENS[n/10]  + (n%10==0 ? "" : " " + LESS_THAN_20[n%10]);
      else return LESS_THAN_20[n/100] + " Hundred" + (n %100 ==0 ? "" : " " + dfs(n%100));
  }
}
