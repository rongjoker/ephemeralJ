##经典问题45个

### 1 MySQL如何实现事务（MVCC等、幻读）
这是一个很大的问题。可以从事务的ACID特性的四个角度来分析。
<br>
1. 原子性：由undo保证 
2. 持久性：由redo保证
3. 隔离性：由undo+事务ID+mvcc保证
4. 一致性：**由程序保障+AID，一致性是我们最终的目的。**
<br>
   [![hpWHHA.md.png](https://z3.ax1x.com/2021/08/22/hpWHHA.md.png)](https://imgtu.com/i/hpWHHA)
<br>
   每一行数有多个版本，当我们要去读取数据的时候，要判断这个数据的版本号，对当前事务而言，是否可见，如果不可见，则要根据undo log计算得到上一个版本。如果上一个版本也不符合要求，则要找到再上一个版本， 直到找到对应正确的数据版本。
   <br>
   **并发版本控制(MCVV)的概念是什么, 是怎么实现的?**
   并不是数据版本比当前事务的ID大，对当前事务来说，此版本的数据不可见了。因为事务启动(begin)的时候就分配了事务ID，但是一致性视图是在第一个快照读的时候才创建的，如果在这段时间内(启动事务和一致性视图创建间隔)，有其他事务开启并提交了，对于当前事务来说这些提交依然是可见的，即使这些事务ID比当前事务的ID大
   <br>

### 1 MySQL如何实现事务（MVCC等、幻读）
[数据库MVCC和隔离级别的关系是什么？ - 大宽宽的回答](https://www.zhihu.com/question/279538775/answer/407458020)

mvcc 读不加锁,读写不冲突。举个验证数据一致性的例子。比如你在统计一个订单表的金额总量和支付那边的流水对。如果不用RR，你一边读着，数据一边在被变化，到最后在你统计的几十分钟里，只要发生新的下单支付/撤单退款，你的数字一定对不上。用了RR，就等价于你在统计这个事务一开始的“snapshot”（实际上你在用snapshot isolation）。这样，你统计的数字才有可能是对的。

当前读:
select...lock in share mode (共享读锁)
select...for update
update , delete , insert
当前读, 读取的是最新版本, 并且对读取的记录加锁, 阻塞其他事务同时改动相同记录，避免出现安全问题。

快照读:
Read Repeatable隔离级别：开启事务后第一个select语句才是快照读的地方，而不是一开启事务就快照读。

#### 2 MySQL开发过程中的优化技巧（MRR、删除数据的方式）
1. 利用判断数据是否是数字，来将参数拆分城多个查询参数，避免or查询
2. 将验证的代码块放放在事务外面，减少事务锁表的时间和范围
3. in 查询用exists优化
4. 字段默认是“”来避免null操作(索引不会包含有NULL值的列,只要列中包含有NULL值都将不会被包含在索引中)
5. 用rename表来清空表数据，而不是delete from 或者 truncate(对于AUTO_INCREMENT的列来说，当在truncate之后，改列的第一个值仍从1开始，但是delete不释放空间，仍然顺序使用删除之前的值；delete属于DML，truncate是DDL语言， 操作立即生效，自动提交，原数据不放到rollback segment中，不能回滚)
6. total 查询不精确，类似 es total 10000以上不准确
7. 软删除修改状态而不是直接delete，减轻程序的复杂度
8. order by field() 函数使用 :
```
ORDER BY FIELD(status,
   'In Process',
   'On Hold',
   'Cancelled',
   'Resolved',
   'Disputed',
   'Shipped');
```
9. Multi-Range Read 多范围读(MRR) , 它的作用针对基于辅助/第二索引的查询，减少随机IO，并且将随机IO转化为顺序IO，提高查询效率。具体步骤:
   1. 根据索引 a，定位到满足条件的记录，将 id 值放入 read_rnd_buffer 中
   2. 将 read_rnd_buffer 中的 id 进行递增排序；
   3. 排序后的 id 数组，依次到主键 id 索引中查记录，并作为结果返回。
10. 索引下沉
11. 使用 hash 字段 & 倒序存储 & hash 字段 等方法优化索引大小

### 3 binlog为什么还需要redo log
WAL 的全称是 Write-Ahead Logging，它的关键点就是先写日志，再写磁盘。当有一条记录需要更新的时候，InnoDB 引擎就会先把记录写到 redo log里面，并更新内存，这个时候更新就算完成了。日志是顺序读写，更新是随机读写。checkpoint 是当前要擦除的位置，也是往后推移并且循环的，擦除记录前要把记录更新到数据文件。binlog是追加写，crash时不能判定binlog中哪些内容是已经写入到磁盘，哪些还没被写入。而redolog是循环写，从check point到write pos间的内容都是未写入到磁盘的<br>
**两阶段提交**:<br>
将 redo log 的写入拆成了两个步骤：prepare 和 commit，这就是"两阶段提交"。
[![5ek8IJ.png](https://z3.ax1x.com/2021/10/11/5ek8IJ.png)](https://imgtu.com/i/5ek8IJ)


### 4 通过binlog同步数据到es
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
// 增删改 (供应商不会被删除，故不需要考虑删除的清空)
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





### 5 mysql的锁
间隙锁和行锁合称 next-key lock，每个 next-key lock 是前开后闭区间。锁住了所有的行和所有可能的行，解决幻读的问题。但是间隙锁的引入，可能会导致同样的语句锁住更大的范围，这其实是影响了并发度的。
```
begin;
select * from t where d>5 for update;
commit;
```
此时会锁定(5,+∞)
<br>
两阶段锁协议:在 InnoDB 事务中，行锁是在需要的时候才加上的，但并不是不需要了就立刻释放，而是要等到事务结束时才释放。这个就是两阶段锁协议。<br>
**innodb行级锁是通过锁索引记录实现的**。如`update t set t.name='abc' where t.name='cde'`; name字段无索引，会扫描表。



### 6 mysql死锁与解决方案
死锁:当并发系统中不同线程出现循环资源依赖，涉及的线程都在等待别的线程释放资源时，就会导致这几个线程都进入无限等待的状态，称为死锁。即循环依赖或者循环等待。<br>
死锁解决:
1. 等待超时
2. 死锁检测

实用方案:<br>
通过将一行改成逻辑上的多行来减少锁冲突。还是以影院账户为例，可以考虑放在多条记录上，比如 10 个记录，影院的账户总额等于这 10 个记录的值的总和。这样每次要给影院账户加金额的时候，随机选其中一条记录来加。这样每次冲突概率变成原来的 1/10，可以减少锁等待个数，也就减少了死锁检测的 CPU 消耗。相当于子账户的概念，原理上就是分段汇总，Java原子类LongAdder也使用了这个原理。影子账户。

问题:
如果你要删除一个表里面的前 10000 行数据，有以下三种方法可以做到：第一种，直接执行 delete from T limit 10000;第二种，在一个连接中循环执行 20 次 delete from T limit 500;第三种，在 20 个连接中同时执行 delete from T limit 500。
答案:第二种最好。第一种人为制造大事务，第三种人为制造锁冲突。


### 7 分布式事务 & 两阶段三阶段提交 


### 8 分表分库与一致性hash

删除节点: 损失一部分hash数据
增加节点: 一部分hash数据会在后续抵达新节点
使用场景：

### 9 synchronized transaction


### 10 慢sql的排查经验
1. 线上追加索引



### 11 mysql-redis数据一致性
1. Redis里的数据不立刻更新，等redis里数据自然过期。然后去DB里取，顺带重新set redis。这种用法被称作“Cache Aside”。
2. 代码做的显式更新DB，然后马上del掉redis里的数据。在下次取数据时，模式就恢复到了上一条说的方式。这也算是一种Cache Aside的变体。这要做的好处是，数据的一致性会比较好，一般正常情况下，数据不一致的时间会在1s以下
3. Redis里的数据总是不过期，但是有个背景更新任务（“定时执行的代码” 或者 “被队列驱动的代码）读取db，把最新的数据塞给Redis。这种做法将Redis看作是“存储”.这种做法还有一种变体Write Through，写入时直接写DB，DB把数据更新Cache，而读取时读Cache。
4. 电商秒杀的扣库存，银行转账等，以上的做法就不够用了。解决办法也有两大类。第一种是不要用Redis，只用DB。或者更直接点说是“只要一个单点的数据源”。这样肯定就没有一致性问题，代价就是CAP中因为CP被满足，因此A被牺牲掉。
<br> [如何保持mysql和redis中数据的一致性？ - 大宽宽的回答 - 知乎](https://www.zhihu.com/question/319817091/answer/653985863)

### 12 redis 热key与大key与雪崩、穿透等问题

1. 缓存穿透:数据库没有,缓存没有<br>
   Spring-boot最新默认cache即支持空对象,为空则保存org.springframework.cache.support.NullValue;布隆过滤器也可以解决
2. 缓存击穿:数据库有,缓存没有(并发情况下恶劣影响);增加双写一致性;热key过期一瞬间也会发生，属于热key情况(参考4);redis查询不到，加锁去数据库查
3. 缓存雪崩:大量缓存同时失效,db面临同时大量请求涌入;添加随机过期时间，避免集中过期;热点数据不过期,热key处理(参考4)
4. 热key:<br>
   其实热key问题说来也很简单，就是瞬间有几十万的请求去访问redis上某个固定的key，从而压垮缓存服务的情情况。 其实生活中也是有不少这样的例子。比如XX明星结婚。那么关于XX明星的Key就会瞬间增大，就会出现热数据问题。
   以xy苹果助手为例子，热词有三种，像"微信"，"微博"，"QQ"，"支付宝"之类，是可以预知的热点数据；第二种是xy苹果助手引流推广的APP或者关键词，比如有段时间一元夺宝类app很火，公司快速制作了xy夺宝，首页重点推荐xy,再比如公司15年上市后大力推广全民奇迹，一度很火爆，这类主动引流的数据通常都是热点数据，qps很高；第三类是不可预知，但是可以通过类似日志分析统计出来的热词，用时间滑动窗口的的方式计算出最近一段时间最热的搜索词。这三类热点数据都会放入本地cache，并有轮询线程定时更新，避免了经过redis。
   登录 redis-cli 命令行，输入monitor，即可进入到 redis 监控模式。

google-guava 本地cache:
```
public static final Cache<String, Map<String, String>> CATEGORY_GROUP_CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(1)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build();
```


### 13 redis与zk 以及其他分布式锁
Redis分布式锁通过setnx(set if not exists)来占位，使用完调用del释放，利用过期避免del未被调用出现锁不释放的情况,redis新版本通过设置set指令扩展参数，使得setnx和expir可以一起执行，彻底解决了非原子性引发的各种问题。<br>
但是随之有另外一个问题，任务没有在过期时间内执行完，锁被其他线程占用。解决方案有2：<br>
1. 避免分布式锁用于长任务
2.  添加随机数进行验证，删除必须同样的随机数才可以，否则只能等待系统过期时间。查询和删除通过Lua脚本来保证原子性

Redis 保证了AP， zk 保证了CP 


### 14 redis的数据结构底层原理

1. 布隆过滤器 vs guava
bf.add/bf.madd 增加一个/多个 或者bf.exists/bf.mexists 判断一个/多个
布隆过滤器可以检查值是 “可能在集合中” 还是 “绝对不在集合中”，提供 K 个不同的哈希函数，并将结果位置上对应位的值置为 “1”
传统的布隆过滤器并不支持删除操作。但是名为 Counting Bloom filter 的变种可以用来测试元素计数个数是否绝对小于某个阈值，它支持元素删除。
案例:垃圾邮件过滤，爬虫判断已经爬过的网站等等

2. 限流
利用zset可以做滑动窗口进行限流，value填毫秒时间戳,利用pipeline进行批量计算，配合过期时间。同一个用户的同一种行为放在一个zset里。<br>
高级限流：漏斗限流,4.0开始，通过Redis-Cell实现了漏斗算法

3. GeoHash 与ES的 geohash
geohash 将二维的经纬度数据映射到一维的证书，这样所有的元素都挂在一条线上，距离靠近的二维坐标映射到一维后点与点的距离也更近。内部利用zset实现，本质是skiplist<br>
es 提供了两种类型的地理数据，分别是基于经纬度的geo_point数据类型 和 基于GeoJson的geo_shape数据类型，并且geo_shape数据类型支持点、线、圆、多边形、多多边形等复杂的地理形状。geo_shape有点过于复杂，它支持很多较复杂的地理形状。距离查询:
```
GET location/_search
{
  "query": {
    "geo_distance": {
      "distance": "5km",
      "locationStr": "40.174697,116.5864"
    }
  },
  "sort": [
    {
      "_geo_distance": {
        "locationStr": "40.174697,116.5864",
        "order": "asc",
        "unit": "km",
        "distance_type": "plane"
      }
    }
  ]
}

```
4. rehash
不同的是，Redis的字典只能是字符串，另外他们rehash的方式不一样，因为Java的HashMap的字典很大时，rehash是个耗时的操作，需要一次全部rehash。Redis为了追求高性能，不能堵塞服务，所以采用了渐进式rehash策略。<br>
渐进式rehash会在rehash的同时，保留新旧两个hash结构，如下图所示，查询时会同时查询两个hash结构，然后在后续的定时任务以及hash操作指令中，循环渐进地将旧hash的内容一点点地迁到新的hash结构中。当搬迁完成了，就会使用新的hash结构取而代之。当hash移除最后一个元素后，该数据结构自动删除，内存被回收。<br>


### 15 ES选举算法 & zk选举   & redis哨兵
Elasticsearch编号比较的判断依据有两个，首先是ClusterState版本号的比较，版本号越大优先级越高，然后是节点id的比较，id越小优先级越高。ClusterState是Master向集群中各个节点发送的集群状态，这个状态有一个版本号码，如果集群状态发生了变化，比如集群新增了节点成员或者有节点成员退出了，那么这个版本号就会加一，比对这个版本号的目的是让拥有最新状态的节点成为Master的优先级最高。

可以处理like类似的查询

es做了大量的缓存，性能更快。


### 16 几种mq对比与ack的实现&&mq消息堆积与flink&队列和队列数据丢失如何解决


### 17 apollo 的使用心得与注意事项


### 18 jdk自带的设计模式


### 19 volatile cas synchronize 


### 20 aqs ThreadPoolExecutor


### 21 threadLocal 与内存泄露与内存溢出的案例


### 22 jdk8-11默认gc与cms的几个问题


### 23 双亲委派与打破双亲委派


### 24 spring解决循环依赖


### 25 spring aop的实现细节


### 26 DDD的思想


### 27 网络拥塞等情况解决方案如滑动窗口


### 28 http2与http3兼谈多路复用与webSocket

通常的认知里，http是无状态的，可以看作短连接。可是在http1.1发布之初(1996年6月)即支持长连接（PersistentConnection）和请求的流水线（Pipelining）， 2.0更是直接支持了复用。

短连接有两个比较大的问题：1，创建新连接耗费的时间尤为明显；2，TCP 连接的性能只有在该连接被使用一段时间后(热连接)才能得达到最佳。长连接的出现，解决了这两个问题。长连接会保持一段时间，重复用于发送一系列请求，节省了新建 TCP 连接握手的时间，还可以利用 TCP 的性能增强能力。当然这个连接也不会一直保留着：连接在空闲一段时间后会被关闭。
长连接也还是有缺点的；就算是在空闲状态，它还是会消耗服务器资源，而且在重负载时，还有可能遭受 DoS attacks 攻击。

流水线是复用的前身。流水线是在同一条长连接上发出连续的请求，而不用等待应答返回。这样可以避免连接延迟。理论上讲，性能还会因为两个 HTTP 请求有可能被打包到一个 TCP 消息包中而得到提升。
流水线的缺点是：正确的使用很麻烦，需要了解传输的资源、有效带宽，处理不当的话，重要的消息可能被延迟到不重要的消息后面。另外一个关键的缺点是【队首阻塞】(即HOT)

[![hUykp4.md.png](https://z3.ax1x.com/2021/08/31/hUykp4.md.png)](https://imgtu.com/i/hUykp4)

h2的重点概念:
流（stream）：流是连接中的一个虚拟信道，可以承载双向的消息；每个流都有一个唯一的整数标识符（1、2…N）<br>
消息（message）：指逻辑上的 HTTP 消息，比如请求、响应等，由一或多个帧组成<br>
帧（frame）：HTTP/2 通信的最小单位，每个帧包含帧首部，至少也会标识出当前帧所属的流，承载着特定类型的数据，如 HTTP 首部、负荷等<br>


1. 二进制分帧,HTTP/2是二进制协议而不是文本协议<br>
2. 这是一个复用协议。并行的请求能在同一个链接中处理，移除了HTTP/1.x中顺序和阻塞的约束。<br>
3. 压缩了headers。因为headers在一系列请求中常常是相似的，其移除了重复和传输重复数据的成本。HTTP/2 在客户端和服务器端使用“首部表”来跟踪和存储之前发送的键－值对，对于相同的数据，不再通过每次请求和响应发送<br>
4. 允许服务器在客户端缓存中填充数据，通过一个叫服务器推送的机制来提前请求。某些资源客户端是一定会请求的，这时就可以采取服务端 push 的技术，提前给客户端推送必要的资源，这样就可以相对减少一点延迟时间.<br>

HTTP/3 基于 UDP 协议的 QUIC(Quick UDP Internet Connection) 协议.0RTT 建连 无延迟连接
QUIC通过使用类似 TCP 快速打开的技术，缓存当前会话的上下文，在下次恢复会话的时候，只需要将之前的缓存传递给服务端验证通过就可以进行传输了。0RTT 建连可以说是 QUIC 相比 HTTP/2 最大的性能优势.
由于建立在 UDP 的基础上，同时又实现了 0RTT 的安全握手，所以在大部分情况下，只需要 0 个 RTT 就能实现数据发送，在实现前向加密的基础上，并且 0RTT 的成功率相比 TLS 的会话记录单要高很多。
[![hUyx8e.md.png](https://z3.ax1x.com/2021/08/31/hUyx8e.md.png)](https://imgtu.com/i/hUyx8e)

因为 TCP 是基于 IP 和端口去识别连接的，这种方式在多变的移动端网络环境下是很脆弱的。而 QUIC 是通过 ID 的方式去识别一个连接，不管你网络环境如何变化，只要 ID 不变，就能迅速重连上
QUIC 协议有一个非常独特的特性，称为前向纠错（Forward Error Correction，FEC），每个数据包除了它本身的内容之外，还包括了部分其它数据包的数据，因此少量的丢包可以通过其它包的冗余数据直接组装而无需重传。
前向纠错牺牲了每个数据包可以发送数据的上限，但是减少了因为丢包导致的数据重传次数。这会取得更好的效果，因为数据重传将会消耗更多的时间，包括确认数据包丢失、请求重传与等待新数据包等步骤。

> 快手的KCP，谷歌的QUIC，都是基于UDP的。B站 在Web 上大规模铺开了QUIC/HTTP3 ， 使用UDP替代了TCP进行视频传输，期待更快的提高打开时间和降低卡顿。

### 29 BIO NIO AIO


### 30 短链如何实现
[如何实现一个短链接服务](https://www.zhihu.com/question/20103344/answer/911532621)

能不能用Hash算法？还是不能，用Hash存在哈希碰撞的问题

随机数然后base62就够了，碰撞了就重新生成
>为什么要用62进制转换
>62进制转换是因为62进制转换后只含数字+小写+大写字母。而64进制转换会含有/,+这样的符号（不符合正常URL的字符）
10进制转62进制可以缩短字符，如果我们要6位字符的话，已经有560亿个组合了。

当我们在浏览器里输入 http://bit.ly/a3300 时
1. DNS首先解析获得 http://bit.ly的 IP 地址
2. 当 DNS 获得 IP 地址以后（比如：12.34.5.32），会向这个地址发送 HTTP GET 请求，查询短码 a3300
3. [http://bit.ly 服务器会通过短码 a3300 获取对应的长 URL
4. 请求通过 HTTP 301 转到对应的长 URL http://www.theaustralian.news.com.au/story/0,25197,26089617-5013871,00.html。

> 知识点：为什么要使用302跳转，而不是301跳转呢？
> 301是永久重定向，302是临时重定向。短地址一经生成就不会变化，所以用301是符合http语义的。但是如果用了301， Google，百度等搜索引擎，搜索的时候会直接展示真实地址，那我们就无法统计到短地址被点击的次数了，也无法收集用户的Cookie, User Agent 等信息，这些信息可以用来做很多有意思的大数据分析，也是短网址服务商的主要盈利来源。

### 31 url运行过程
1. 在浏览器中输入url地址，回车或者点击转到
2. 浏览器检查输入的URL是否合法
3. 浏览器对URL进行分析和拆解
4. IP地址DNS查询
   查询浏览器缓存->查询系统缓存->向DNS服务器发起查询请求->根域名服务器
5. 调用Socket发送数据
   应用层:客户端在应用层发送HTTP请求
   传输层:在传输层（TCP）加入端口号
   网络层:在网络层（IP）加入IP地址
   数据链路层:在数据链路层加入MAC地址
6. Web Server服务接收到请求报文
   应用服务器：Weblogic、Tomcat 、Web Server：IIS、Apache,可能前置有网关、代理服务器等等
7. 阅读HTTP请求内容/参数信息/cookies
8. 服务进行操作或处理，生成HTTP响应，编码后传输
9. 客户端接收请求并解码，用浏览器渲染引擎将文档进行解析，构建DOM树
10. 浏览器使用CPU、GPU完成网页渲染

### 32 HTTPS 对称与非对称加密


### 33 netty的架构 & netty与kafka的零拷贝

kafka优势：<br>
kafka吞吐量更高：
1）Zero Copy机制，内核copy数据直接copy到网络设备，不必经过内核到用户再到内核的copy，减小了copy次数和上下文切换次数，大大提高了效率。
2）磁盘顺序读写，减少了寻道等等的时间。
3）批量处理机制，服务端批量存储，客户端主动批量pull数据，消息处理效率高。
4）存储具有O(1)的复杂度，读物因为分区和segment，是O(log(n))的复杂度。
5）分区机制，有助于提高吞吐量。


### 34 golang的协程与管道对比java的线程& futureTask


### 35 微服务的思考


### 36 Serverless


### 37 抢红包问题


### 38 12306问题


### 39 超卖与秒杀


### 40 导入导出文件通用化解决


### 41 项目管理与人员任务分配心得 & 代码管理与review


### 42 阿里云与AWS与腾讯云的使用体验


### 43 CAP


### 44 分类聚类算法 && TF/IDF算法


### 45 vue mvvc

