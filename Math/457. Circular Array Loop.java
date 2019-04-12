/*
You are given a circular array nums of positive and negative integers. If a number k at an index is positive, then move forward k steps. Conversely, if it's negative (-k), move backward k steps. Since the array is circular, you may assume that the last element's next element is the first element, and the first element's previous element is the last element.

Determine if there is a loop (or a cycle) in nums. A cycle must start and end at the same index and the cycle's length > 1. Furthermore, movements in a cycle must all follow a single direction. In other words, a cycle must not consist of both forward and backward movements.

 

Example 1:

Input: [2,-1,1,2,2]
Output: true
Explanation: There is a cycle, from index 0 -> 2 -> 3 -> 0. The cycle's length is 3.
Example 2:

Input: [-1,2]
Output: false
Explanation: The movement from index 1 -> 1 -> 1 ... is not a cycle, because the cycle's length is 1. By definition the cycle's length must be greater than 1.
Example 3:

Input: [-2,1,-1,-2,-2]
Output: false
Explanation: The movement from index 1 -> 2 -> 1 -> ... is not a cycle, because movement from index 1 -> 2 is a forward movement, but movement from index 2 -> 1 is a backward movement. All movements in a cycle must follow a single direction.
 

Note:

-1000 ≤ nums[i] ≤ 1000
nums[i] ≠ 0
1 ≤ nums.length ≤ 5000
 

Follow up:

Could you solve it in O(n) time complexity and O(1) extra space complexity?
*/

/*
 start from each number, if nums[i] = 0 skip, since it can't go forward or backward.
 slow = fast = (nums[i] + i + n) % n, fast go 2 steps, slow go 1 step
 if slow = fast, there is a circle
 for elements has visited in each loop, we can set it to 0, no need to check them again, since they are in the same loop don't lead to circle
 
 所谓的循环必须是一个方向的就是说不能跳到一个数，再反方向跳回来，这不算一个loop。
 比如[1, -1]就不是一个loop，而[1, 1]是一个正确的loop。
 
 由于从一个位置只能跳到一个别的位置，而不是像图那样一个点可以到多个位置，所以这里我们就可以根据坐标建立一对一的映射，一旦某个达到的坐标已经有映射了，
 说明环存在，当然我们还需要进行一系列条件判断。首先我们需要一个visited数组，来记录访问过的数字，然后我们遍历原数组，如果当前数字已经访问过了，直接跳过，
 否则就以当前位置坐标为起始点开始查找，进行while循环，计算下一个位置，计算方法是当前位置坐标加上对应的数字，由于是循环数组，所以结果可能会超出数组的长度，所以我们要对数组长度取余。当然上面的数字也可能是负数，加完以后可能也是负数，所以在取余之前还得再补上一个n，使其变为正数。
 此时我们判断，如果next和cur相等，说明此时是一个数字的循环，不符合题意，再有就是检查二者的方向，数字是正数表示forward，若是负数表示backward，在一个loop中必须同正或同负，我们只要让二者相乘，如果结果是负数的话，说明方向不同，直接break掉。此时如果next已经有映射了，
 说明我们找到了合法的loop，返回true，否则建立一个这样的映射，将next位置在visited数组中标记true，继续循环，参见代码如下：
 e.g: [-8, -9, -10, 1, 2]
 if cur=0 next -> -10= index=2 (-8)%5=-3 ->-3+5=2 ->2%5=2
*/

class Solution {
  public boolean circularArrayLoop(int[] nums) {
      int n = nums.length;
      boolean[] visited = new boolean[n];
      for(int i=0; i<n; i++){
          if(visited[i]) continue;
          visited[i] = true;
          int cur = i;
          Set<Integer> set = new HashSet<>();
          while(true) {
              // This is the key
              int next=  (cur + nums[cur] >= 0)? (cur + nums[cur]) % n: (n + ((cur + nums[cur]) % n))%n;
              //cur ==next 说明此时是一个数字的循环，不符合题意
              // or directions are not the same, break
              if(cur == next || nums[cur] *nums[next] < 0) break; 
              if(set.contains(next)) return true; // visited, there is a loop
              set.add(cur);
              
              visited[next] = true;
              cur = next;
          }
      }
      return false;
  }
}
