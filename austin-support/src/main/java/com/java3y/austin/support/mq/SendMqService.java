package com.java3y.austin.support.mq;


/**
 * 发送数据至消息队列
 * 有多种实现方式：eventBus、kafka、rocketMq、redis, 其中eventBus单机队列不支持tag过滤
 * @Autowired KafkaTemplate rocketMqTemplate、redisTemplate;
 */
public interface SendMqService {
    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     * @param tagId  用于分布式队列过滤
     */
    void send(String topic, String jsonValue, String tagId);


    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     */
    void send(String topic, String jsonValue);

}
