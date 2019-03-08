/*
Imagine you have a special keyboard with the following keys:

Key 1: (A): Print one 'A' on screen.

Key 2: (Ctrl-A): Select the whole screen.

Key 3: (Ctrl-C): Copy selection to buffer.

Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.

Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.

Example 1:
Input: N = 3
Output: 3
Explanation: 
We can at most get 3 A's on screen by pressing following key sequence:
A, A, A
Example 2:
Input: N = 7
Output: 9
Explanation: 
We can at most get 9 A's on screen by pressing following key sequence:
A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
Note:
1 <= N <= 50
Answers will be in the range of 32-bit signed integer.
*/


/*
    这里 n = 7 ，我们使用了 3 （j=4，i-j=7-4）步来获得 AAA
接着我们使用 4 步: Ctrl A, Ctrl C, Ctrl V, Ctrl V, 来获得j - 1 = 4 - 1 = 3 份 AAA 的复制。

我们要么一点不使用copy操作，就是我们初始化 DP[ i ] 为 i 的情形。要么使用copy操作，这样我们要留3步给 Ctrl A, Ctrl C, Ctrl V ，所以 j 至少是 3.

得到公式 dp[ i ] = max ( dp[ i ] , dp[ i - j ] * ( j - 1 ) )      {  j in [ 3, i )  }
我们要生成n个‘A’可以有两种途径：1）在键盘上敲击n次‘A’；
2）采用i步生成maxA(i)个‘A’，然后用n - i步执行:
        1次Ctrl A，
        1次Ctrl C，
        n - i- 2次Ctrl V，
    这样最终就可以生成n - i - 1个maxA(i)。
    由于n - i - 2 >= 1，所以i <= n - 3。如果我们定义dp[i]表示i步可以生成的‘A’的最大个数，
    则可以方便地实现基于动态规划的版本。

    we loop from n = 1 to N
    i from 1 to N-3
    dp[n] intitally = n, no copy paste
    dp[n] = dp[i] * (n-i-1)
    answer is dp[N]
*/
class Solution {
  public int maxA(int N) {
      int[] dp = new int[N+1];
      for(int n=1; n<=N; n++) {
          dp[n] = n;
          for(int i=1; i<=n-3; i++) {
              dp[n] = Math.max(dp[n], dp[i]* (n-i-1));
          }
      }
      return dp[N];
  }
}
