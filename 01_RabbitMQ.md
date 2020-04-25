<font size='7'>RabbitMQ</font>

# 1、AMQP

**AMQP 是什么？**

AMQP（Advanced Message Queuing Protocol,高级消息队列协议）是一个进程间传递**异步消息**的**网络协议**



**AMQP模型**

![](F:\Typora\images\20181022113306601.png)

**工作过程**

发布者（Publisher）发布消息（Message），经由交换机（Exchange）。

交换机根据**路由规则**将收到的消息分发给与该交换机绑定的队列（Queue）。

最后 AMQP 代理会将消息投递给订阅了此队列的消费者，或者消费者按照需求自行获取



**深入理解**

1、发布者、交换机、队列、消费者都可以有多个。同时因为 AMQP 是一个网络协议，所以这个过程中的发布者，消费者，消息代理 可以分别存在于不同的设备上。

2、发布者发布消息时可以给消息指定各种消息属性（Message Meta-data）。有些属性有可能会被消息代理（Brokers）使用，然而其他的属性则是完全不透明的，它们只能被接收消息的应用所使用。

3、从安全角度考虑，网络是不可靠的，又或是消费者在处理消息的过程中意外挂掉，这样没有处理成功的消息就会丢失。基于此原因，AMQP 模块包含了一个消息确认（Message Acknowledgements）机制：当一个消息从队列中投递给消费者后，不会立即从队列中删除，直到它收到来自消费者的确认回执（Acknowledgement）后，才完全从队列中删除。

4、在某些情况下，例如当一个消息无法被成功路由时（无法从交换机分发到队列），消息或许会被返回给发布者并被丢弃。或者，如果消息代理执行了延期操作，消息会被放入一个所谓的死信队列中。此时，消息发布者可以选择某些参数来处理这些特殊情况。



# 2、RabbitMQ

## Rabbit能做些什么？

**消息系统允许软件、应用相互连接和扩展．这些应用可以相互链接起来组成一个更大的应用，或者将用户设备和数据进行连接．消息系统通过将消息的发送和接收分离来实现应用程序的异步和解偶**



## 技术亮点

**可靠性**

RabbitMQ提供了多种技术可以让你在性能和可靠性之间进行权衡。这些技术包括持久性机制、投递确认、发布者证实和高可用性机制。

**灵活的路由**

消息在到达队列前是通过交换机进行路由的。`RabbitMQ` 为典型的路由逻辑提供了多种内置交换机类型。如果你有更复杂的路由需求，可以将这些交换机组合起来使用，你甚至可以实现自己的交换机类型，并且当做`RabbitMQ` 的插件来使用。

**集群**

在相同局域网中的多个RabbitMQ服务器可以聚合在一起，作为一个独立的逻辑代理来使用。

**联合**

对于服务器来说，它比集群需要更多的松散和非可靠链接。为此RabbitMQ提供了联合模型。

**高可用的队列**

在同一个集群里，队列可以被镜像到多个机器中，以确保当其中某些硬件出现故障后，你的消息仍然安全。

**多协议**

RabbitMQ 支持多种消息协议的消息传递。

**广泛的客户端**

只要是你能想到的编程语言几乎都有与其相适配的RabbitMQ客户端。

**可视化管理工具**

RabbitMQ附带了一个易于使用的可视化管理工具，它可以帮助你监控消息代理的每一个环节。

**追踪**

如果你的消息系统有异常行为，RabbitMQ还提供了追踪的支持，让你能够发现问题所在。

**插件系统**

RabbitMQ附带了各种各样的插件来对自己进行扩展。你甚至也可以写自己的插件来使用。



## 测试连接

网址：http://192.168.xxx.xxx:15672      ip+默认端口号（默认为15672）

用户和默认密码：guest

![这里写图片描述](F:\Typora\images\20180805224641957.png)



## RabbitMQ基本概念

![img](F:\Typora\images\16cbc20c53dc5392)

