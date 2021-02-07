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

#### 5.6 版本的索引下推.
假设有一张people表,包含字段name、address、first_name

索引为(name,address,first_name)

```shell
SELECT * FROM person WHERE `name` = "1" AND `address` LIKE "%222" and 
```
简单的来说就是. 在索引上判断 where 条件

查看执行计划时发现extra一栏中有Using index condition信息，说明使用了索引下推。

https://juejin.cn/post/6844904017332535304
