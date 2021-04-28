### 

### JVM类加载

### JVM运行时分区

### 垃圾回收算法

### 垃圾回收器
#### CMS
> 新生代  阶段 


> 老年代 阶段 

#### G1
> 新生代  阶段 

> 老年代  阶段 

#### ZGC
> 新生代 阶段 

> 老年代 阶段 
差别比较

AQS相关.
公平与非公平.

#### AQS 抽象队列同步器
   - 在第一个线程运行到 ReentrantLock 对象的 lock 方法时, 会用原子操作把 AQS的 state 设为 1 表示这个AQS被占用,然后把会把当前线程写入到这个抽象队列同步器的一个成员变量`setExclusiveOwnerThread(Thread.currentThread());`为了可重入与解锁的时候
   - 把运行的线程用node串起来
   - 公平与不公平的区别.
   - 线程如何进队
#### CountDownLatch 与 CyclicBarrier
CountDownLatch : 等待与释放的可以在不同的线程上进行. 
CyclicBarrier : 是一个屏障,等待 **所有线程** 到达了这个屏障后,线程才能执行.