**生产者(Producer) > 交换器(Exchange) > 队列(Queue) > 消费者(Consumer)**



**1: Message**

消息，消息是不具名的，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）、delivery-mode（指出该消息可能需要持久性存储）等。

**2: Publisher**

消息的生产者，也是一个向交换器发布消息的客户端应用程序。

**3: Exchange**

交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。

**4: Binding**

绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。

**5: Queue**

消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

**6: Connection**

网络连接，比如一个TCP连接。

**7: Channel**

信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内地虚拟连接，`AMQP` 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。

**8: Consumer**

消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。

**9: Virtual Host**

虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是 / 。

**10:** **Broker**

表示消息队列服务器实体。





# 3、RabbitMQ中的交换机

## 1、交换机的作用

我们通过队列发送和接收消息中，实际上里面还有一个重要的组合交换机。

在 `RabbitMq` 中，产生着不是直接将消息发送给消费者，产生者根本不知道这个消息要传递给哪些队列。实际上，产生者只是将消息发送给交换机。交换机收到消息后，根据交换机的类型和配置来处理消息，有如下几种情况：

- 将消息传送给特定的队列
- 有可能发送到多个队列中
- 也有可能丢弃消息



**RabbitMQ各个组件的功能归纳**：

- 产生者：发送消息
- 交换机：将收到的消息根据规则路由到特定队列
- 队列：用于存储消息
- 消费者：收到消息并消费



## 2、交换机的类型

1. Direct exchange（直连交换机）
2. Fanout exchange（扇形交换机）
3. Topic exchange（主题交换机）
4. Header exchange（头交换机）

另外，RabbitMQ默认定义了一些交换机

- Default exchange（默认交换机）
- amq.* exchange

还有一类特殊的交换机：Dead Letter Exchange（死信交换机）



|          交换机类型           |            预声明的默认名称             |
| :---------------------------: | :-------------------------------------: |
| Direct exchange（直连交换机） |      (Empty string) and amq.direct      |
| Fanout exchange（扇型交换机） |               amq.fanout                |
| Topic exchange（主题交换机）  |                amq.topic                |
| Headers exchange（头交换机）  | amq.match (and amq.headers in RabbitMQ) |



交换机可以有两个状态：持久（durable）、暂存（transient）。持久化的交换机会在消息代理（Broker）重启后依旧存在，而暂存的交换机则不会（它们需要在代理再次上线后重新被声明）。然而并不是所有的应用场景都需要持久化的交换机。



### 默认交换机

默认交换机（default exchange）实际上是一个由消息代理预先声明好的没有名字（名字为空字符串）的直连交换机（direct exchange）。它有一个特殊的属性使得它对于简单应用特别有用处：那就是每个新建队列（queue）都会自动绑定到默认交换价上，绑定的路由键（routing key）名称与队列名称相同。

举个栗子：当你声明了一个名为 "search-indexing-online" 的队列，AMQP代理会自动将其绑定到默认交换机上，绑定（binding）的路由键名称也是为 "search-indexing-online" 。因此，当携带着名为 "search-indexing-online" 的路由键的消息被发送到默认交换机的时候，此消息会被默认交换机路由至名为 "search-indexing-online" 的队列中。换句话说，默认交换机看起来貌似能够直接将消息投递给队列，尽管技术上并没有做相关的操作。



- **交换器（Exchange, X）**：生产者将消息发送到Exchange，由Exchange再路由到一个或多个队列中；
- **路由键（RoutingKey**）：生产者将消息发送给交换器的时候，会指定RoutingKey指定路由规则，实际情况是需要将RoutingKey、交换器类型、绑定键联合使用才能最终生效。当交换器类型与BindingKey固定情况下，通过执行RoutingKey来决定消息流向哪里。
- **绑定（BindingKey）**：通过绑定键将交换器与队列关联起来，这样RabbitMQ就知道如何正确地将消息路由到队列，其实绑定键也是一种路由键的一种，不过是用在绑定交换器与队列的时候。



