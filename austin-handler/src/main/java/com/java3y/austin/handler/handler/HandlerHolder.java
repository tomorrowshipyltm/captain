package com.java3y.austin.handler.handler;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * channel->Handler的映射关系
 *
 */
@Component
public class HandlerHolder {
    // 一个hashMap维护发送渠道与handler的对应关系
    private final Map<Integer, Handler> handlers = new HashMap<>(128);

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }
}
