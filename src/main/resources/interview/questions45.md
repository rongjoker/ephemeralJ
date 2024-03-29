##经典问题45个

### 1 MySQL如何实现事务（MVCC等、幻读）
这是一个很大的问题。可以从事务的ACID特性的四个角度来分析。
<br>
<br>isolation 隔离由 锁 实现
<br> consistency 一致性 由 undo log来保证,帮助事务回滚+MVCC
<br> atomicity 由 undo log来实现 每次都将重做日志缓存 写入重做日志文件，再调用一次fsync操作,顺序写入
<br> Durability（持久性）：事务处理结束后，对数据的修改就是永久的，即便系统故障也不会丢失。
<br> 额外还有一个binlog，跟redo log很像，但是redo log是在innodb引擎层面产生，而binlog是在数据库上层，任何修改都会产生binlog，binlog是一种逻辑日志，记录的是sql语句；redo log是物理格式日志，记录的是对于每一页的修改。
<br>binlog只要由事务提交完成就会进行写入；redo log是并发的，不是在事务提交时候写入，记录的顺序并不是事务开始的顺序。
<br> 事务执行失败或者接收到rollback命令进行回滚，可以利用undo log回滚到修改之前的样子。undo log也是 逻辑日志。
<br> mvcc也是用undo log实现，用户读取一行记录，如果该记录已经被其他事务占用，当前事务可以通过undo读取之前的行版本信息，实现非锁定读取
<br> undo log 也会产生redo log，因为undo log也需要持久性的保护
<br>
   [![hpWHHA.md.png](https://z3.ax1x.com/2021/08/22/hpWHHA.md.png)](https://imgtu.com/i/hpWHHA)


[数据库MVCC和隔离级别的关系是什么？ - 大宽宽的回答](https://www.zhihu.com/question/279538775/answer/407458020)

mvcc 读不加锁,读写不冲突。举个验证数据一致性的例子。比如你在统计一个订单表的金额总量和支付那边的流水对。如果不用RR，你一边读着，数据一边在被变化，到最后在你统计的几十分钟里，只要发生新的下单支付/撤单退款，你的数字一定对不上。用了RR，就等价于你在统计这个事务一开始的“snapshot”（实际上你在用snapshot isolation）。这样，你统计的数字才有可能是对的。

保证读写之间不会有锁竞争，提升了性能
版本链：一条数据被多次改变，被undo日志记录下来，并且每次改变有一个记录，记录带一个自增的事务id，通过回滚指针进行一个一个连接起来。
readview 的作用是确定当前选用版本链中的对应版本。记录活跃的没有被提交的事务id，以及下一个要分配的事务id

rr模式下，生成一个readview是以一个事务为单位的，所以一个事务内看到的数据是相同的
解决了快照读模式下的幻读问题
间隙锁解决了当前读模式下的幻读问题


<br>

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
10. Index Condition Pushdown 索引下推 解决 `组合索引满足最左匹配，但是遇到非等值判断时匹配停止` 问题
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
不需要考虑的问题：
partition 导致的无序问题 可以一个表指定到一个partition




### 5 mysql的锁
1. 间隙锁和行锁合称 next-key lock，每个 next-key lock 是前开后闭区间。锁住了所有的行和所有可能的行，解决幻读的问题。但是间隙锁的引入，可能会导致同样的语句锁住更大的范围，这其实是影响了并发度的。
```
begin;
select * from t where d>5 for update;
commit;
```
此时会锁定(5,+∞)
<br>
2. 两阶段锁协议:在 InnoDB 事务中，行锁是在需要的时候才加上的，但并不是不需要了就立刻释放，而是要等到事务结束时才释放。这个就是两阶段锁协议。<br>
3. **innodb行级锁是通过锁索引记录实现的**。如`update t set t.name='abc' where t.name='cde'`; name字段无索引，会扫描表。

4. 锁升级过程:
锁升级(Lock Escalation)是指将当前锁的粒度变粗。举个例子：数据库可以把一个表的1000个行锁升级为一个页锁，或者将页锁升级为表锁
索引失效会引发锁升级，产生表锁


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
业务上能避免就尽量避免。两段提交，或者尽量送达然后补偿。都麻烦，后者，最后对账(本地消息表，幂等性，定时check等看着头痛)


### 8 分表分库与一致性hash

水平分库分表后的分页查询：
1. 全局视野法：若查询第x页的数据，每页y条。一共n个库。将order by time offset (x*y+1) limit y，改写成order by time offset 0 limit (x*y+1) +y，
服务层将改写后的SQL语句发往各个分库：即每库各取x页的数据，服务层对得到的数据进行内存排序，内存排序后再取偏移量x*y+1后的y条记录
2. 业务折中：只提供“下一页”的功能。每个库执行order by time where time>0 limit y  得到 n*y条数据，内存排序，得到前y条，即为第1页。

删除节点: 损失一部分hash数据，如果一台服务器不可用，受影响的数据仅仅是此服务器到其环空间前一台服务器之间的数据
增加节点: 一部分hash数据会在后续抵达新节点，此时对象ObjectA、ObjectB没有受到影响，只有Object C重新定位到了新的节点X上
使用场景：
将数据Key使用相同的函数Hash计算出哈希值，并确定此数据在环上的位置，从此位置沿环顺时针查找，遇到的服务器就是其应该定位到的服务器。

### 9 synchronized transaction

spring 事务内如果保证事务提交后再执行其他操作(可能是热门题目)

解法：利用 事务同步管理器 `TransactionalEventListener` 解决
org.springframework.transaction.support.TransactionSynchronizationManager

示例：
```
@Transactional(rollbackFor = Exception.class)
    public Boolean inviteUser(..) {
        userService.add(..);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                integralService.addIntegration(..,20)
            }
        });


```

注解方式:
```
@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void addIntegration(..){
        integralService.addIntegration(..,20)
    }

```

核心源码:

```

@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (TransactionSynchronizationManager.isSynchronizationActive()) {
			TransactionSynchronization transactionSynchronization = createTransactionSynchronization(event);
			TransactionSynchronizationManager.registerSynchronization(transactionSynchronization);
		}
		else if (this.annotation.fallbackExecution()) {
			if (this.annotation.phase() == TransactionPhase.AFTER_ROLLBACK && logger.isWarnEnabled()) {
				logger.warn("Processing " + event + " as a fallback execution on AFTER_ROLLBACK phase");
			}
			processEvent(event);
		}
		else {
			// No transactional event execution at all
			if (logger.isDebugEnabled()) {
				logger.debug("No transaction is active - skipping " + event);
			}
		}
	}

```
本质上还是使用了TransactionSynchronizationManager，只是对他再一次进行封装


### 10 慢sql的排查经验
1. 线上追加索引
2. 扫表



### 11 mysql-redis数据一致性
1. Redis里的数据不立刻更新，等redis里数据自然过期。然后去DB里取，顺带重新set redis。这种用法被称作“Cache Aside”。
2. 代码做的显式更新DB，然后马上del掉redis里的数据。在下次取数据时，模式就恢复到了上一条说的方式。这也算是一种Cache Aside的变体。这要做的好处是，数据的一致性会比较好，一般正常情况下，数据不一致的时间会在1s以下
3. Redis里的数据总是不过期，但是有个背景更新任务（“定时执行的代码” 或者 “被队列驱动的代码）读取db，把最新的数据塞给Redis。这种做法将Redis看作是“存储”.这种做法还有一种变体Write Through，写入时直接写DB，DB把数据更新Cache，而读取时读Cache。
4. 电商秒杀的扣库存，银行转账等，以上的做法就不够用了。解决办法也有两大类。第一种是不要用Redis，只用DB。或者更直接点说是“只要一个单点的数据源”。这样肯定就没有一致性问题，代价就是CAP中因为CP被满足，因此A被牺牲掉。
<br> [如何保持mysql和redis中数据的一致性？ - 大宽宽的回答 - 知乎](https://www.zhihu.com/question/319817091/answer/653985863)

实践一般先写库，后删缓存就完了, 其实是有小概率不一致的(延时双删, 也不好说，时间平经验);不管了，让redis自己缓存过期，不少业务并不要求实时生效



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



### 13 redis与zk 以及其他分布式锁 & redLock & redssion
Redis分布式锁通过setnx(set if not exists)来占位，使用完调用del释放，利用过期避免del未被调用出现锁不释放的情况,redis新版本通过设置set指令扩展参数，使得setnx和expir可以一起执行，彻底解决了非原子性引发的各种问题。<br>
但是随之有另外一个问题，任务没有在过期时间内执行完，锁被其他线程占用。解决方案有2：<br>
1. 避免分布式锁用于长任务
2. 添加随机数进行验证，删除必须同样的随机数才可以，否则只能等待系统过期时间。查询和删除通过Lua脚本来保证原子性

Redssion是在加锁成功之后，注册一个定时任务去监听这个锁，每隔一段时间去检查这个锁，如果还持有这个锁，那就修改过期时间，对这个过期时间进行续期。这个延长过期时间的机制也叫做watchdog“看门狗”机制。加锁的过程实际上也是调用了一个Lua脚本，这里只是画图表明了这个Lua脚本加锁的过程，有兴趣可以查看下Redisson加锁的Lua脚本源码



Redis 保证了AP， zk 保证了CP 

细说zookeeper，怎么选举的（ZAB协议）
ZK如何保证其一致性？

zookeeper就是个分布式文件系统,当你注册服务就是创建了一个znode节点，节点存储了该服务的IP、端口、调用方式。它是强一致（CP）
就是Zookeeper保证整个cluster只有一个有效的node作为leader，所有的写都需要汇总到leader这里来排序，然后这个顺序写会由leader发给cluster所有的node，然后得到大多数（majority）的同意后，才确认这个写有效，并返回结果（committed）给客户c1或c2


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

# 空间聚合
# geo_hash算法, 网格聚合grid
# 两次聚合
def aggs_geohash_grid():
    body = {
        "aggs": {
            "new_york": {
                "geohash_grid": {
                    "field":     "point",
                    "precision": 5
                }
            },
            "map_zoom": {
                "geo_bounds": {
                    "field": "point"
              }
            }
          }
    }

```
4. rehash
不同的是，Redis的字典只能是字符串，另外他们rehash的方式不一样，因为Java的HashMap的字典很大时，rehash是个耗时的操作，需要一次全部rehash。Redis为了追求高性能，不能堵塞服务，所以采用了渐进式rehash策略。<br>
渐进式rehash会在rehash的同时，保留新旧两个hash结构，如下图所示，查询时会同时查询两个hash结构，然后在后续的定时任务以及hash操作指令中，循环渐进地将旧hash的内容一点点地迁到新的hash结构中。当搬迁完成了，就会使用新的hash结构取而代之。当hash移除最后一个元素后，该数据结构自动删除，内存被回收。<br>
set 也是用的dict数据结构，value为NUll
5. hyperloglog底层怎么实现的?为什么会有误差呢?
   Redis 内部都是使用字符串位图来存储 HyperLogLog 所有桶的计数值。密集存储的结构非常简单，就是连续 16384 个 6bit 串成的字符串位图



### 15 ES选举算法 & zk选举   & redis哨兵
Elasticsearch编号比较的判断依据有两个，首先是ClusterState版本号的比较，版本号越大优先级越高，然后是节点id的比较，id越小优先级越高。ClusterState是Master向集群中各个节点发送的集群状态，这个状态有一个版本号码，如果集群状态发生了变化，比如集群新增了节点成员或者有节点成员退出了，那么这个版本号就会加一，比对这个版本号的目的是让拥有最新状态的节点成为Master的优先级最高。

可以处理like类似的查询

es做了大量的缓存，性能更快。


### 16 几种mq对比与ack的实现&&mq消息堆积与flink&队列和队列数据丢失如何解决

消息列队 堵塞

堵塞说明你的消费能力跟不上
1、可以创建更多的消费者
2、优化消费代码
消息重复
每个消息加唯一标识。处理过了不再处理。幂等性


RabbitMQ由于消费者读取消息之后可能会把消息放回（或者重传）到队列中（例如，处理失败的情况），这样就会导致消息的顺序无法保证

对于Kafka来说，它在消息处理方面提供了可靠的顺序保证。Kafka能够保证发送到相同主题分区的所有消息都能够按照顺序处理。

RabbitMQ支持消息存活时间、延迟/预定的消息
死信
消息被拒绝(basic.reject / basic.nack)，并且requeue = false
消息TTL过期
队列达到最大长度

### 17 apollo 的使用心得与注意事项


### 18 jdk自带的设计模式

JDK源码对应的设计模式 迭代器:迭代器模式 runnable 命令模式

适配器模式 （可通过创建方法识别采用不同抽象/接口类型的实例，并返回自己/另一个抽象/接口类型的实现，其装饰/覆盖给定实例）
java.util.Arrays#asList()

装饰器模式 （通过创作方法识别采用相同抽象/接口类型的实例，添加额外的行为）
所有子类java.io.InputStream，OutputStream，Reader并Writer有一个构造函数取相同类型的实例。

责任链模式 （通过行为方法识别（间接地）在队列中的相同抽象/接口类型的另一个实现中调用相同的方法）
javax.servlet.Filter#doFilter()

观察者模式（或发布/订阅） （可以通过行为方法识别，根据自己的状态调用另一个抽象/接口类型的实例上的方法）
所有实现java.util.EventListener（因此实际上各地的Swing）


观察者模式与消费者模式的区别：
这两个模式确实有点相似，都为了实现程序的解耦产生的，观察者一般又称发布/订阅模式，它一般是有一个主题对象，然后有多个订阅者去关注它，当它的状态发生变化时，会自动通知这些订阅者；而消费者模式类似一个缓存队列的概念，它也称为生产者/消费者模式，生产者只负责生产数据不去做处理（缓解高并发的问题），而消费者只从消费中间件里拿到所要处理的数据，并进行相应的逻辑处理工作，生产者与消费者是相互不知道对方的存在的


### 19 volatile cas synchronize 

CPU有缓存一致性协议：MESI，这不错。但MESI并非是无条件生效的！
java虚拟机在实现volatile关键字的时候，是写入了一条lock 前缀的汇编指令。
lock 前缀的汇编指令会强制写入主存，也可避免前后指令的CPU重排序，并及时让其他核中的相应缓存行失效，从而利用MESI达到符合预期的效果。
非lock前缀的汇编指令在执行写操作的时候，可能是是不生效的。比如前面所说的Store Buffer的存在，lock前缀的指令在功能上可以等价于内存屏障，可以让其立即刷入主存。
是volatile的底层实现，满足了MESI的触发条件，才让变量有了缓存一致性。



servlet 是否线程安全
由于servlet在Tomcat中是以单例模式存在的，所有的线程共享实例变量。多个线程对共享资源的访问就造成了线程不安全问题
synchronized同步快对同一条线程来说是可重入的，不会出现自己把自己锁死的问题；
Mutex Lock
监视器锁（Monitor）本质是依赖于底层的操作系统的Mutex Lock（互斥锁）来实现的。每个对象都对应于一个可称为" 互斥锁" 的标记，这个标记用来保证在任一时刻，只能有一个线程访问该对象。

互斥锁：用于保护临界区，确保同一时间只有一个线程访问数据。对共享资源的访问，先对互斥量进行加锁，如果互斥量已经上锁，调用线程会阻塞，直到互斥量被解锁。在完成了对共享资源的访问后，要对互斥量进行解锁。
在运行期间，Java对象头Mark Word里存储的数据会随着锁标志位的变化而变化


java可以利用Syschronized、ReentrantLock、AtomicInteger类实现线程安全，AtomicInteger封装了一个【private volatile int value;】属性，它可以对这个属性进行许多原子性操作，这些原子性操作大多是基于cas原理，而在cas中，AtomicInteger使用的是一个叫Unsafe的类中的方法，Unsafe可以提供一些底层操作，也就是CPU特定的指令集，进而避免了并发问题（Unsafe是一个很危险的类，它可以做一些和内存相关的操作）


synchronized当没有竞争出现时，默认使用偏斜锁。JVM 会利用 CAS 操作在对象头上的 Mark Word 部分设置线程 ID ，以表示对象偏向当前线程。所以并不涉及真正的互斥锁，这样做的假设是基于在很多应用场景中，大部分对象生命周期中最多会被一个线程锁定，使用偏斜锁可以降低无竟争开销。
交替竞争关系：轻量级锁
同时竞争：此时输出结果为重量级锁。自旋的代码是写在轻量级锁膨胀中的，就是轻量级锁获取失败在进入重量级锁过程中进行自旋。CAS自旋实际上是CPU空转，升级为重量级锁避免空转。

<br>
gas

```
  public final int getAndSet(int newValue) {
        return U.getAndSetInt(this, VALUE, newValue);
    }

```
<br>
cas:

```

    public final boolean compareAndSet(int expectedValue, int newValue) {
        return U.compareAndSetInt(this, VALUE, expectedValue, newValue);
    }

```

### 20 aqs ThreadPoolExecutor

线程2会将自己放入AQS中的一个等待队列，因为自己尝试加锁失败了，此时就要将自己放入队列中来等待，等待线程1释放锁之后，自己就可以重新尝试加锁了。
所以大家可以看到，AQS是如此的核心！AQS内部还有一个等待队列，专门放那些加锁失败的线程！
<br>
接着，线程1在执行完自己的业务逻辑代码之后，就会释放锁！他释放锁的过程非常的简单，就是将AQS内的state变量的值递减1，如果state值为0，则彻底释放锁，会将“加锁线程”变量也设置为null！
<br>
接下来，会从等待队列的队头唤醒线程2重新尝试加锁。好！线程2现在就重新尝试加锁，这时还是用CAS操作将state从0变为1，此时就会成功，成功之后代表加锁成功，就会将state设置为1。此外，还要把“加锁线程”设置为线程2自己，同时线程2自己就从等待队列中出队了。
<br>

ThreadPoolExecutor的拒绝策略RejectedExecutionHandler
自定义拒绝策略：
```
class MyRejectedExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        new Thread(r,"新线程"+new Random().nextInt(10)).start();
    }
}

```


### 21 threadLocal 与内存泄露与内存溢出的案例 ConcurrentHashMap

threadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal不存在外部强引用时，Key(ThreadLocal)势必会被GC回收，这样就会导致ThreadLocalMap中key为null， 而value还存在着强引用，只有thead线程退出以后,value的强引用链条才会断掉。

但如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：

Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value
永远无法回收，造成内存泄漏。

由于ThreadLocalMap中的key是ThreadLocal的弱引用，一旦发生GC便会回收ThreadLocal，那么此时的ThreadLocalMap存储的key便是null。如果不通过手动remove()那么ThreadLocalMap的Entry便伴随线程的整个生命周期造成内存泄漏，大致就是一个thread ref -> thread -> threadLocals -> entry -> value的强引用关系。因此Java其实是有对于内存泄漏的一些预防机制的，每次调用ThreadLocal的set()、get()、remove()方法时都会回收key为空的Entry的value。

那么为什么ThreadLocalMap的key要设计成弱引用呢？其实很简单，如果key设计成强引用且没有手动remove()，那么key会和value一样伴随线程的整个生命周期，如果key是弱引用，被GC后至少ThreadLocal被回收了，在下一次的set()、get()、remove()还会回收key为null的Entry的value。


### 22 jdk8-11默认gc与cms的几个问题
jdk8环境下，默认使用 Parallel Scavenge（新生代）+ Serial Old（老年代）
因为CMS并不算优秀的GC策略啊，老生常谈的话，有以下缺点：
1. CMS在GC时会对CPU有比较大的压力，形成典型的CPU Spike。
2. CMS仅针对老年代，还需要一个年轻代的收集器。CMS又和Parallel Scavenge不兼容，只能和ParNew凑合，然而ParNew又不如Parallel Scavenge先进。
3. CMS没法处理浮动垃圾，并发标记过程中死亡的对象只能留到以后的GC处理。
4. Mark-Sweep算法对内存碎片无能为力，并发又如何？内存碎片太多，触发了Concurrent Mode Failure还不是得去请Serial Old来收拾烂摊子？结果就是世界のstop。
...以上的种种，造成的结果就是ParNew+CMS+Serial Old的组合工作起来其实并不稳定。为了得到CMS那一点好处，需要付出很多的代价（包括JVM调参）



### 23 双亲委派与打破双亲委派
在Launcher指定了Bootstrap ClassLoader的加载文件夹System.getProperty("sun.boot.class.path");这个主要就是JDK指定的\lib\rt包在ExtClassLoader中指定了加载文件夹：String var0 = System.getProperty("java.ext.dirs");这个主要就是JDK指定的\lib\ext包在AppClassLoader中指定了加载文件夹：String var1 = System.getProperty("java.class.path");都是环境变量，自行可配。这个主要指的是开发中的类路径

<br>
通过委派的方式，可以避免类的重复加载，当父加载器已经加载过某一个类时，子加载器就不会再重新加载这个类
<br>
通过双亲委派的方式，还保证了安全性。因为Bootstrap ClassLoader在加载的时候，只会加载JAVA_HOME中的jar包里面的类，如java.lang.Integer，那么这个类是不会被随意替换的，除非有人跑到你的机器上， 破坏你的JDK
<br>
打破的场景：
热部署

<br>
为什么JNDI、JDBC等需要破坏双亲委派？

ServiceLoader<Driver> loadedDrivers = ServiceLoader.load(Driver.class);
DriverManager是被根加载器加载的，那么在加载时遇到以上代码，会尝试加载所有Driver的实现类，但是这些实现类基本都是第三方提供的，根据双亲委派原则，第三方的类不能被根加载器加载。

<br>
JDBC中通过引入ThreadContextClassLoader（线程上下文加载器，默认情况下是AppClassLoader）的方式破坏了双亲委派原则。
第一行，获取当前线程的线程上下⽂类加载器 AppClassLoader，⽤于加载 classpath 中的具体实现类。

<br>
为什么TOMCAT要破坏双亲委派？

<br>
不同的应用程序可能会依赖同一个第三方类库的不同版本，但是不同版本的类库中某一个类的全路径名可能是一样的。如多个应用都要依赖hollis.jar，但是A应用需要依赖1.0.0版本，但是B应用需要依赖1.0.1版本。这两个版本中都有一个类是com.hollis.Test.class。如果采用默认的双亲委派类加载机制，那么是无法加载多个相同的类。所以，Tomcat破坏双亲委派原则，提供隔离的机制，为每个web容器单独提供一个WebAppClassLoader加载器。Tomcat的类加载机制：为了实现隔离性，优先加载 Web 应用自己定义的类，所以没有遵照双亲委派的约定，每一个应用自己的类加载器——WebAppClassLoader负责加载本身的目录下的class文件，加载不到时再交给CommonClassLoader加载，这和双亲委派刚好相反。
<br>
简单来说就是tomcat下的应用可以互相隔离，各自可以用不同版本的第三方类库


### 24 spring解决循环依赖
三级缓存解决循环依赖:
```


/ 一级缓存，缓存正常的bean实例
/** Cache of singleton objects: bean name to bean instance. */
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

// 二级缓存，缓存还未进行依赖注入和初始化方法调用的bean实例
/** Cache of early singleton objects: bean name to bean instance. */
private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

// 三级缓存，缓存bean实例的ObjectFactory
/** Cache of singleton factories: bean name to ObjectFactory. */
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

public Object getSingleton(String beanName) {
    return getSingleton(beanName, true);
}

protected Object getSingleton(String beanName, boolean allowEarlyReference) {
    // 先尝试中一级缓存获取
    Object singletonObject = this.singletonObjects.get(beanName);
    // 获取不到，并且当前需要获取的bean正在创建中
    // 第一次容器初始化触发getBean(A)的时候，这个isSingletonCurrentlyInCreation判断一定为false
    // 这个时候就会去走创建bean的流程，创建bean之前会先把这个bean标记为正在创建
    // 然后A实例化之后，依赖注入B，触发B的实例化，B再注入A的时候，会再次触发getBean(A)
    // 此时isSingletonCurrentlyInCreation就会返回true了

    // 当前需要获取的bean正在创建中时，代表出现了循环依赖（或者一前一后并发获取这个bean）
    // 这个时候才需要去看二、三级缓存
    if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
        // 加锁了
        synchronized (this.singletonObjects) {
            // 从二级缓存获取
            singletonObject = this.earlySingletonObjects.get(beanName);
            if (singletonObject == null && allowEarlyReference) {
                // 二级缓存也没有，并且允许获取早期引用的话 - allowEarlyReference传进来是true
                ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                // 从三级缓存获取ObjectFactory
                if (singletonFactory != null) {
                    // 通过ObjectFactory获取bean实例
                    singletonObject = singletonFactory.getObject();
                    // 放入二级缓存
                    this.earlySingletonObjects.put(beanName, singletonObject);
                    // 从三级缓存删除
                    // 也就是说对于一个单例bean，ObjectFactory#getObject只会调用到一次
                    // 获取到早期bean实例之后，就把这个bean实例从三级缓存升级到二级缓存了
                    this.singletonFactories.remove(beanName);
                }
            }
        }
    }
    // 不管从哪里获取到的bean实例，都会返回
    return singletonObject;
}

```

### 25 spring aop的实现细节

利用asm进行字节码增强，修改字节码，产生子类,接管了之前的方法

### 26 DDD的思想


### 27 网络拥塞等情况解决方案如滑动窗口
HTTP/2解决了HTTP的队头阻塞问题，但是并没有解决TCP队头阻塞问题
HTTP/2因为没有顺序了，所以就不需要阻塞了，就有效的解决了HTTP对队头阻塞的问题。

h2 通过多路复用解决了以前 HTTP1.1 管道化请求时的队首阻塞问题
TCP队头阻塞:TCP是一个按序传输的通道,一旦丢包就会等待重新发包，阻塞后续内容传输

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

[美团Java NIO浅析](https://tech.meituan.com/2016/11/04/nio.html)

标准/典型的Reactor：
步骤1：等待事件到来（Reactor负责）。
步骤2：将读就绪事件分发给用户定义的处理器（Reactor负责）。
步骤3：读数据（用户处理器负责）。
步骤4：处理数据（用户处理器负责）。

```
  Channel channel;
   while(channel=Selector.select()){//选择就绪的事件和对应的连接
      if(channel.event==accept){
         registerNewChannelHandler(channel);//如果是新连接，则注册一个新的读写处理器
         Selector.interested(read);
      }
      if(channel.event==write){
         getChannelHandler(channel).channelWritable(channel);//如果可以写，则执行写事件
      }
      if(channel.event==read){
          byte[] data = channel.read();
          if(channel.read()==0)//没有读到数据，表示本次数据读完了
          {
          getChannelHandler(channel).channelReadComplate(channel，data;//处理读完成事件
          }
          if(过载保护){
          Selector.interested(read);
          }

```

NIO由原来的阻塞读写（占用线程）变成了单线程轮询事件，找到可以进行读写的网络描述符进行读写。除了事件的轮询是阻塞的（没有可干的事情必须要阻塞），剩余的I/O操作都是纯CPU操作，没有必要开启多线程。 并且由于线程的节约，连接数大的时候因为线程切换带来的问题也随之解决，进而为处理海量连接提供了可能。

<br>
我们需要的线程，其实主要包括以下几种： 1. 事件分发器，单线程选择就绪的事件。 2. I/O处理器，包括connect、read、write等，这种纯CPU操作，一般开启CPU核心个线程就可以。 3. 业务线程，在处理完I/O后，业务一般还会有自己的业务逻辑，有的还会有其他的阻塞I/O，如DB操作，RPC等。只要有阻塞，就需要单独的线程。

Java的Selector对于Linux系统来说，有一个致命限制：同一个channel的select不能被并发的调用。因此，如果有多个I/O线程，必须保证：一个socket只能属于一个IoThread，而一个IoThread可以管理多个socket。

另外连接的处理和读写的处理通常可以选择分开，这样对于海量连接的注册和读写就可以分发。虽然read()和write()是比较高效无阻塞的函数，但毕竟会占用CPU，如果面对更高的并发则无能为力。


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

做密码机工作的时候读过北邮的一篇关于加密算法的文章。非对称加密。<br>
常见的对称加密算法有DES、3DES、AES、Blowfish、IDEA、RC5、RC6.<br>
要想使用非对称加密算法，首先要有一对key，一个被称为private key私钥，一个成为public key公钥，然后可以把你的public key分发给想给你传密文的用户，然后用户使用该public key加密过得密文，只有使用你的private key才能解密，也就是说，只要你自己保存好你的private key，就能确保，别人想给你发的密文不被破解，所以你不用担心别人的密钥被盗<br>
常见的非对称加密算法有RSA、DSA.<br>
由于进行的都是大数计算，使得 RSA 最快的情况也比 DES 慢上好几倍，无论是软件还是硬件实现。速度一直是 RSA 的缺陷。一般来说只用于少量数据加密。RSA 的速度是对应同样安全级别的对称密码算法的1/1000左右。
比起 DES 和其它对称算法来说，RSA 要慢得多。实际上一般使用一种对称算法来加密信息，然后用 RSA 来加密比较短的公钥，然后将用 RSA 加密的公钥和用对称算法加密的消息发送给接收方。
这样一来对随机数的要求就更高了，尤其对产生对称密码的要求非常高，否则的话可以越过 RSA 来直接攻击对称密码。



HTTPS详细握手过程，CA证书存放的位置，TSL1.2和TSL1.3的主要区别，对称密钥和非对称密钥等?
HTTPS 其实就是在 HTTP 层和 TCP 之间加了一个 SSL/TLS 层

SSL / TLS 握手详细过程
SSL(Secure Sockets Layer 安全套接字协议),及其继任者传输层安全（Transport Layer Security，TLS）

1. "client hello" 消息：客户端通过发送 “client hello” 消息向服务器发起握手请求，该消息包含了客户端所支持的 TLS 版本，支持的算法列表和密码组合以供服务器进行选择，还有一个 “client random” 随机字符串。

2. "server hello" 消息：服务器发送 “server hello” 消息对客户端进行回应，该消息包含了服务器选择的加密算法，服务器选择的密码组合，数字证书和 “server random” 随机字符串。 
   
3. 验证：客户端对服务器发来的证书进行验证，确保对方的合法身份，验证过程可以细化为以下几个步骤：

检查数字签名
验证证书链
检查证书的有效期
检查证书的撤回状态 (撤回代表证书已失效)

4. "premaster secret"字符串：客户端向服务器发送另一个随机字符串 “premaster secret (预主密钥)”，这个字符串是经过服务器的公钥加密过的，只有对应的私钥才能解密。

5. 使用私钥：服务器使用私钥解密 “premaster secret”。

6. 生成共享密钥：客户端和服务器均使用 client random，server random 和 premaster secret，并通过相同的算法生成相同的共享密钥 KEY。

7. 客户端就绪：客户端发送经过共享密钥 KEY 加密过的 “finished” 信号，为了防止握手过程遭到篡改，该消息的内容是前一阶段所有握手消息的MAC值。

8. 服务器就绪：服务器发送经过共享密钥 KEY 加密过的 “finished” 信号，该消息的内容是前一阶段所有握手消息的 MAC 值。

9. 达成安全通信：握手完成，双方使用对称加密进行安全通信。

[原文链接](https://blog.csdn.net/happyjacob/article/details/113447803)

### 33 netty的架构 & netty与kafka的零拷贝

[Kafka为什么吞吐量大、速度快](https://zhuanlan.zhihu.com/p/120967989)
kafka优势：<br>
kafka吞吐量更高：
1. Zero Copy机制，内核copy数据直接copy到网络设备，不必经过内核到用户再到内核的copy，减小了copy次数和上下文切换次数，大大提高了效率。
linux操作系统 “零拷贝” 机制使用了sendfile方法， 允许操作系统将数据从Page Cache 直接发送到网络，只需要最后一步的copy操作将数据复制到 NIC 缓冲区， 这样避免重新复制数据 (零拷贝不依赖用户进程空间的copy,1.Producer发送消息到broker后broker持久化到磁盘采用的是mmap ;2.customer拉取broker的数据时使用sendFile,即把分区内文件数据读入os缓冲区后直接转到socket buffer进行网络传输)
2. 磁盘顺序读写，减少了寻道等等的时间。磁盘的顺序读写性能却很高，一般而言要高出磁盘随机读写三个数量级，一些情况下磁盘顺序读写性能甚至要高于内存随机读写.这种方法有一个缺陷—— 没有办法删除数据 ，所以Kafka是不会删除数据的.
3. 批量处理机制，服务端批量存储，客户端主动批量pull数据，消息处理效率高。
4. 存储具有O(1)的复杂度，读物因为分区和segment，是O(log(n))的复杂度。
5. 分区机制，有助于提高吞吐量。 
6. Page Cache ，Kafka利用了操作系统本身的Page Cache，就是利用操作系统自身的内存而不是JVM空间内存,避免GC问题;操作系统本身也对于Page Cache做了大量优化，提供了 write-behind、read-ahead以及flush等多种机制。再者，即使服务进程重启，系统缓存依然不会消失，避免了in-process cache重建缓存的过程





### 34 golang的协程与管道对比java的线程 & futureTask & callable

FutureTask的实现：
```

public class FutureTask<V> implements RunnableFuture<V>

```
FutureTask类实现了RunnableFuture接口，我们看一下RunnableFuture接口的实现：
```

public interface RunnableFuture<V> extends Runnable, Future<V> {
void run();
}

```
可以看出RunnableFuture继承了Runnable接口和Future接口，而FutureTask实现了RunnableFuture接口。所以它既可以作为Runnable被线程执行，又可以作为Future得到Callable的返回值。


操作系统层面，进程运行有5个状态：运行态、就绪态、阻塞态、创建态、结束态。jvm的线程调用的是内核线程。

Java的NIO用于业务的问题，其核心痛点应该是JDBC。这是个诞生了几十年的，必须使用Blocking IO的DB交互协议。其上承载了Java庞大的生态和业务逻辑。Java要改自己的编程方式，必须得重新设计和实现JDBC

[为什么 Java 坚持多线程不选择协程？ - 大宽宽的回答 - 知乎](https://www.zhihu.com/question/332042250/answer/734115120)

做为Huawei JDK的协程特性的开发者:
[为什么 Java 坚持多线程不选择协程？ - 海纳的回答 - 知乎](https://www.zhihu.com/question/332042250/answer/732749013)

### 35 微服务的思考
1. 微服务的好处，可以很好的解耦数据、产品、开发、基础设施等，使维护迭代大型应用变成可能，变成全异步和全并行
2. 更清晰的服务边界也使得标准化变得困难，微服务非常依赖上层框架来进行规约，否则迭代的时间成本、断服成本和协调成本变得很高
3. DB的设计与业务规划变成难点和痛点

### 36 Serverless


### 37 抢红包问题


### 38 12306问题
设计一个高并发售票网站，分布式缓存分担查询请求考虑一致性，如何防止并发超卖，同一班次列车在不同运营段的票务表设计。

一、表结构定义：
主键|车次|座位号|区间加数|发车日期|出售状态

二、【区间加数】解释：
1，将列车的整个行程，按【临近两个站点之间的行程】划分为多个【最小独立区间】。N个站点，即有N-1个【区间】。
2，所有车次，从起点到终点依次将【区间】的【区间加数】设置为1、2、4、8、...、2^m。
3，以G1列车为例：
3.1，列车4个站点依次为：北京南、天津南、南京南、上海。
3.2，分为3个区间：北京南~天津南(区间加数=1)、天津南~南京南(区间加数=2)、南京南~上海(区间加数=4)。

三、查询G1列车2021年10月1日【天津南~上海】的可售卖的座位号列表，SQL如下：

SELECT 座位号, SUM(区间加数) AS 区间加数和 FROM t
WHERE 车次=G1 AND 发车日期 = 2021-10-01 AND 出售状态=未出售
GROUP BY 座位号
HAVING 区间加数和 = (2 + 4)

四、【区间加数和】解释：
1，【区间加数和】是旅客所要购买的两个站点之间连续多个【区间】的【区间加数】的和。
2.1，只要能证明在等比数列1、2、4、8、...、2^m中，任意连续x个元素的和，不等于任意连续y个元素的和。则证明【区间加数和】具有唯一性，任意给定的【区间加数和】可以对应固定的一段行程（一个或多个连续区间）。
2.3，在旅客选择起点与终点后，将该段行程中的【区间加数】求和，传入SQL即可。
3，经过简单数学证明，将等式两边除以最小公约数；等式一边恒为奇数，另一边恒为偶数。即证明2.1中的等比数列可以满足条件，所以通过【区间加数和】可以映射到一段连续不重复的行程；故SQL成立。

### 39 超卖与秒杀


### 40 导入导出文件通用化解决
小数据量的情况下：导入，解析数据，每一行添加一个错误信息的hashmap，返回；提交无错误的数据，进行二次校验，没有错误则入库
导出，开放表头接口+分页查询数据接口，统一到一个微服务接口去调用下载，配置好指定的服务、接口、文件类型等等

大文件分片上传：大文件拆分小文件，用多个存储服务分开存放，通过文件ID识别文件，用MD5校验文件完整性。


### 41 第三方登录原理
实质就是 OAuth 授权,案例：

```
1、A 网站让用户跳转到 GitHub。
2、GitHub 要求用户登录，然后询问"A 网站要求获得 xx 权限，你是否同意？"
3、用户同意，GitHub 就会重定向回 A 网站，同时发回一个授权码。
4、A 网站使用授权码，向 GitHub 请求令牌。
5、GitHub 返回令牌.
6、A 网站使用令牌，向 GitHub 请求用户数据。
```


### 42 系统设计

一个系统用户在coupang上的操作会产生log这个log会放到kafka里面然后有个datacollector将kafka里面log放到S3里面User可以再coupang页面上面提交一个request里面有ID你需要设计一个nearrealtime的系统去支持从user发出请求开始所有时间之后log都要发送给客户界面理解错误的点也是在这里我理解为offline的需要去S3里面querylog但是大叔给了个提示：可以设计个kafkaconsumer直接从kafka拿incoming的log然后步入正轨开始设计要求还有一大堆：每个loglatency必须小于1s避免singlepointfailure还有四五个要求没有全部记下来然后我设计就是用dynamdb去存并且解释了dynamoD


### 43 CAP & zk 与redis的比较  & 羊群效应

 CAP理论
-  CAP理论：一个分布式系统不可能同时满足一致性，可用性和分区容错性这个三个基本需求，最多只能同时满足其中两项
- 一致性(C)：数据在多个副本之间是否能够保持一致的特性。
- 可用性(A)：是指系统提供的服务必须一致处于可用状态，对于每一个用户的请求总是在有限的时间内返回结果，超过时间就认为系统是不可用的
- 分区容错性(P)：分布式系统在遇到任何网络分区故障的时候，仍然需要能够保证对外提供满足一致性和可用性的服务，除非整个网络环境都发生故障。

 CAP定理的应用
- 放弃P(CA)：如果希望能够避免系统出现分区容错性问题，一种较为简单的做法就是将所有的数据(或者是与事物先相关的数据)都放在一个分布式节点上，这样虽然无法保证100%系统不会出错，但至少不会碰到由于网络分区带来的负面影响
- 放弃A(CP):其做法是一旦系统遇到网络分区或其他故障时，那受到影响的服务需要等待一定的时间，应用等待期间系统无法对外提供正常的服务，即不可用
- 放弃C(AP):这里说的放弃一致性，并不是完全不需要数据一致性，是指放弃数据的强一致性，保留数据的最终一致性。

羊群效应
大量服务同时注册，zk集群本身要复制数据，zk client会接收到大量节点变化的通知，双端压力很大

### 44 分类聚类算法 && TF/IDF算法  &&  漏斗算法

分类算法: 朴实贝叶斯

es 包含检索和排序两块核心，检索基于倒排索引；排序采用tf * idf算法，词频（term frequency，TF）指的是某一个给定的词语在该文件中出现的频率;某一特定词语的IDF，可以由总文件数目除以包含该词语之文件的数目.这个算法的核心要义就是一个词在一个文档里出现的频率很高，而在所有文档里出现的频率较低，那么检索这个词的时候，这个文档排名就会靠前。<br>
QueryElevationComponent
elevate.xml 
查询完推广的信息之后，再查询solr的数据，比如推广的信息有10条，一页有20，则第一页查询solr limit 0,10即可

漏斗算法:[知乎资料](https://zhuanlan.zhihu.com/p/134536155)

漏斗算法顾名思义采用一个漏斗来对流量进行限制。
因为漏斗下面有孔，所以会定时的漏水下去，然后我们可以将流量想象为从上边落入漏斗的水。
这样就会有两种情况:
1. 如果流量的注入漏斗的速度比漏斗的漏水的速度慢，漏斗就会处于一个空漏斗的状态，也就是没有超出负荷的状态。
2. 第二种情况就是，如果流量注入漏斗的速度比漏斗快，那么漏斗就会渐渐的超出最大的容量，对于溢出的流量，漏斗会采用拒绝的方式，防止流量继续进入。

### 45 vue mvvc

它可以总共分为8个阶段：创建前/后,载入前/后，更新前/后，销毁前/销毁后
第一次页面加载时会触发beforeCreate,created,beforeMount,mounted

computed是计算属性，也就是计算值，它更多用于计算值的场景
computed具有缓存性，computed的值在getter执行后是会缓存的，只有在它依赖的属性值改变之后，下一次获取computed的值时重新调用对应的getter来计算
computed适用于计算比较消耗性能的计算场景

watch无缓存性，页面重新渲染时值不变化也会执行
状态管理:Store的代码结构一般由State、Getters、Mutation、Actions这四种组成

Model-View-ViewModel

```
Vue是以数据为驱动的，Vue自身将DOM和数据进行绑定，一旦创建绑定，DOM和数据将保持同步，每当数据发生变化，DOM会跟着变化。

ViewModel是Vue的核心，它是Vue的一个实例。Vue实例时作用域某个HTML元素上的，这个HTML元素可以是body，也可以是某个id所指代的元素。

DOM Listeners和Data Bindings是实现双向绑定的关键，实现的原理是Object.defineProperty中的get和set方法，及消息订阅模式。DOM Listeners监听页面所有View层DOM元素的变化，当发生变化，Model层的数据随之变化；Data Bindings监听Model层的数据，当数据发生变化，View层的DOM元素随之变化。

```