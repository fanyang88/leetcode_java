/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 
Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/


/*
   store the timestamps in the array, we store the corresponding counts in the count.
    hit(1) hit(2) hit(3) count[1]=1 [2]=1 [3]=1 
    hit(300) since ts[0]=0!=300 ts[0]=300 count[0]=1 total=4
    getHits(301): 301-ts[0]<300 301-ts[1]=300 not inlcude  301-ts[2]=299<300 include ...
*/
class HitCounter {
  int[] ts,  freq;
  /** Initialize your data structure here. */
  public HitCounter() {
      ts = new int[300];
      freq = new int[300];
  }
  
  /** Record a hit.
      @param timestamp - The current timestamp (in seconds granularity). */
  public void hit(int timestamp) {
      int index = timestamp % 300;
      if(ts[index] != timestamp) {  // an old one exist, override
          ts[index] = timestamp;
          freq[index] =1;
      } else {
          freq[index] ++;
      }
  }
  
  /** Return the number of hits in the past 5 minutes.
      @param timestamp - The current timestamp (in seconds granularity). */
  public int getHits(int timestamp) {
      int count=0;
      for(int i=0; i<300; i++) {
          if(timestamp - ts[i] < 300)  count+= freq[i];
      }
      return count;
  }
}

/**
* Your HitCounter object will be instantiated and called as such:
* HitCounter obj = new HitCounter();
* obj.hit(timestamp);
* int param_2 = obj.getHits(timestamp);
*/