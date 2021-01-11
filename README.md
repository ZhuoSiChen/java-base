#### HashMap
- 构造函数
`public HashMap(int initialCapacity, float loadFactor)`
  - 问题1 为什么需要判断 initialCapacity > MAXIMUM_CAPACITY
    > 原因 Integer的范围是 -2^31 到 2^31-1 第一位为符号位 0 占用了一位.
    > 所以 MAXIMUM_CAPACITY = 1 << 30 
  - 问题2 为什么初始化的大小不能为其他数呢? 2^31-1 到 2^30之间的数呢？
    >
- public V put(K key, V value) 
  - final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) 
- HashMapSpliterator 这个用来干什么的呢?
- static final int hash(Object key) {
          int h;
          return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
      } 为什么 要左移16位呢?
    > 因为 在速度，实用性和比特位之间分布需要考虑,因为在比特位分布上得不到好处,还有hashmap应用了树结构解决了hash冲突,还有因为表结构的限制 高位得不到计算,所以应用了左移加异或的方式来处理key的hash