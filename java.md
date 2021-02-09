### 

### JVM类加载

### JVM运行时分区

### 垃圾回收算法

### 垃圾回收器
CMS:
G1:
ZGC: 
差别比较

AQS相关.
公平与非公平.

#### AQS 抽象队列同步器
   - 在第一个线程运行到 ReentrantLock 对象的 lock 方法时, 会用原子操作把 AQS的 state 设为 1 表示这个AQS被占用,然后把会把当前线程写入到这个抽象队列同步器的一个成员变量`setExclusiveOwnerThread(Thread.currentThread());`为了可重入与解锁的时候
   - 把运行的线程用node串起来
   - 公平与不公平的区别.
   - 线程如何进队

