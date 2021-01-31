#### Counter(计数器)
计数器是一个累积量度，代表一个单调递增的计数器，其值只能增加或在重新启动时重置为零。例如，您可以使用计数器来表示已服务请求，已完成任务或错误的数量。
#### Gauge
量规是一种度量标准，代表可以任意上下波动的单个数值。
####  Histogram 直方图
The histogram metric type measures the frequency of value observations that fall into specific predefined buckets.
- 直方图是测量观察值在预定义桶的分配频率
- prometheus 的直方图 多数是用来统计时间与次数的关系的.
- 因为在 prometheus 提供的客户端API中有个 Timer 的时间计数器
例子如下:
适合如下需求.例如我想统计下
某个接口的请求响应时间的分布.
你需要先在 API 定义一个bucket数组里存放一个 double的数字,
然后在根据请求响应时间放在相应的桶里.
```shell
# HELP request_duration Time for HTTP request.
# TYPE request_duration histogram
request_duration_bucket{le="0.005",} 0.0
request_duration_bucket{le="0.01",} 0.0
request_duration_bucket{le="0.025",} 0.0
request_duration_bucket{le="0.05",} 0.0
request_duration_bucket{le="0.075",} 0.0
request_duration_bucket{le="0.1",} 0.0
request_duration_bucket{le="0.25",} 0.0
request_duration_bucket{le="0.5",} 0.0
request_duration_bucket{le="0.75",} 0.0
request_duration_bucket{le="1.0",} 0.0
request_duration_bucket{le="2.5",} 0.0
request_duration_bucket{le="5.0",} 1.0
request_duration_bucket{le="7.5",} 1.0
request_duration_bucket{le="10.0",} 3.0
request_duration_bucket{le="+Inf",} 3.0
request_duration_count 3.0
request_duration_sum 22.978489699999997
```
 1s,2s,3s,4s,5s
- 请求 成功率  次数
#### Summaries 摘要
摘要和直方图有很多相似之处。总结在直方图之前，建议尽可能使用直方图。值得注意的是直方图和摘要之间的主要区别：
- 使用直方图，可在Prometheus服务器上计算分位数。使用摘要，它们在应用程序服务器上进行计算。
- 因此，摘要数据无法从多个应用程序实例中汇总
- 直方图需要预先定义存储桶，因此适合您对价值分布有个好主意的用例
- 如果需要计算精确的分位数，摘要是一个不错的选择，但不能确定取值范围是多少
例如：统计某个接口的请求时间的占比
```
# HELP request_duration_summary Time for HTTP request.
# TYPE request_duration_summary summary
request_duration_summary{quantile="0.95",} 7.4632192
request_duration_summary_count 5.0
request_duration_summary_sum 27.338737899999998
```
[prometheus 4 种类型的用途](https://tomgregory.com/the-four-types-of-prometheus-metrics/#3_Histograms)
