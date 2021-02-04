Redis remote dictionary service(远程字典表服务)
一个远程的缓存
缓存都是易失的
缓存的持久化方式
RDB: 基于时间与手动快照
AOF: 基于命令 append only file
缓存都有时间
过期策略有：
1. 定时删除
2. 惰性删除
3. 定期删除
Redis的过期删除策略就是：惰性删除和定期删除两种策略配合使用。
缓存过期时间
缓存都有大小限制
淘汰策略
不过期：no eviction
all keys lru
all key random
volatile key lru 
volatile lru
volatile ttl 

Redis 问题?
缓存过期策略
缓存淘汰策略
缓存雪崩
缓存穿透
缓存击穿

Redis集群方式?