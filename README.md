#### HashMap
##### 类变量
- `static final int TREEIFY_THRESHOLD = 8`
    链表长度超过 8 的时候会转成树
- `int UNTREEIFY_THRESHOLD = 6` 
    树大小小于6的时候转化为链表
- `int MIN_TREEIFY_CAPACITY = 64`
    整个hashmap最小必须要树化的容量
- `DEFAULT_INITIAL_CAPACITY = 1 << 4`
    默认的数组大小
- `DEFAULT_LOAD_FACTOR = 0.75f;`
    默认的负载因子 0.75 
##### 成员变量
-  `threshold` 
    如果大于阈值的话会重新hash
    //更新
    > resize() {
    > newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    > }
    > if (++size > threshold)
    >           resize();

##### Public operations
- 构造函数
`public HashMap(int initialCapacity, float loadFactor)`
  - 问题1 为什么需要判断 initialCapacity > MAXIMUM_CAPACITY
    > 原因 Integer的范围是 -2^31 到 2^31-1 第一位为符号位 0 占用了一位.
    > 所以 MAXIMUM_CAPACITY = 1 << 30 
  - 问题2 为什么初始化的大小不能为其他数呢? 2^31-1 到 2^30之间的数呢？
    >
- public V put(K key, V value) 
  - final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) 
    > 
    > ```java 
    > //1. 看 `table` 是否为null 初始化 `table` 大小 
    > if ((tab = table) == null || (n = tab.length) == 0)
    >           n = (tab = resize()).length;
    > //2. 查看对应槽位是否有值,直接设置进去
    >  if ((p = tab[i = (n - 1) & hash]) == null)
    >             tab[i] = newNode(hash, key, value, null);
    > //3. 比较
    > else {
    >   HashMap.Node<K,V> e; K k;
    >   // 槽位相同且key与之前的相等,直接修改应用
    >   if (p.hash == hash &&
    >       ((k = p.key) == key || (key != null && key.equals(k))))
    >       e = p;
    >   // 这个槽位对应的是个树
    >   else if (p instanceof TreeNode)
    >       e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
    >   else {
    >   // 否则是链表
    >   // 遍历链表
    >       for (int binCount = 0; ; ++binCount) {
    >            if ((e = p.next) == null) {
    >                p.next = newNode(hash, key, value, null);
    >                  // 转成树的阈值 默认为 8 
    >                if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
    >                    treeifyBin(tab, hash);
    >                break;
    >            }
    >            if (e.hash == hash &&
    >                ((k = e.key) == key || (key != null && key.equals(k))))
    >                break;
    >            p = e;
    >        }
    >   }
    > 
    > ```
##### Static utilities

- static final int hash(Object key) {
          int h;
          return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
      } 为什么 要左移16位呢?
    > 因为 在速度,实用性和比特位之间分布需要考虑,因为在比特位分布上得不到好处,还有hashmap应用了树结构解决了hash冲突,还有因为表结构的限制 高位得不到计算,所以应用了高位的比特位 左移然后异或方式来处理key,
- 当 hashmap 的长度大于 16 了呢?
    **`java.lang.Object.hashCode`**的方法注释是
    hashcode 方法说白了就是提供对象 到 int 的映射 省去一个个比较对象里面的属性.
  This method is supported for the benefit of hash tables such as those provided by {@link java.util.HashMap}
  这个方法的作用是为了给hash tables 提供便利的.例如 hashMap 提供的 hash 表
  hashCode 
  此方法 一般的约定有以下:
   - 在整个java程序中,在不改变对象的前提下 equals 调用多少次都是一样的
   - 如果 此对象调用了 equals 返回的结果是 true的话,他们的hashcode方法返回的int必须相同
   - 如果 此对象调用的 equals 返回的结果是 false的话,他们的hashcode返回的int必须不相同
   
##### hashmap 总结:
   - hashmap 是什么,是一个 k,v的容器,通过一个 tab 数组 去存放 kv 的键值对,可以通过键去找到对应的值
   - 1.8 之后的 hashMap

#### AQS 抽象队列同步器
   - 在第一个线程运行到 ReentrantLock 对象的 lock 方法时, 会用原子操作把 AQS的 state 设为 1 表示这个AQS被占用,然后把会把当前线程写入到这个抽象队列同步器的一个成员变量`setExclusiveOwnerThread(Thread.currentThread());`为了可重入与解锁的时候
   - 把运行的线程用node串起来
   - 公平与不公平的区别.
   - 线程如何进队
#### 缓存更新的套路
    cache aside patten
        读先读缓存,如果没有,读数据库,读到写入缓存
        更新在更新数据库,然后再让缓存失效
        有问题,因为读的操作比写的操作快所以没问题
    read through patten
        由缓存来读
    write through patten
        由缓存来写
    write behind the cache patten
        异步写
#### 数据库
   - 事务的实现
        事务数组分三段已提交,未提交,未开始事务,打快照,
   - 读提交与可重复读的区别
   - 

