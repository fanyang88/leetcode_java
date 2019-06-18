/*
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Twitter twitter = new Twitter();
// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);
// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);
// User 1 follows user 2.
twitter.follow(1, 2);
// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);
// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);
// User 1 unfollows user 2.
twitter.unfollow(1, 2);
// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
*/

/*
  data structure:
    Have a hashmap to store user, key is userId, value is the user itself which include:
        1. uid
        2. followees
        3. the tweets this user posted, it is a linked list with type Tweet<tis, ts, next>
*/

class Twitter {
rivate static int ts=0;
private Map<Integer, User> userMap;
private class Tweet {
  int tid, ts;
  Tweet next;
  Tweet(int tid, int ts) {
      this.ts = ts;
      this.tid = tid;
      next = null;
  }
}
private class User {
  Set<Integer> followees;
  int uid;
  Tweet thead;
  User(int uid) {
      this.uid = uid;
      followees = new HashSet<>();
      followees.add(uid); // add itself
      thead = null;
  }
}

/** Initialize your data structure here. */
public Twitter() {
  userMap = new HashMap<Integer, User>();
}

/** Compose a new tweet. */
public void postTweet(int userId, int tweetId) {
  Tweet t =new Tweet(tweetId, ts++);
  // if userMap has this user
  if(userMap.containsKey(userId)) {
      t.next= userMap.get(userId).thead;
  } else {
      User user = new User(userId);
      userMap.put(userId, user);
  }
  userMap.get(userId).thead = t;
}

/** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
public List<Integer> getNewsFeed(int userId) {
  
  List<Integer> res = new ArrayList<>();
  if(!userMap.containsKey(userId))  return res;
  
  Set<Integer> users = userMap.get(userId).followees;
  PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.ts-a.ts));

  // add all tweets list head into the list
  for(int user: users) {
      Tweet node = userMap.get(user).thead;
      if(node !=null)  q.offer(node);
  }
  
  // now use priority queue to process
  int n=0;
  while(!q.isEmpty() && n<10) {
      Tweet cur = q.poll();
      res.add(cur.tid);
      n++;
      if(cur.next != null) q.offer(cur.next);
  }
  return res;
}
// user 1 post tid=5 user 3 post tid =6 user1 follow user 2, get newsfeed should be [5,6]
// so for follow function, followeeId should add to followerId hashset
/** Follower follows a followee. If the operation is invalid, it should be a no-op. */
public void follow(int followerId, int followeeId) {
  
  // if followeeId not exist, create it
  if(!userMap.containsKey(followeeId)) {
      User user = new User(followeeId);
      userMap.put(followeeId, user);
  }
  // if followerId not exist, create it
  if(!userMap.containsKey(followerId)) {
      User user = new User(followerId);
      userMap.put(followerId, user);
  }
  userMap.get(followerId).followees.add(followeeId);
}

/** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
public void unfollow(int followerId, int followeeId) {
  if(followerId == followeeId || userMap.get(followerId)==null)  return;
  userMap.get(followerId).followees.remove(followeeId);
}
}

/**
* Your Twitter object will be instantiated and called as such:
* Twitter obj = new Twitter();
* obj.postTweet(userId,tweetId);
* List<Integer> param_2 = obj.getNewsFeed(userId);
* obj.follow(followerId,followeeId);
* obj.unfollow(followerId,followeeId);
*/