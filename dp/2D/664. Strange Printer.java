/*
There is a strange printer with the following two special requirements:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

Example 1:
Input: "aaabbb"
Output: 2
Explanation: Print "aaa" first and then print "bbb".
Example 2:
Input: "aba"
Output: 2
Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
Hint: Length of the given string will not exceed 100.
*/

/*
   dp[i][j]表示打印出字符串[i, j]范围内字符的最小步数，
   首先如果只有一个字符，比如字符串是"a"的话，那么直接一次打印出来就行了。如果字符串是"ab"的话，那么我们要么先打印出"aa"，再改成"ab"，或者先打印出"bb"，再改成"ab"。
   同理，如果字符串是"abc"的话，就需要三次打印。那么一个很明显的特征是，如果没有重复的字符，打印的次数就是字符的个数。
   这题的难点就是要处理有相同字符的情况:
        比如字符串是"aba"的时候，我们先打"aaa"的话，两步就搞定了，如果先打"bbb"的话，就需要三步。
        我们再来看一个字符串"abcb"，我们知道需要需要三步，我们看如果把这个字符串分成两个部分"a"和"bcb"，它们分别的步数是1和2，加起来的3是整个的步数。
        而对于字符串"abba"，如果分成"a"和"bba"，它们分别的步数也是1和2，但是总步数却是2。这是因为分出的"a"和"bba"中的最后一个字符相同。
        对于字符串"abbac"，因为位置0上的a和位置3上的a相同，那么整个字符串的步数相当于"bb"和"ac"的步数之和，为3。那么分析到这，是不是有点眉目了？
        我们关心的是字符相等的地方，对于[i, j]范围的字符，我们从i+1位置上的字符开始遍历到j，如果和i位置上的字符相等，我们就以此位置为界，将[i+1, j]范围内的字符拆为两个部分，
        将二者的dp值加起来，和原dp值相比，取较小的那个。所以我们的递推式如下:

dp[i][j] = min(dp[i][j], dp[i + 1][k - 1] + dp[k][j])      (s[k] == s[i] and i + 1 <= k <= j)
split i+1~j array to be: from i+1 to k-1 and k ~j
    
    要注意一些初始化的值，dp[i][i]是1，因为一个字符嘛，打印1次，还是就是在遍历k之前，dp[i][j]初始化为 1 + dp[i + 1][j]，为啥呢，可以看成在[i + 1, j]的范围上多加了一个s[i]字符，最坏的情况就是加上的是一个不曾出现过的字符，步数顶多加1步，注意我们的i是从后往前遍历的，
    
    e.g:    a   b    a
    0       1   2    2
    1           1    2
    2                1(i==j)
    
    when i=1 j=1 dp[1][1]=1 
             j=2 since there is no k from 2 to 2 equals a[1], dp[1][2] = dp[2][2]+1=2
    when i=0 j=0 dp[0][0]=1
             j=1 dp[0][1] = dp[1][1]+1=2
             j=2 since a[2]=a[0] k=2 dp[0][2] = min(dp[0+1][2-1] + dp[2][2], dp[1][2] +1)=2
    so answer is 2
    
*/

class Solution {
  public int strangePrinter(String s) {
      int n = s.length();
      if(n==0)  return 0;
      int[][] dp = new int[n][n];
      for(int i=n-1; i>=0; i--) {
          for(int j=i; j<n; j++) {
              dp[i][j] = i==j ? 1 : dp[i+1][j] +1;
              for(int k=i+1; k<=j; k++) {
                  if(s.charAt(k) == s.charAt(i)) {
                      dp[i][j] = Math.min(dp[i][j], dp[i+1][k-1] + dp[k][j]);
                  }
              }
          }
      }
      return dp[0][n-1];
  }
}
