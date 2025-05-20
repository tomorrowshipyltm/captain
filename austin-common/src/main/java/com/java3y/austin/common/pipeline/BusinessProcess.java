package com.java3y.austin.common.pipeline;

/**
 * 业务执行器
 * 普通发送pipeline 、消费消息的pipeline的节点都implements BusinessProcess
 */
public interface BusinessProcess<T extends ProcessModel> {

    /**
     * 真正处理逻辑
     *
     * @param context
     */
    void process(ProcessContext<T> context);
}