### 1、Direct exchange（直连交换机）

bindings（绑定）、routingKey（路由键）

![image-20200410154006111](F:\Typora\images\image-20200410154006111.png)

`direct` 类型的 `exchange` 路由规则时：它会把消息路由到那些 `binding key` 与 `routing key` 完全匹配的 `Queue` 中。

`direct Exchange` 是 `RabbitMQ Broker` 的默认 `exchange` ，它有一个特别的属性对一些简单的应用来说是非常有用的，在使用这个类型的 `exchange` 时，可以不必指定 `routing key` 的名字，在此类型下创建的 `Queue` 有一个默认的 `routing key` ，这个 `routing key` 一般同 `Queue` 同名

`direct` 模式可以使用 `RabbitMQ` 自带的 `exchange`：`default exchange`。所以不需要将 `exchange` 进行任何(binding) 绑定。消息传递时，`RouteKey` 必须完全匹配，才会被队列接收，否则该消息会被抛弃。



### 2、Fanout exchange（扇形交换机）

![image-20200410155157679](F:\Typora\images\image-20200410155157679.png)

任何发送到 **Fanout Exchange** 的消息都会被发送到与该 **Exchange** 绑定（Binding）的所有 **Queue** 上。

1. 这种模式不需要 `RouteKey`
2. 这种模式需要提前将 `Exchange` 与 `Queue` 进行绑定，一个 `Exchange` 可以绑定多个 `Queue`，一个`Queue` 可以同多个 `Exchange` 进行绑定
3. 如果接受到消息的 `Exchange` 没有与任何 `Queue` 绑定，则消息会被抛弃



### 3、Topic exchange（主题交换机）

![image-20200410162346144](F:\Typora\images\image-20200410162346144.png)

**任何发送到 Topic Exchange 的消息都会被转发到所有关心 Route 中指定话题的 Queue 上**

1. 每个队列都有其关心的主题，所有的消息都带有一个“标题”(RouteKey)， `Exchange` 会将消息转发到所有关注主题能与 `RouteKey` 模糊匹配的队列
2. 这种模式需要 `RouteKey`，也许要提前绑定 `Exchange` 与 `Queue` 
3. 在进行绑定时，要提供一个该队列关心的主题，如 “#.log.#” 表示该队列关心所有涉及log的消息(一个RouteKey为”MQ.log.error”的消息会被转发到该队列)
4. 如果 `Exchange` 没有发现能够与 `RouteKey` 匹配的 `Queue` ，则会抛弃此消息

注："#" 表示0个或若干个关键字，"\*"表示一个关键字。如 “log.\* ”能与 “log.warn” 匹配，无法与 “log.warn.timeout” 匹配；但是“log.#”能与上述两者匹配

<img src="F:\Typora\images\image-20200128160405007.png" alt="image-20200128160405007" style="zoom:67%;" />

### 4、Headers exchange（头交换机）

![img](F:\Typora\images\1794747-777bd2c9331f8271.webp)

headers类型的Exchange不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配。

在绑定Queue与Exchange时指定一组键值对；当消息发送到Exchange时，RabbitMQ会取到该消息的headers（也是一个键值对的形式），对比其中的键值对是否完全匹配Queue与Exchange绑定时指定的键值对；如果完全匹配则消息会路由到该Queue，否则不会路由到该Queue。



交换时通过Headers头部来将消息映射到队列的，有点像HTTP的Headers，Hash结构中要求携带一个键“x-match”，这个键的Value可以是any或者all，这代表消息携带的Hash是需要全部匹配(all)，还是仅匹配一个键(any)就可以了。相比直连交换机，首部交换机的优势是匹配的规则不被限定为字符串(string)而是Object类型。

- any: 只要在发布消息时携带的有一对键值对headers满足队列定义的多个参数arguments的其中一个就能匹配上，注意这里是键值对的完全匹配，只匹配到键了，值却不一样是不行的
- all：在发布消息时携带的所有Entry必须和绑定在队列上的所有Entry完全匹配





