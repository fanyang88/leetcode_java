/*
You are playing the following Nim Game with your friend: 
There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones.
 The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. 
Write a function to determine whether you can win the game given the number of stones in the heap.

Example:

Input: 4
Output: false 
Explanation: If there are 4 stones in the heap, then you will never win the game;
             No matter 1, 2, or 3 stones you remove, the last stone will always be 
             removed by your friend.
*/

/*
 n=1 you win
 n=2 you win
 n=3 you win
 n=4 you lose
 n=5 you-1, next-3 you-1 you win
 mod 4
 
 博弈论中极为经典的尼姆游戏。有总数为n的石头，每个人可以拿1~m个石头，两个人交替拿，拿到最后一个的人获胜。究竟是先手有利，还是后手有利？

1个石子，先手全部拿走；
2个石子，先手全部拿走；
3个石子，先手全部拿走；
4个石子，后手面对的是先手的第1，2，3情况，后手必胜；
5个石子，先手拿走1个让后手面对第4种情况，后手必败；
6个石子，先手拿走2个让后手面对第4种情况，后手必败；
……
容易看出来，只有当出现了4的倍数，先手无可奈何，其余情况先手都可以获胜。
（石子数量为4的倍数）后手的获胜策略十分简单，每次取石子的数量，与上一次先手取石子的数量和为4即可；
（石子数量不为4的倍数）先手的获胜策略也十分简单，每次都令取之后剩余的石子数量为4的倍数（4*0=0，直接拿光），他就处于后手的位置上，利用上一行的策略获胜。


*/

class Solution {
  public boolean canWinNim(int n) {
      return n%4==0 ? false : true;
  }
}