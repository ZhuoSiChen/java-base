### netty的好处
- 它的基于 Java NIO 的异步的和事件驱动的实现,保证了高负载下应用程序
性能的最大化和可伸缩性
- Netty 也包含了一组设计模式,将应用程序逻辑从网络层解耦,
  简化了开发过程,同时也最大限度地提高了可测试性、模块化以及代码的可重用性。
  
#### Netty 抽象
- Channel — Socket ;
- EventLoop —控制流、多线程处理、并发;
- ChannelFuture —异步通知。

##### ChannelPipeline 是什么 ?
    - ChannelPipeline  提供了 ChannelHandler 链的容器
##### ChannelHandler 是什么 ?
  
 > ChannelHandler 安装到 ChannelPipeline 中的过程如下所示:
  一个 ChannelInitializer 的实现被注册到了 ServerBootstrap 中 1 ;
  当 ChannelInitializer.initChannel() 方法被调用时, ChannelInitializer
  将在 ChannelPipeline 中安装一组自定义的 ChannelHandler ;
  ChannelInitializer
  将它自己从 ChannelPipeline 中移除。
#####什么是边缘触发 
- JDK 的实现是水平触发,而 Netty 的(默认的)是边沿触发。有关的详细信息参见 epoll 在维基百科上的
解释:http://en.wikipedia.org/wiki/Epoll - Triggering_modes。