消息队列（Message Queue，简称MQ），从字面意思上看，本质是个队列，FIFO先入先出，只不过队列中存放的内容是message而已。
		其主要用途：不同进程 `Process`/线程 `Thread` 之间通信。



# 4、RabbitMQ中的消息队列

**什么是消息队列？**

[详解文章](https://developer.51cto.com/art/201904/595020.htm)

我们可以把消息队列比作是一个存放消息的容器，当我们需要使用消息的时候可以取出消息供自己使用。消息队列是分布式系统中重要的组件，使用消息队列主要是为了通过异步处理提高系统性能和削峰、降低系统耦合性。目前使用的比较多的消息队列有ActiveMQ，RabbitMQ，Kafka，RocketMQ。

另外，我们知道队列 Queue 是一种先进先出的数据结构，所以消费消息时也是按照顺序来消费的。比如生产者发送消息1,2,3...对于消费者就会按照1,2,3...的顺序来消费。但是偶尔也会出现消息被消费的顺序不对的情况，比如某个消息消费失败又或者一个 queue 多个consumer 也会导致消息被消费的顺序不对，我们一定要保证消息被消费的顺序正确。



MQ框架非常之多，比较流行的有RabbitMq、ActiveMq、ZeroMq、kafka，以及阿里开源的RocketMQ。



**为什么要用消息队列？**

1.通过异步处理提高系统性能（削峰、减少响应所需时间

![img](F:\Typora\images\2509688-311483f18a8d228e.webp)

如上图，在不使用消息队列服务器的时候，用户的请求数据直接写入数据库，在高并发的情况下数据库压力剧增，使得响应速度变慢。但是在使用消息队列之后，用户的请求数据发送给消息队列之后立即 返回，再由消息队列的消费者进程从消息队列中获取数据，异步写入数据库。由于消息队列服务器处理速度快于数据库（消息队列也比数据库有更好的伸缩性），因此响应速度得到大幅改善。

通过以上分析我们可以得出**消息队列具有很好的削峰作用的功能**——即**通过异步处理，将短时间高并发产生的事务消息存储在消息队列中，从而削平高峰期的并发事务。** 举例：在电子商务一些秒杀、促销活动中，合理使用消息队列可以有效抵御促销活动刚开始大量订单涌入对系统的冲击。如下图所示：

<img src="F:\Typora\images\2509688-f9b9af2c6620e724.webp" alt="img" style="zoom:67%;" />

因为用户请求数据写入消息队列之后就立即返回给用户了，但是请求数据在后续的业务校验、写数据库等操作中可能失败。因此使用消息队列进行异步处理之后，需要适当修改业务流程进行配合，比如用户在提交订单之后，订单数据写入消息队列，不能立即返回用户订单提交成功，需要在消息队列的订单消费者进程真正处理完该订单之后，甚至出库后，再通过电子邮件或短信通知用户订单成功，以免交易纠纷。这就类似我们平时手机订火车票和电影票。



2.降低系统耦合性。



**使用消息队列产生的问题？**

- **系统可用性降低：** 系统可用性在某种程度上降低，为什么这样说呢？在加入MQ之前，你不用考虑消息丢失或者说MQ挂掉等等的情况，但是，引入MQ之后你就需要去考虑了！
- **系统复杂性提高：** 加入MQ之后，你需要保证消息没有被重复消费、处理消息丢失的情况、保证消息传递的顺序性等等问题！
- **一致性问题：** 我上面讲了消息队列可以实现异步，消息队列带来的异步确实可以提高系统响应速度。但是，万一消息的真正消费者并没有正确消费消息怎么办？这样就会导致数据不一致的情况了!



# 5、RabbitMQ的几种应用模型



引入依赖

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.10</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

创建一个连接工具类

```java
public class ConnectionUtils {

    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("192.168.10.128");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("test");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
```



## 1、基本消息模型

![image-20200411105732237](F:\Typora\images\image-20200411105732237.png)

P（producer/ publisher）：生产者，一个发送消息的用户应用程序。

C（consumer）：消费者，消费和接收有类似的意思，消费者是一个主要用来等待接收消息的用户应用程序

队列（红色区域）：rabbitmq内部类似于邮箱的一个概念。虽然消息流经rabbitmq和你的应用程序，但是它们只能存储在队列中。队列只受主机的内存和磁盘限制，实质上是一个大的消息缓冲区。许多生产者可以发送消息到一个队列，许多消费者可以尝试从一个队列接收数据。

总之：

**生产者将消息发送到队列，消费者从队列中获取消息，队列是存储消息的缓冲区。**



**生产者发送消息**

```java
public class Send {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        //获取连接以及mq通道
        Connection connection = ConnectionUtils.getConnection();
        //从连接中创建通道，这是完成大部分API的地方
        Channel channel = connection.createChannel();

        // 声明（创建）队列，必须声明队列才能够发送消息，我们可以把消息发送到队列中。
        // 声明一个队列是幂等的 - 只有当它不存在时才会被创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //消息内容
        String message = "Hello World";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
```



在队列页面可以看到

![image-20200411110552041](F:\Typora\images\image-20200411110552041.png)

点击队列名称，进入详细，查看消息

![image-20200411110617131](F:\Typora\images\image-20200411110617131.png)

**消费者接收消息**

```java
public class Receive {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body即消息体
                String msg = new String(body);
                System.out.println(" [x] received : " + msg + "!");
            }
        };

        //监听队列，第二个参数：是否自动进行消息确认
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

队列中消息已经没有了

![image-20200411110821760](F:\Typora\images\image-20200411110821760.png)



### 消息确认机制（ACK）

通过刚才的案例可以看出，消息一旦被消费者接收，队列中的消息就会被删除。

那么问题来了：RabbitMQ怎么知道消息被接收了呢？

如果消费者领取消息后，还没执行操作就挂掉了呢？或者抛出了异常？消息消费失败，但是RabbitMQ无从得知，这样消息就丢失了！

因此，RabbitMQ有一个ACK机制。当消费者获取消息后，会向RabbitMQ发送回执ACK，告知消息已经被接收。不过这种回执ACK分两种情况：

- 自动ACK：消息一旦被接收，消费者自动发送ACK
- 手动ACK：消息接收后，不会发送ACK，需要手动调用

大家觉得哪种更好呢？

这需要看消息的重要性：

- 如果消息不太重要，丢失也没有影响，那么自动ACK会比较方便
- 如果消息非常重要，不容丢失。那么最好在消费完成后手动ACK，否则接收消息后就自动ACK，RabbitMQ就会把消息从队列中删除。如果此时消费者宕机，那么消息就丢失了。

我们之前的测试都是自动ACK的，如果要手动ACK，需要改动我们的代码：



**自动ACK存在的问题**

运行消费者，程序抛出异常，但是消息依然被消费。

手动ACK

```java
public class Receive {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body即消息体
                String msg = new String(body);
                System.out.println(" [x] received : " + msg + "!");
                //手动ACK
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //监听队列，第二个参数：是否自动进行消息确认
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```



## 2、work消息模型

工作队列或竞争消费者模式

![image-20200411114102158](F:\Typora\images\image-20200411114102158.png)

工作队列（又称：任务队列——Task  Queues）是为了避免等待一些占用大量资源、时间的操作。当我们把任务（Task）当作消息发送到队列中，一个运行在后台的工作者（worker）进程就会取出任务然后处理。当你运行多个工作者（workers），任务就会在它们之间共享。



**避免消息堆积**

1. 采用workqueue，多个消费者监听同一队列
2. 接收到消息之后，通过线程池，异步消费



**生产者**

```java
public class Send {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //循环发布任务
        for (int i = 0; i < 50; i++) {
            //消息内容
            String message = "task ... " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Send '" + message + "'");

            Thread.sleep(i * 2);
        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
```

**消费者1**

```java
public class Receive1 {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
                try {
                    //模拟完成任务的耗时：1000
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //手动ACK
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
```

**消费者2**

```java
public class Receive2 {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取到连接
        Connection connection = ConnectionUtils.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
                //手动ACK,告诉服务器，已经处理成功
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
```



将两个消费者一同启动，然后发送信息

![image-20200411115122129](F:\Typora\images\image-20200411115122129.png)

可以发现，两个消费者各自消费了25条消息，而且各不相同，这就实现了任务的分发。



**能者多劳**

现在的状态属于是把任务平均分配，正确的做法应该是消费越快的人，消费的越多。

 我们可以使用basicQos方法和prefetchCount = 1设置。  这告诉RabbitMQ一次不要向工作人员发送多于一条消息。 或者换句话说，不要向工作人员发送新消息，直到它处理并确认了前一个消息。  相反，它会将其分派给不是仍然忙碌的下一个工作人员。

```java
public static void main(String[] args) throws IOException, TimeoutException {
    //获取到连接
    Connection connection = ConnectionUtils.getConnection();
    //获取通道
    Channel channel = connection.createChannel();

    //声明队列
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);

    //能者多劳，消费的快的，消费的越多
    //设置每个消费者同时只能处理一条消息
    channel.basicQos(1);

    //定义队列的消费者
    DefaultConsumer consumer = new DefaultConsumer(channel) {
        //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
        @Override
        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            //body 即消息体
            String msg = new String(body);
            System.out.println(" [消费者1] received : " + msg + "!");
            try {
                //模拟完成任务的耗时：1000
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //手动ACK
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
    };
    //监听队列
    channel.basicConsume(QUEUE_NAME, false, consumer);
}
```

![image-20200411115357239](F:\Typora\images\image-20200411115357239.png)



## 3、订阅模式

分发一个消息给多个消费者，这种模式被称为 "发布/订阅"



- 1个生产者，多个消费者
- 每一个消费者都有自己的一个队列
- 生产者没有将消息直接发送到队列，而是发送到了交换机
- 每个队列都要绑定到交换机
- 生产者发送的消息，经过交换机到达队列，实现一个消息被多个消费者获取的目的



<img src="F:\Typora\images\image-20200411120017863.png" alt="image-20200411120017863" style="zoom:67%;" />



X（Exchanges）：交换机一方面：接收生产者发送的消息。另一方面：知道如何处理消息，例如递交给某个特别队列、递交给所有队列、或是将消息丢弃。到底如何操作，取决于Exchange的类型。

具体交换机：<a href="##2、交换机的类型">传送门</a>

**Exchange（交换机）只负责转发消息，不具备存储消息的能力**，因此如果没有任何队列与Exchange绑定，或者没有符合路由规则的队列，那么消息会丢失！



**Fanout**

生产者

- 声明 exchange，不再声明 Queue
- 发送消息到 exchange，不再发送到 Queue

```java
public class Send {

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为 fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //消息内容
        String message = "Hello everyone";
        //发布消息到exchange
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [生产者] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
```

消费者，需要将队列和交换机绑定

```java
public class Receive1 {

    private final static String QUEUE_NAME = "fanout_exchange_queue_1";

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列和交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        //监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

Receive2

```java
public class Receive2 {

    private final static String QUEUE_NAME = "fanout_exchange_queue_2";

    private final static String EXCHANGE_NAME = "fanout_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列和交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
            }
        };
        //监听队列，自动返回完成
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```



![image-20200411141235774](F:\Typora\images\image-20200411141235774.png)



![image-20200411141248555](F:\Typora\images\image-20200411141248555.png)

![image-20200411141302723](F:\Typora\images\image-20200411141302723.png)



## 4、路由模式

在某些场景下，我们希望不同的消息被不同的队列消费，这是就要用到 `Direct` 类型的 `Exchange`。在 `Direct` 模型下，队列与交换机的绑定，不能是任意绑定了，而是要指定一个 `RoutingKey`（路由key），消息的发送方在向 `Exchange` 发送消息时，也必须指定消息的 `routing key`

![img](F:\Typora\images\1158835-20200407100904761-1020751412.png)

- P：生产者，向Exchange发送消息，发送消息时，会指定一个routing key。
- X：Exchange（交换机），接收生产者的消息，然后把消息递交给 与routing key完全匹配的队列
- C1：消费者，其所在队列指定了需要routing key 为 error 的消息
- C2：消费者，其所在队列指定了需要routing key 为 info、error、warning 的消息



**生产者**

```java
public class Send {

    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明 exchange ，指定类型为 direct
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        //消息内容
        String message = "删除商品";
        //发送消息，并且指定 routing key 为：insert，代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
```

消费者1

```java
public class Receive1 {

    private final static String QUEUE_NAME = "direct_exchange_queue_1";

    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机，同时指定需要订阅的 routing key，假设此处需要 update 和 delete 消息
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body即消息体
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        //监听队列，自动ACK
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

消费者2

```java
ublic class Receive2 {

    private final static String QUEUE_NAME = "direct_exchange_queue_2";

    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //绑定队列到交换机，同时指定需要订阅的 routing key，假设此处需要 insert、update、delete 消息
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "insert");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "delete");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //body即消息体
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        //监听队列，自动ACK
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

我们分别发送增、删、改的 `RoutingKey`，发现结果：

![image-20200411145058384](F:\Typora\images\image-20200411145058384.png)

![image-20200411145108534](F:\Typora\images\image-20200411145108534.png)



## 5、主题模式

`Topic` 类型的 `exchange` 与 `Direct` 相比，都是可以根据 `RoutingKey` 把消息路由到不同的队列，只不过 `Topic` 类型 `exchange` 可以让队列在绑定 `Routing Key` 的时候使用通配符。

`Routing Key` 一般都是有一个或多个单词组成，多个单词之间以 "." 分割：

通配符规则：

- "#"：匹配一个或多个词
- "*"：匹配不多不少恰好一个词

举例：

- `audit.#`：能够匹配`audit.irs.corporate` 或者 `audit.irs`
- `audit.*`：只能匹配`audit.irs`



![image-20200411145541276](F:\Typora\images\image-20200411145541276.png)

发送者

```java
public class Send {

    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明exchange，指定类型为 topic
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //消息内容
        String message = "新增商品";
        // 发送消息，并且指定routing key 为：insert ,代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "item.insert", null, message.getBytes());
        System.out.println(" [商品服务：] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
```

消费者1

```java
public class Reveive1 {

    private final static String EXCHANGE_NAME = "topic_exchange_test";

    private final static String QUEUE_NAME = "topic_exchange_queue_1";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机，同时指定需要订阅的routing key。需要 update、delete
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.update");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.delete");

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者1] received : " + msg + "!");
            }
        };
        // 监听队列，自动ACK
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```

消费者2

```java
public class Reveive2 {

    private final static String EXCHANGE_NAME = "topic_exchange_test";

    private final static String QUEUE_NAME = "topic_exchange_queue_2";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取到连接
        Connection connection = ConnectionUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机，同时指定需要订阅的routing key。需要 update、delete
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.*");

        // 定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
            }
        };
        // 监听队列，自动ACK
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
```



#### 持久化

如何避免消息丢失？

1. 消费者的ACK机制，可以防止消费者丢失消息
2. 但是，如果在消费者消费之前，MQ就宕机了，消息就没了



所以要将消息持久化，前提是：队列、exchange 都持久化



**交换机持久化**

![image-20200411154442891](F:\Typora\images\image-20200411154442891.png)

**队列持久化**

![image-20200411155300766](F:\Typora\images\image-20200411155300766.png)

**消息持久化**

![image-20200411155842881](F:\Typora\images\image-20200411155842881.png)



![image-20200411172300180](F:\Typora\images\image-20200411172300180.png)



























