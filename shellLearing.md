### 统计请求耗时
```sh
#!/bin/sh
rm -f aa.txt
rm -f bb.txt
for i in {1..100} ; do
    res=`{ time curl -X POST http://127.0.0.1:9081/api/v1/check/text  -H 'content-type: application/json' -d '{
"appId":"voice_assistant",
"data":"毛泽东吃屎",
"accessKey":"DIpowkJMwCw8",
"additionalInfo": {
"processEngine": "string",
"useEngine": 0
}}';} 2>> aa.txt`
done
cat aa.txt | grep real |awk  '{print substr($2,6,2)}'|awk '{sum+=$1} END {print "Average = ", sum/NR}'
for i in {1..100} ; do
    res=`{ time curl -X POST http://127.0.0.1:9081/api/v1/check/text  -H 'content-type: application/json' -d '{
      "appId":"voice_assistant",
      "data":"你好呀",
      "accessKey":"DIpowkJMwCw8",
      "additionalInfo": {
      "processEngine": "string",
      "useEngine": 0
      }}';} 2>> bb.txt`
done
cat bb.txt | grep real |awk  '{print substr($2,6,2)}'|awk '{sum+=$1} END {print "Average = ", sum/NR}'
```

#### 查找多个文件是否包含有 req
```shell
grep -l req log/*
```

[awk](https://lvs071103.gitbooks.io/awk/content/awk_syntax_and_basic_commands/ofs-.html)

## if [ “x${var}" == “x” ]整句的意思是判断${var}是否为空。
从kafka启动文件中看到的
```shell script
#!/bin/bash
if [ "x$KAFKA_LOG4J_OPTS" = "x" ]; then
    export KAFKA_LOG4J_OPTS="-Dlog4j.configuration=file:$base_dir/../config/log4j.properties"
fi
```

> = 与 == 在 [ ] 中表示判断(字符串比较)时是等价的
> 在 (( )) 中 = 表示赋值， == 表示判断(整数比较)，它们不等价