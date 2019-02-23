/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
*/

/*
    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * sort by k first, if k equals, sort by h: 
        [7, 0] [7, 1] [6, 1] [5, 0] [5, 2] [4, 4]
 * use a stack,  
        push [7,0], 
        since stack size=1 = 1, directly push [7, 1]   
        [6, 1]: since stack size=2(2 ppl greater) > 1, we need to insert [6, 1] at pos 1: [7,0][6,1][7,1]
        [5, 0]: since stack size=3(3 ppl greater) > 0, insert [5,0] at 0: [5,0][7,0][6,1][7,1]
        [5, 2]: since stack size=4(4 ppl greater) > 2, insert [5,2] at 2: [5,0][7,0][5,2][6,1][7,1]
        [4, 4]: since stack size=5(5 ppl greater) > 4, insert [4,4] at 4: [5,0][7,0][5,2][6,1][4,4][7,1] 

*/

class Solution {
  public int[][] reconstructQueue(int[][] people) {
       Arrays.sort(people, new Comparator<int[]>(){
          public int compare(int[] arr1, int[] arr2){
              if(arr1[0] == arr2[0])
              return arr1[1] - arr2[1];
              else
              return arr2[0] - arr1[0];
          } 
      });
      List<int[]> res = new ArrayList<>();
      for(int[] p : people) {
          if(res.size() <= p[1]) {
              res.add(p);
          } else {
              res.add(p[1], p);
          }
      }
      for(int i=0; i<res.size(); i++) {
          people[i] = res.get(i);
      }
      return people;
  }
}