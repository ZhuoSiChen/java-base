# 什么是反应式编程
我们熟知的有
1. 命令式编程
2. 函数式编程
3. 面向对象编程
跟所有的编程一样都是一种范式 
[^范式]: 如何构建程序的结构和元素称之为范式
> 反应式编程（Reactive Programming）是一种
> 面向数据流和变化传播的编程范式
> 这意味着可以通过所使用的编程语言轻松
> 地表达静态(如数组)或动态(如事件发射器)数据流。
> — https://en.wikipedia.org/wiki/Reactive_programming 

### reactor 3.x 

> Reactor 是第4代反应式库,给予 `响应式规范`,为了创建没有阻塞的jvm应用程序
> Reactor is a fourth-generation reactive library, based on the `Reactive Streams specification`, for building non-blocking applications on the JVM

### 为什么需要反应式编程
1.上下游速度不匹配呀.
例如: 服务A的处理速度是 每秒 5 个请求. 服务 B 的处理速度 每秒 1 个请求.
如果 A -> B  -> 表示调用依赖
这个时候就需要 限制 A 的速度了.
如果 B -> A 这样就不需要

### 反应式规范 只规定了 4 个接口,如何让他们工作的呢?
#### org.reactivestreams:reactive-streams:1.0.3

```java
public interface Processor<T, R> extends Subscriber<T>, Publisher<R> {
}
public interface Subscriber<T> {
     void onSubscribe(Subscription s);
     void onNext(T t);
     void onError(Throwable t);
     void onComplete();
}
public interface Subscription {
     void request(long n);
     void cancel();
}
public interface Publisher<T> {
     void subscribe(Subscriber<? super T> s);
}
```
**就这4个接口,就把程序构建了.太厉害了吧!(天才)**

```java
Flux<String> just = Flux.just("tom", "jack", "allen");
		just.map(s-> s.concat("@qq.com"))
		    .subscribe(System.out::println);
```

> 分为 4 个阶段
>  assemble 组装阶段
>  subscribe 订阅阶段
>  onSubscribe 触发订阅阶段
>  request 请求阶段



# webflux与传统的req 与 resp模式的区别
1. req 与 resp的在同一个线程上。当线程数量达到一定数量后，会出现失败的情况。
而webflux则不会。
因为内部分离了读写的逻辑，
避免了阻塞。

[大厂实践](https://zhuanlan.zhihu.com/p/152325857)
[5 Things to Know About Reactive Programming](https://developers.redhat.com/blog/2017/06/30/5-things-to-know-about-reactive-programming/)
[响应式规范](https://www.reactive-streams.org)