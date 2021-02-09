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