package com.java3y.austin.handler.action;

import cn.hutool.core.collection.CollUtil;
import com.java3y.austin.common.constant.CommonConstant;
import com.java3y.austin.common.domain.TaskInfo;
import com.java3y.austin.common.enums.DeduplicationType;
import com.java3y.austin.common.enums.EnumUtil;
import com.java3y.austin.common.pipeline.BusinessProcess;
import com.java3y.austin.common.pipeline.ProcessContext;
import com.java3y.austin.handler.deduplication.DeduplicationHolder;
import com.java3y.austin.handler.deduplication.DeduplicationParam;
import com.java3y.austin.support.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * 去重服务
 * 1. 根据相同内容N分钟去重（SlideWindowLimitService）
 * 2. 相同的渠道一天内频次去重（SimpleLimitService）
 *
 * 本质是： 基于redis，在一段时间内发送次数超过N次， 构建不同key=channel_receiver  和 templateId_content_receiver
 * 然后在taskInfo.remove(receiver), 如果receiver为空，直接setNeedBreak(true)
 */
@Service
public class DeduplicationAction implements BusinessProcess<TaskInfo> {

    public static final String DEDUPLICATION_RULE_KEY = "deduplicationRule";

    @Autowired
    private ConfigService config;

    @Autowired
    private DeduplicationHolder deduplicationHolder;

    @Override
    public void process(ProcessContext<TaskInfo> context) {
        TaskInfo taskInfo = context.getProcessModel();

        // 配置样例{"deduplication_10":{"num":1,"time":300},"deduplication_20":{"num":5}}
        String deduplicationConfig = config.getProperty(DEDUPLICATION_RULE_KEY, CommonConstant.EMPTY_JSON_OBJECT);

        // 去重
        List<Integer> deduplicationList = EnumUtil.getCodeList(DeduplicationType.class);
        for (Integer deduplicationType : deduplicationList) {
            DeduplicationParam deduplicationParam = deduplicationHolder.selectBuilder(deduplicationType).build(deduplicationConfig, taskInfo);
            if (Objects.nonNull(deduplicationParam)) {
                deduplicationHolder.selectService(deduplicationType).deduplication(deduplicationParam);
            }
        }

        if (CollUtil.isEmpty(taskInfo.getReceiver())) {
            context.setNeedBreak(true);
        }
    }
}
