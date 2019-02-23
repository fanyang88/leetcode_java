/*
You are playing the following Bulls and Cows game with your friend: 
You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. 

Please note that both secret number and friend's guess may contain duplicate digits.

Example 1:
Input: secret = "1807", guess = "7810"

Output: "1A3B"

Explanation: 1 bull and 3 cows. The bull is 8, the cows are 0, 1 and 7.
Example 2:

Input: secret = "1123", guess = "0111"

Output: "1A1B"

Explanation: The 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow.
Note: You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.
*/

/*
   1123
   0111
   
   i=0, 1!=0 map[1] = 1
   i=1, 1==1 bull++
   i=2, 2!=1 map[2] = 1
   i=3, 3!=1 map[3] = 1
   
   go through 0111 
   i=0 since map[0] not exist, do nothing
   i=1 guess[1] == secret[1] 
   i=2 guess[1] != secret[1]  and map[1] exist cow++ map[1]==0
   i=3 since map[1] =0, do nothing
*/

class Solution {
  public String getHint(String secret, String guess) {
      int bulls=0, cows = 0;
      int[] map = new int[26];
      // loop 1, need to count how many chars(except bulls) and their frequency from secret
      for(int i=0; i<secret.length(); i++) {
          if(secret.charAt(i) == guess.charAt(i)) {
              bulls++;
          } else {
              map[secret.charAt(i) - '0'] ++;
          }
      }
      // loop2 go through guess, if a char is not a bull and exist in map, means it is a cow
      for(int i=0; i<guess.length(); i++) {
          if(secret.charAt(i) != guess.charAt(i) && map[guess.charAt(i) - '0'] > 0) {
             cows++;
             map[guess.charAt(i) - '0'] --;
          }
      }
      return bulls+"A"+cows+"B";
  }
}
