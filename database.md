## 数据库知识整理

#### 数据库 的数据结构

#### SQL select 语句的执行方式?

#### SQL 的 join 实现?

#### SQL 优化方式?

#### SQL 事务的实现?

#### SQL MVCC是什么?

#### SQL innodb 引擎与 Myisam 引擎的区别

#### SQL 索引类型

#### SQL 事务的隔离级别

#### SQL 有哪些锁? 分别都用来控制什么?

#### SQL 视图用来干什么?

#### SQL explain 主要的字段都有什么值?

#### 2PL two phase lock
> Expanding phase (aka Growing phase): locks are acquired and no locks are released (the number of locks can only increase).
Shrinking phase (aka Contracting phase): locks are released and no locks are acquired.
第一阶段是.描述锁扩从：也就是一旦获取了锁 就不能释放锁,锁的数量也就只能一直增加
第二阶段是.描述锁收缩：也就是一旦释放了锁就不能 获取锁

[WIKI 2PL](https://en.wikipedia.org/wiki/Two-phase_locking)

2PL是为了保证多个事务能正确运行的
