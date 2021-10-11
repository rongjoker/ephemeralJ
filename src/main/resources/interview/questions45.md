##经典问题45个

### 1 MySQL如何实现事务（MVCC等、幻读）


### 2 MySQL开发过程中的优化技巧（MRR、删除数据的方式）
1. 利用判断数据是否是数字，来将参数拆分城多个查询参数，避免or查询
2. 将验证的代码块放放在事务外面，减少事务锁表的时间和范围
3. in 查询用exists优化
4. 字段默认是“”来避免null操作(索引不会包含有NULL值的列,只要列中包含有NULL值都将不会被包含在索引中)
5. 用rename表来清空表数据，而不是delete from 或者 truncate(对于AUTO_INCREMENT的列来说，当在truncate之后，改列的第一个值仍从1开始，但是delete不释放空间，仍然顺序使用删除之前的值；delete属于DML，truncate是DDL语言， 操作立即生效，自动提交，原数据不放到rollback segment中，不能回滚)
6. total 查询不精确，类似 es total 10000以上不准确
7. order by field() 函数使用 :
```
ORDER BY FIELD(status,
   'In Process',
   'On Hold',
   'Cancelled',
   'Resolved',
   'Disputed',
   'Shipped');
```

### binlog为什么还需要redo log


### 通过binlog同步数据到es
[使用Kafka订阅数据库的实时Binlog](https://cloud.tencent.com/developer/article/1748294) <br>
类似方案有  Alibaba Cannal, Zendesk Maxwell, Yelp mysql_streamer，不会影响主进程，但是会有不定时长的延迟<br>
1. PartitionSubListener 监听 bin log的kafka分片:
```
public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
for (TopicPartition p : partitions) {
log.info("add TopicPartition:{}", p);
TopicPartitionMsgConsumer.getTopicPartitionMsgConsumerMap()
.computeIfAbsent(p, k -> new TopicPartitionMsgConsumer(p, binlogSyncHandlerMap));
}
}

```


核心0:消费日志  TopicPartitionMsgConsumer#consume:

```

SubscribeDataProto.Entries entries = SubscribeDataProto.Entries.parseFrom(completeMsg.toByteArray());
// 此处消费信息
for (SubscribeDataProto.Entry entry : entries.getItemsList()) {
//获取event类型
if(!entry.getEvent().hasDmlEvent()){
continue;
}
//获取数据库
String binlogSchema = entry.getHeader().getSchemaName().toLowerCase();
//获取表名
String binlogTableName = entry.getHeader().getTableName().toLowerCase();
BinlogSyncHandler<?> binlogSyncHandler = binlogSyncHandlerMap.get(Pair.of(binlogSchema, binlogTableName));

    if(Objects.isNull(binlogSyncHandler)){
        log.info("skip data {}.{}",binlogSchema,binlogTableName);
        continue;
    }
    LocalDateTime happenAt = LocalDateTime
        .ofInstant(Instant.ofEpochSecond(entry.getHeader().getTimestamp()), TimeZone.getDefault().toZoneId());
    List tableData = BinlogHelper.getTableData(entry, binlogSyncHandler.getEntityType());
    LocalDateTime now = LocalDateTime.now();
    log.info("data happen at:{}, receive at:{}, latency:{}, content is {}",
        happenAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
        now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME), Duration.between(happenAt, now).toNanos(), JSON
            .toJSONString(tableData));
    binlogSyncHandler.handle(entry.getEvent().getDmlEvent().getDmlEventType(), tableData);

}

// commit at checkpoint message
if (entries.getItemsCount() > 0
&& entries.getItems(0).getHeader().getMessageType() == SubscribeDataProto.MessageType.CHECKPOINT) {

    consumer.commitAsync(Collections.singletonMap(tp, new OffsetAndMetadata(consumerRecord.offset() + 1)), null);
}

```


核心1: 获取日志 VendorBinlogSyncHandler:
```
@Override
public void handle(DMLType dmlType, List<VendorDsl> dataList) {
if (CollectionUtils.isEmpty(dataList)) {
return;
}
// 增删改
log.info("handle dmlType={}, dataList={}", dmlType, JsonUtil.toJson(dataList));
Map<String, VendorDsl> dataFromDb = dataList.stream().map(data -> {
Optional<VendorDetailVO> optional = Optional.of(vendorService.getVendorById(data.getVendorId()));
if (optional.isPresent()) {
return generalConverter.toVendorDsl(optional.get());
} else {
return data;
}
}
).collect(Collectors.toMap(k -> k.getVendorId().toString(), v -> v, (a, b) -> a));
vendorService.writeVendorToEsData(dataFromDb);
}

```


核心2: 写入es VendorServiceImpl#writeVendorToEsData -> ElasticSearchServiceImpl#createBatch

```
public <T> BulkResponse createBatch(String idxName, Map<String, T> dataMap) {
if (MapUtils.isEmpty(dataMap)) {
BulkItemResponse[] responses = new BulkItemResponse[0];
return new BulkResponse(responses, 1);
}
//创建BulkRequest
BulkRequest bulkRequest = new BulkRequest();
dataMap.forEach((id, data) -> {
IndexRequest indexRequest = new IndexRequest(idxName)
.id(id)
.source(JsonUtil.toJson(data), XContentType.JSON);
bulkRequest.add(indexRequest);
});
try {
//执行插入请求操作
log.info("es bulk idxName:{}, dsl:{}", idxName, bulkRequest.getDescription());
return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
} catch (IOException e) {
throw new QueryBusinessException("restClient.createBatch.error:", e);
}
}

```





### mysql的锁


### mysql死锁与解决方案


### 5 分布式事务与 两阶段三阶段提交


### 7 分表分库与一致性hash


### synchronized transaction


### 慢sql的排查经验


### mysql-redis数据一致性


### 3 redis 热key与大key与雪崩、穿透等问题
google-guava 本地cache:
```
public static final Cache<String, Map<String, String>> CATEGORY_GROUP_CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(1)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build();
```


### redis与zk 以及其他分布式锁


### redis的数据结构底层原理


### ES选举算法 & zk选举   & redis哨兵


### 几种mq对比与ack的实现&&mq消息堆积与flink&队列和队列数据丢失如何解决


### apollo 的使用心得与注意事项


### jdk自带的设计模式


### volatile cas synchronize 


### aqs ThreadPoolExecutor


### threadLocal 与内存泄露与内存溢出的案例


### jdk8-11默认gc与cms的几个问题


### 双亲委派与打破双亲委派


### spring解决循环依赖


### spring aop的实现细节


### DDD的思想


### 网络拥塞等情况解决方案如滑动窗口


###  http2与http3兼谈多路复用与webSocket


### BIO NIO AIO


### 短链如何实现


### url运行过程


### netty的架构


### netty与kafka的零拷贝


### golang的携程与管道对比java的线程& futureTask


### 微服务的思考


### Serverless


### 抢红包问题


### 12306问题


### 超卖与秒杀


### 导入导出文件通用化解决


### 项目管理与人员任务分配心得 & 代码管理与review



### 阿里云与AWS与腾讯云的使用体验


### CAP


### 分类聚类算法 && TF/IDF算法


### vue mvvc

