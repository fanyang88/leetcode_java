/**
 Given an array nums of n integers where n > 1, return an array output such that output[i] is equal to the product of 
 all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).
 */



/*
        [1,2,3,4]
        [1,     1*1, 1*2, 2*3]
        [2*3*4, 3*4, 1*4,  1]
        
*/
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n], right= new int[n], res= new int[n];
        left[0] = 1;
        right[n-1]=1;
        for(int i=1; i<n; i++) {
            left[i]= left[i-1] * nums[i-1];
        }
        for(int i=n-2; i>=0; i--) {
            right[i]= right[i+1] * nums[i+1];
        }
        for(int i=0; i<n; i++) {
            res[i]= left[i] * right[i];
        }
        return res;
    }
}