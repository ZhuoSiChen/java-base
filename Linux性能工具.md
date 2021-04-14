### **stress** linux 系统压力测试工具
### uptime
```shell script
[root@bj8904 ab]# uptime
 10:27:47 up 70 days, 22:16,  1 user,  load average: 0.00, 0.10, 0.08
```
平均负载是指单位时间内，系统处于可运行状态和不可中断状态的平均进程数，也就是平均活跃进程数，它和 CPU 使用率并没有直接关系。
可运行状态和不可中断状态这俩词儿。
- 可运行状态的进程:是指正在使用 CPU 或者正在等待 CPU 的进程,也就是我们常用 ps 命令看到的，处于 R 状态（Running 或 Runnable）的进程。
- 不可中断状态的进程则是正处于内核态关键流程中的进程，并且这些流程是不可打断的，比如最常见的是等待硬件设备的 I/O 响应，
也就是我们在 ps 命令中看到的 D 状态（Uninterruptible Sleep，也称为 Disk Sleep）的进程。

**平均负载最理想的情况是等于 CPU 个数。所以在评判平均负载时，首先你要知道系统有几个 CPU**

#### 平均负载与 CPU 使用率
CPU 密集型进程，使用大量 CPU 会导致平均负载升高，
此时这两者是一致的；I/O 密集型进程，等待 I/O 也会导致平均负载升高，
但 CPU 使用率不一定很高；大量等待 CPU 的进程调度也会导致平均负载升高，
此时的 CPU 使用率也会比较高。

### **sysstat** linux系统性能工具
- **mpstat** 是一个常用的多核 CPU 性能分析工具，用来实时查看每个 CPU 的性能指标，以及所有 CPU 的平均指标。
- **iostat** 
- **vmstat** 主要用来分析系统的内存使用情况，也常用来分析 CPU 上下文切换和中断的次数。
- **pidstat** pidstat -w(进程切换指标)/-u（cpu使用指标）/-wt(线程上下文切换指标)） 注意看是自愿上下文切换、还是被动上下文切换
#### 测试工具
- stress ： 模拟进程 、 io
- sysbench ： 模拟线程数
```shell script
[root@bj5371 ~]# vmstat 1 
procs -----------memory---------- ---swap-- -----io---- --system-- -----cpu-----
 r  b   swpd   free   buff  cache   si   so    bi    bo   in   cs us sy id wa st
 0  0   4504 28083036 555648 29767244    0    0     0     7    0    0  0  0 99  0  0	
 4  0   4504 28080776 555648 29767736    0    0     0    16 8010 6831  1  0 99  0  0	
 1  0   4504 28079628 555648 29768560    0    0     0     0 5798 6201  1  0 99  0  0	
```

[性能分析文章](https://zhuanlan.zhihu.com/p/74553637)

# Java 性能分析
> 
```shell script
#查看java进程
ps -ef
UID         PID   PPID  C STIME TTY          TIME CMD
root          1      0  0 12:19 ?        00:00:00 /baymax/bin/dumb-init -- /baymax/bin/run.sh
root         50      1  0 12:19 ?        00:00:00 /usr/sbin/sshd
nscd         80      1  0 12:19 ?        00:00:00 /usr/sbin/nscd
root         91      1  0 12:19 ?        00:00:00 /usr/sbin/crond
root        145     50  0 12:19 ?        00:00:00 sshd: root@pts/0
root        147    145  0 12:19 pts/0    00:00:00 -bash
root        200      1  0 12:21 pts/0    00:00:38 java -server -Djava.io.tmpdir=/home/service/app/content-inf
root       3019    147  0 15:26 pts/0    00:00:00 ps -ef

# centos7 install perf
yum install perf
# install perf-map-agent
git clone https://github.com/jvm-profiling-tools/perf-map-agent.git
cd perf-map-agent
# cmake>=2.8.6
yum groupinstall "Development Tools"
yum install make
cmake .
make

```
> 
            
[工具](https://netflixtechblog.com/java-in-flames-e763b3d32166)
[工具使用](http://thoreauz.com/2019/02/16/perf-flameGraph/)
[火焰图解读](http://www.ruanyifeng.com/blog/2017/09/flame-graph.html)

[all in one](https://riboseyim.github.io/2017/10/24/Linux-Perf-Picture/#%E6%89%A9%E5%B1%95%E9%98%85%E8%AF%BB%EF%BC%9A%E7%94%B5%E5%AD%90%E4%B9%A6%E3%80%8ALinux-Perf-Master%E3%80%8B)