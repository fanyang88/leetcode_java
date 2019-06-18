/*
Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
*/

/*
    e.g:  [-1, 3, 2, 0]   i<j<k   ai<aK<aj from right to left
    st=[0]  since 2>0 0 would be potential s3, once we can find a value > potential s3, return true
    st[2] since 3>2 2 would be potential s3  
    0, since 0<s3, we found a sequence, return true
    
    [3,1,4,2]
    st=[2] 
    4: since 4>2 set s3=2 pop st, push 4 to st, st=[4]
    1: since 1<2(s3) return t
    
    Suppose we want to find a 123 sequence with s1 < s2 < s3, we just need to find s3, followed by s2 and s1. Now if we want to find a 132 sequence with s1 < s3 < s2, we need to switch up the order of searching. we want to first find s2, followed by s3, then s1.

DETECTION: More precisely, we keep track of highest value of s3 for each valid (s2 > s3) pair while searching for a valid s1 candidate to the left. Once we encounter any number on the left that is smaller than the largest s3 we have seen so far, we know we found a valid sequence, since s1 < s3 implies s1 < s2.

CORRECTNESS: As we scan from right to left, we can easily keep track of the largest s3 value of all (s2,s3) candidates encountered so far. Hence, each time we compare nums[i] with the largest candidate for s3 within the interval nums[i+1]...nums[n-1] we are effectively asking the question: Is there any 132 sequence with s1 = nums[i]? Therefore, if the function returns false, there must be no 132 sequence.

IMPLEMENTATION:

Have a stack, each time we store a new number, we first pop out all numbers that are smaller than that number. The numbers that are popped out becomes candidate for s3.
We keep track of the maximum of such s3 (which is always the most recently popped number from the stack).
Once we encounter any number smaller than s3, we know we found a valid sequence since s1 < s3 implies s1 < s2.

    
*/

class Solution {
  public boolean find132pattern(int[] nums) {
      Stack<Integer> st= new Stack<>();
      int potential = Integer.MIN_VALUE;
      for(int i=nums.length-1; i>=0; i--) {
          if(nums[i] < potential && potential != Integer.MIN_VALUE)  return true;
          while(!st.isEmpty() && nums[i] > st.peek()) {
            potential = st.pop();
          }
          st.push(nums[i]);
      }
      return false;
  }
}
