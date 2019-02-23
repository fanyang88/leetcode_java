/*
Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.

 

Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.
 


 

Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6 
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

 

Example:

Input: m = 1, n = 1
Output: 9
*/
/*
    we can have a jump table to record the key would need be passes between two other keys
    table[1][3] = table[3][1] = 2;
    table[1][7] = table[7][1] = 4;
    table[3][9] = table[9][3] = 6;
    table[7][9] = table[9][7] = 8;
    table[1][9] = table[9][1] = table[2][8] = table[8][2] = table[3][7] = table[7][3] = table[4][6] = table[6][4] = 5;
    
 e.g: [1][7] = 4  we need to pass 4 if jump from 1 to 7
 
 since the numbers are symmetric, start from 1 is same as start from 3, 7, 9
 start from 2 is same as 4,6,8 
 we can only calculate the counts from 1 and 2 and 5

 keep a visited array to make sure the next one is visited or the middle one has visited or not 
*/


class Solution {
  public int numberOfPatterns(int m, int n) {
      boolean[] visited = new boolean[10];
      int[][] table= new int[10][10];
      table[1][3] = table[3][1] = 2;
      table[1][7] = table[7][1] = 4;
      table[3][9] = table[9][3] = 6;
      table[7][9] = table[9][7] = 8;
      table[1][9] = table[9][1] = table[2][8] = table[8][2] = table[3][7] = table[7][3] = table[4][6] = table[6][4] = 5;
      
      return dfs(1, m, n, 1, 0, table, visited) * 4 +
             dfs(2, m, n, 1, 0, table, visited) * 4 +
             dfs(5, m, n, 1, 0, table, visited);
  }
  
  // this is the key part, assume m=1, n=1 next length=2 >1 return 
  // assume m=2, n=3, if current len=3 next length would be 4, exceed it, return
  public int dfs(int start, int m, int n, int len, int count, int[][] table, boolean[] visited) {
      if(len>=m)  count++;
      len++;
      if(len > n) return count;
      visited[start] = true;
      for(int i=1; i<=9; i++) {
          if(visited[i])  continue; // next pos visited, skip
          int mid = table[start][i];
          if(mid !=0 && visited[mid]==false)  continue;  // the key in the jump is not visited
          count = dfs(i, m, n, len, count, table, visited);
      }
      visited[start] = false;
      return count;
  }
}
