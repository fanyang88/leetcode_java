/*
You have an initial power P, an initial score of 0 points, and a bag of tokens.

Each token can be used at most once, has a value token[i], and has potentially two ways to use it.

If we have at least token[i] power, we may play the token face up, losing token[i] power, and gaining 1 point.
If we have at least 1 point, we may play the token face down, gaining token[i] power, and losing 1 point.
Return the largest number of points we can have after playing any number of tokens.

 

Example 1:

Input: tokens = [100], P = 50
Output: 0
Example 2:

Input: tokens = [100,200], P = 150
Output: 1
Example 3:

Input: tokens = [100,200,300,400], P = 200
Output: 2
 

Note:

tokens.length <= 1000
0 <= tokens[i] < 10000
0 <= P < 10000
*/

/*
 
 Tranlations:
You have a bag of tokens, from which you can take whichever token you want, 
and after you take one, you can't put it back to the bag, meaning you can use every token at most once.
You start the game with P power and 0 point.

For every tokens[i], you can use it in either way:
- plus tokens[i] powers, and minus 1 point;
- or, minus tokens[i] powers, and plus 1 point.
(meaning you exchange your powers to get 1 point, or exchange your point to get more powers)
But you have to make sure that during the process, both your powers>=0 and points>=0, 
otherwise you would have to stop playing the game.
And you can use just some of the tokens (don't have to use all of them).
Your target is to get the maximum points possible.

e.g: [100,200,300,400]  P=200
since P=200, minus token[0] power, plus 1 point, we exchange power to points P=100 score=1
since power< 200, plus token[3] power, minus 1 point, exchange score to power, P=500 score=0
since power > 200, minus token[1], add 1 score, exchange power to points, P=300 score=1
since power =300, minus token[2], add 1 score, exchange power to score, score=2

two pointers, 
when restPower < left, 
    we don't have enough power, so we need to gain power by (use the largest we have so far)get the largest power is from right
    p += token[right] right--, score--;
otherwise, we have enough power to get score, so we sold the token on left(smallest we have so far) to get one more score
    p -= token[left] left++, score++;
*/

class Solution {
  public int bagOfTokensScore(int[] tokens, int P) {
      Arrays.sort(tokens);
      int left = 0, n = tokens.length, right = n-1, power = P, score=0, max=0;
      while(left <= right) {  // <= not <
          if(power < tokens[left]) { // we need power
              power += tokens[right--];
              score --;
              if(score < 0) break;
          } else {
              power -= tokens[left++];
              if(power < 0) break;
              score++;
          }
          max = Math.max(score, max);
      }
      return max;
  }
}
