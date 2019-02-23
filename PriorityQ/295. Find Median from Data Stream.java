/*
Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/

/*
  use two priority Queue
  maxQ add larger number, minQ add smaller number
  e.g: [2,3,1]
  add(2) -> maxQ = {2} minQ = {2}, maxQ = {} since max.size < min.size max={2} min={}
  add(3) -> maxQ = {2,3} min = {} min={2} max= {3} since size equal, do nothing
  add(1) -> maxQ = {3,1} min={2} then min={2,1} max={3} since max<min.size max={2,3} min={1}
  
  when add number, we add to max first
  then we get the smallest from max and put it in min
  however, if max.size < min.size we need to get the largest from min to put in max
  
  max.poll -> return the smallest
  min.poll -> return the largest
  peek() fetch the first element
  max.peek -> return the smallest
  min.peek -> return the largest
*/


class MedianFinder {

  PriorityQueue<Integer> min,  max;
  /** initialize your data structure here. */
  public MedianFinder() {
      min = new PriorityQueue();
      max = new PriorityQueue(1000, Collections.reverseOrder());
  }
  
  public void addNum(int num) {
      max.offer(num);
      min.offer(max.poll()); // put the smallest from max to min
      if(max.size() < min.size()) {
          max.offer(min.poll());
      }
  }
  
  public double findMedian() {
      if(min.size() == max.size()) return (min.peek() + max.peek()) / 2.0;
      return max.peek();
  }
}

/**
* Your MedianFinder object will be instantiated and called as such:
* MedianFinder obj = new MedianFinder();
* obj.addNum(num);
* double param_2 = obj.findMedian();
*/
