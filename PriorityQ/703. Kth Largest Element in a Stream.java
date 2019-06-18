/*
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4  [2,3,4,5,8]
kthLargest.add(5);   // returns 5  [2,3,4,5,5,8]
kthLargest.add(10);  // returns 5  [2,3,4,5,5,8, 10]
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.
*/

//Keep track of the k biggest elements in the minimum priority queue q. q.peek() is the answer.
class KthLargest {
  int k;
  Queue<Integer> pq;
  public KthLargest(int k, int[] nums) {
      this.k = k;
      pq = new PriorityQueue<>(k); // default is sort from largest to small, poll gets the smallest
      for(int num: nums) {
          add(num);
      }
  }
  
  public int add(int val) {
      if(pq.size() < k) {
          pq.offer(val);
      } else if(pq.peek() < val) {
          pq.poll(); // pop out the small one
          pq.offer(val);
      } else {
          // when pq size is k and pq smallest is still > new val, do nothing
      }
      
      return pq.peek();
  }
}

/**
* Your KthLargest object will be instantiated and called as such:
* KthLargest obj = new KthLargest(k, nums);
* int param_1 = obj.add(val);
*/
