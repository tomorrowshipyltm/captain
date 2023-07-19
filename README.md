
## 消息推送平台captain介绍

**核心功能**：统一的接口发送各种类型消息，对消息生命周期全链路追踪。

**意义**：只要公司内部有发送消息的需求，都应该要有类似`austin`的项目。消息推送平台对各类消息进行统一发送处理，这有利于对功能的收拢，以及提高业务需求开发的效率。

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c267ebb2ff234243b8665312dbb46310~tplv-k3u1fbpfcp-zoom-1.image)

## 使用姿势

**1**、创建需要发送的渠道账号

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1720f1e9c6b546a18b13a84fabe1f562~tplv-k3u1fbpfcp-zoom-1.image)

**2**、创建消息模板

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b387c53663d04796a20fda2d4ba925ac~tplv-k3u1fbpfcp-zoom-1.image)

**3**、测试发送消息是否正常

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e58637c9a00a482f91d1a84ab7a17c51~tplv-k3u1fbpfcp-zoom-1.image)

**4**、查看消息下发情况

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c2dd1369fa8a4f40abcbb5c02ed21893~tplv-k3u1fbpfcp-zoom-1.image)

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2b49be4a87f44621b5b916114b17bd28~tplv-k3u1fbpfcp-zoom-1.image)

**5**、亦可在新建模板时选择**定时任务**，通过上传[csv文件](https://www.yuque.com/office/yuque/0/2022/csv/1285871/1671865125068-b5385387-b4a4-41ac-a43e-bab54ee49d88.csv?from=https%3A%2F%2Fwww.yuque.com%2Fu1047901%2Fniffsu%2Fqqtese%2Fedit)和指定cron表达式实现下发消息

![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/42ecf9c743bd4e3493e092d525cbffe7~tplv-k3u1fbpfcp-zoom-1.image)

## 部署姿势

austin项目**强依赖**`MySQL`/`Redis`/(**大概需要2G内存**)，**弱依赖**`kafka`/`prometheus`/`graylog`/`flink`/`xxl-job`/`apollo`/`hive`(**完全部署所有的服务，大概16G内存**)。如果缺少相关的组件可戳：[安装相关组件教程](INSTALL.md)。

> 实在想要`clone`项目后不用自己部署环境直接在**本地**启动`debug`，我这提供了[股东服务](https://www.yuque.com/u1047901/eg5qvy/hh0gk5p4uwie8bva)，**直连**部署好的服务器。

**1**、austin目前使用的MySQL版本**5.7x**，如果你使用的MySQL版本8.0，注意改变`pom.xml`所依赖的版本以及对应的连接信息。

**2**、填写`application.properties`中`spring.datasource`对应的`ip/port/username/password`信息

**3**、执行`sql`文件夹下的`austin.sql`创建对应的表

**4**、填写`application.properties`中`spring.redis`对应的`ip`/`port`/`password`信息

**5**、以上配置信息都在`application.properties`文件中修改。(`prometheus`/`graylog`/`flink`/`xxl-job`/`apollo`/`kafka`/`hive`可选)

**6**、**austin前端管理系统部署**，戳[GitHub](https://github.com/ZhongFuCheng3y/austin-admin)或[Gitee](https://gitee.com/zhongfucheng/austin-admin)跳转至对应的仓库

**7**、（可选）正常使用**数据管理**(查看实时数据链路下发)需要将`austin-stream`的`jar`包上传至`Flink`，根据[部署文档](INSTALL.md)启动Flink。在打`jar`包前需要填写`com.java3y.austin.stream.constants.AustinFlinkConstant`中的`redis`和`kafka`的`ip/port`（注意：日志的topic在`application.properties`中的`austin.business.log.topic.name`。如果没有该topic，需要提前创建，并**使用Kafka**作为消息队列实现)

**8**、（可选）正常使用**定时任务**需要部署`xxl-job`，根据[部署文档](INSTALL.md)启动xxl的调度中心，并在`application.properteis`中填写  `austin.xxl.job.ip`和`austin.xxl.job.port`

**9**、（可选）正常使用**分布式日志采集**需要部署`graylog`，根据[部署文档](INSTALL.md)启动`graylog`，并在`application.properteis`中填写  `austin.graylog.ip`。

**10**、（可选）正常使用**系统监控**需要部署`promethus`和`grafana`，根据[部署文档](INSTALL.md)配置`grafana`图表。

**11**、（可选）正常使用**动态配置中心**需要部署`apollo`，根据[部署文档](INSTALL.md)启动`apollo`，通过docker-compose启动需要在AustinApplication注入对应的ip和port(可看注释)。

**12**、（可选）正常使用**数据仓库**需要部署`hive`，根据[部署文档](INSTALL.md)通过`flink`把数据写入到`hive`中（`flink`环境也要安装好），将`austin-data-house`的`jar`包提交到`flink`执行
