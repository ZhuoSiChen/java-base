## 数据库知识整理

#### 数据库 的数据结构


#### 数据库
   - 事务的实现
        每个事务开始时,MySQL会全局分配一个 transaction id.
        每行的
        事务数组分三段已提交,未提交,未开始事务,
        开始事务时,数据库会创建一个视图.
        然后给每个事务打个快照,在事务中读取到的数据或者使用到的数据都有这个 row_tx_id
        这样就保证了,在事务中的视图是一致的.
   - 读提交与可重复读的区别
   
#### SQL select 语句的执行方式?

#### SQL 的 join 实现?

(join实现)[https://zhuanlan.zhihu.com/p/54275505]

#### SQL 优化方式?

#### SQL 事务的实现? and 什么是事务? and 为什么需要事务?

#### SQL 有哪些锁? 分别都用来控制什么?
X锁：用来控制写入的
和
S锁：用来控制读取的
在这之上还有区间锁.是innodb用来解决幻读问题的.


#### mysql中的锁算法?
> innodb存储引擎有三种行锁算法,分别是
> Record Lock：单个行记录上的锁
> Gap Lock：间隙锁 锁定一个范围，但不包含记录本身
> 保证 在可重复读下 前后两次查询一致
> Next-key lock：Gap Lock + Record Lock 锁定一个范围 并且锁定记录本身

>[锁相关](https://www.zhihu.com/column/c_1104074839660294144)

#### SQL MVCC是什么? 为什么需要MVCC? MVCC是如何工作的? 
>是用来并发控制的,因为mysql只要两种锁,
当 mysql 存在多个并发控制的事务时,通过给对应行记录打上快照.
也就是 transation Id,来控制多个事务是回滚还是提交.
而不用,等待锁的释放.

> [MVCC](https://zhuanlan.zhihu.com/p/52977862)

#### SQL innodb 引擎与 Myisam 引擎的区别
InnoDB支持事务,行锁
MyIsam不支持事务,表锁

#### SQL 索引类型? 区别和联系?
|      |  数据结构 |类型|
| ---- | ---- | ----      |
| 主键索引|B+树|  聚簇      |
| 唯一索引|B+树|  非聚簇    |
| 普通索引|B+树|  非聚簇    |


#### SQL 事务的隔离级别



#### SQL 视图用来干什么?

#### SQL explain 主要的字段都有什么值?

#### 2PL two phase lock
> Expanding phase (aka Growing phase): locks are acquired and no locks are released (the number of locks can only increase).
Shrinking phase (aka Contracting phase): locks are released and no locks are acquired.
第一阶段是.描述锁扩从：也就是一旦获取了锁 就不能释放锁,锁的数量也就只能一直增加
第二阶段是.描述锁收缩：也就是一旦释放了锁就不能 获取锁

[WIKI 2PL](https://en.wikipedia.org/wiki/Two-phase_locking)

2PL是为了保证多个事务能正确运行的

#### 5.6 版本的索引下推.
假设有一张people表,包含字段name、address、first_name

索引为(name,address,first_name)

```shell
SELECT * FROM person WHERE `name` = "1" AND `address` LIKE "%222" and 
```
简单的来说就是. 在索引上判断 where 条件

查看执行计划时发现extra一栏中有Using index condition信息，说明使用了索引下推。

https://juejin.cn/post/6844904017332535304


https://github.com/ChiangShin/call-me-crud-boy/blob/3952d56c18b1769b63e72fe83f75b139d5af8a2d/%E9%9D%A2%E8%AF%95%E7%AC%94%E8%AE%B0/mysql.md