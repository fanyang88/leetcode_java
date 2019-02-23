/*
Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();
*/

/*
  straight forward thingking: have a set has all numbers
  randomly pick a number from set and put int arr[0], remove that number from set, rest is n-2
  randomly pick a number from set and put int arr[1], remove that number from set, rest is n-1
  ....
  
  randomly pick a number from set and put int arr[n-1], remove that number from set, rest is 0
  
  which is similar as arr[0] stay the same
  arr[1] swap with either arr[0] or stay the same
  arr[2] swap wither either arr[0] or arr[1] or stay the same
  ....
  
*/

class Solution {
  int[] nums;
  public Solution(int[] nums) {
      this.nums = nums;
  }
  
  /** Resets the array to its original configuration and return it. */
  public int[] reset() {
      return nums;
  }
  
  /** Returns a random shuffling of the array. */
  public int[] shuffle() {
      int[] temp = nums.clone();
      for(int i=0; i<nums.length; i++) {
          int rand = (int)(Math.random() * (i+1)); // not include i+1
          swap(rand, i, temp);
      }
      return temp;
  }
  
  public void swap(int i, int j, int[] nums) {
      int t= nums[i];
      nums[i] = nums[j];
      nums[j] = t;
  }
}

/**
* Your Solution object will be instantiated and called as such:
* Solution obj = new Solution(nums);
* int[] param_1 = obj.reset();
* int[] param_2 = obj.shuffle();
*/
