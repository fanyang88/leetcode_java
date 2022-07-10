/*
In a row of trees, the i-th tree produces fruit with type tree[i].

You start at any tree of your choice, then repeatedly perform the following steps:

Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.

You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.

What is the total amount of fruit you can collect with this procedure?

 

Example 1:

Input: [1,2,1]
Output: 3
Explanation: We can collect [1,2,1].
Example 2:

Input: [0,1,2,2]
Output: 3
Explanation: We can collect [1,2,2].
If we started at the first tree, we would only collect [0, 1].
Example 3:

Input: [1,2,3,2,2]
Output: 4
Explanation: We can collect [2,3,2,2].
If we started at the first tree, we would only collect [1, 2].
Example 4:

Input: [3,3,3,1,2,1,1,2,3,3,4]
Output: 5
Explanation: We can collect [1,2,1,1,2].
If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 

Note:

1 <= tree.length <= 40000
0 <= tree[i] < tree.length
*/

// same as LC 159
/*
        1,2,3,2,2
    i=0 count=1 map[1]=1
    i=1 count=2 map[2]=1
    i=3 count=3 map[3]=1 count>2 move j, j++ map[1]--=0 count--=2 lenth = i-j=3-1=2
    i=4 count=2 map[2]=2 
    i=5 count=2 map[2]=3
    
    length=i-j=5-1=4
    maxlength=4
    

*/
class Solution {
  public int totalFruit(int[] tree) {
      Map<Integer, Integer> map = new HashMap<>();
      int i=0, j=0, max=-1, count=0;
      
      for(; i<tree.length; i++) {
          if(!map.containsKey(tree[i]) || map.get(tree[i])==0) {
              count++;
          }
          map.put(tree[i], map.getOrDefault(tree[i], 0)+1);
          while(count > 2) {
              map.put(tree[j], map.get(tree[j])-1);
              if(map.get(tree[j]) == 0) {
                  count--;
              }
              j++;
          }
          max = Math.max(max, i-j+1);
      }
      max = Math.max(max, i-j);
      return max;
  }
}





PYTHON version:

class Solution:
    def totalFruit(self, fruits: List[int]) -> int:
        maxlen = 0
        map_d = {}
        right = 0
        left = 0
        while right < len(fruits):
            map_d[fruits[right]] = map_d[fruits[right]]+1 if fruits[right] in map_d else 1
            
            while len(map_d) > 2:
                map_d[fruits[left]] = map_d[fruits[left]]-1
                if map_d[fruits[left]] == 0:
                    del map_d[fruits[left]]
                left=left+1
            
            #update maxlen
            maxlen = max(maxlen, right-left+1)
            right=right+1
            
        return maxlen
        
# same as 159
# [1,2,3,2,2]
# 1: {1: 1} update maxlen
# 2: {1: 1, 2: 1} update maxlen
# 3: {1: 1, 2: 1, 3: 1} left++ {2: 1, 3:1} update maxlen
# 2: {2: 2, 3:1} update maxlen
# 2: {2: 3, 3:1} update maxlen
