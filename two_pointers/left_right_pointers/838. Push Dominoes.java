/*
There are N dominoes in a line, and we place each domino vertically upright.
In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left.
Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.
Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
Return a string representing the final state. 

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Note:

0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'
*/

/*

record the nearest L and R for each position
if R < L means at this position is it closer to R, so it is a R
   R . p . . L -> R R p . L L -> R R R L L L
if R > L means at this position is it closer to L, so it is a L
otherwise, it is a .

      0 1 2 3 4 5 6 7 8 9 10 11 12 13
        . L . R . . . L R . .  L  .  .  
 for each dot record it's shorest distance from 'R' and 'L' first
 i=0, posR = -1 i++ till i=3 str[i]=='R' posR = 3 
 i=4: since posR!=-1 R[4]=4-3=1  
 i=5: since posR!=-1 R[5]=5-3=2 
 i=6: since posR!=-1 R[6]=6-3=3 
 i=7 since str[i]='L' posR = -1
 i=8 str[i]=='R' posR = 8
 i=9: since posR!=-1 R[9]=9-8=1 
 i=10: since posR!=-1 R[10]=10-8=2 
 ...
 R= [0,0,0,0,1,2,3,0,0,1,2,0,0,0]
 
 i=11 posL = 11 
 i=10 since posL!=-1 L[10]=1
 i=9 since posL!=-1 L[9]=2
 i=8 str[i]=='R' posL = -1
 i=7 since str[i]='L' posL = 7
 i=6: since posL!=-1 L[6]=1 
 i=5: since posL!=-1 L[5]=2 
 i=4: since posL!=-1 L[4]=3
 ...
 
 L=[1,0,0,0,3,2,1,0,0,2,1,0,0,0]

 R[0,0,0,0,1,2,3,0,0,1,2,0,0,0]
 L[1,0,0,0,3,2,1,0,0,2,1,0,0,0]
 if(R===0 && L==0)   it is the same
 if(R===0 && L!==0)  it is L
 if(L===0 && R!==0)  it is R
 if(L > R)  it is R
 if(R > L)  it is L
 if(R == L)  it is .
 
*/

class Solution {
  public String pushDominoes(String dominoes) {
      int posR = -1, n=dominoes.length(), posL = -1;
      char[] arr = dominoes.toCharArray(), res= new char[n];
      int[] R = new int[n], L=new int[n];
      
      for(int i=0; i<n; i++) {
          if(arr[i] == 'L') {
              posR = -1;
          } else if(arr[i] == 'R') {
              posR = i;
          } else {
              if(posR !=-1) R[i] = i- posR;
          }
      }
      for(int i=n-1; i>=0; i--) {
          if(arr[i] == 'L') {
              posL = i;
          } else if(arr[i] == 'R') {
              posL = -1;
          } else {
              if(posL !=-1)  L[i] = posL - i;
          } 
      }
      
      for(int i=0; i<n; i++) {
          if(R[i]==0 && L[i]==0) {
               res[i] = arr[i];
          }else if(R[i]==0 && L[i]!=0) {
              res[i] = 'L';
          } else if(R[i]!=0 && L[i]==0) {
              res[i] = 'R';
          } else if(R[i] < L[i]) {
              res[i] = 'R';
          } else if(R[i] > L[i]) {
              res[i] = 'L';
          } else {
              res[i]= '.';
          }
      }
      return new String(res);
  }
}
