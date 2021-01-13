```shell
top - 14:27:07 up 761 days, 19:51,  1 user,  load average: 0.69, 0.65, 0.62
Tasks: 741 total,   2 running, 738 sleeping,   0 stopped,   1 zombie
Cpu(s):  0.9%us,  2.6%sy,  0.0%ni, 96.5%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:  65632616k total, 64613988k used,  1018628k free,   604056k buffers
Swap:  8388604k total,     7340k used,  8381264k free, 55097300k cached
```
```
第一行
简单来说，平均负载是指单位时间内，系统处于可运行状态和不可中断状态的平均进程数，也就是平均活
跃进程数，它和 CPU 使用率并没有直接关系。这里我先解释下，可运行状态和不可中断状态这俩词儿。

不可中断状态的进程则是正处于内核态关键流程中的进程，并且这些流程是不可打断的，比如最常见的是
等待硬件设备的 I/O 响应，也就是我们在 ps 命令中看到的 D 状态（Uninterruptible Sleep，
也称为 Disk Sleep）的进程。 
```