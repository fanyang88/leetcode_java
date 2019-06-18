/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

/*
   1,10,3,,5,6
   1: count=1 mod=0 % 3=0 sum-0+1=1 arr[0]=1
   10:count=2 mod=1 % 3=1 sum-0+10=11 arr[1]=10 sum/count=next
   3: count=3 mod=2 % 3=2 sum-0+3=14  arr[2]=3 sum/count=next
   5: count=4 mod=3 % 3=0 sum-arr[0]+val=18  arr[0]=5 sum/count=next
   6: count=5 mod=4 % 3=1 sum-arr[1]+val=14  arr[1]=6 sum/count=next
   

*/

class MovingAverage {
  int[] arr;
  int sum, count;
  public MovingAverage(int size) {
      sum=0;
      arr = new int[size];
      count=0;
  }
  
  public double next(int val) {
      int size = arr.length;
      count+=1;
      int mod =  (count-1) % size;
      sum=sum-arr[mod] + val;
      arr[mod] = val;
      return (double)sum/(count>size ? size : count);
  }
}

/**
* Your MovingAverage object will be instantiated and called as such:
* MovingAverage obj = new MovingAverage(size);
* double param_1 = obj.next(val);
*/
