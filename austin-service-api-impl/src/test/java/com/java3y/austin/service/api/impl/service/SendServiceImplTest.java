package com.java3y.austin.service.api.impl.service;

import ch.qos.logback.core.joran.conditional.ThenAction;
import com.java3y.austin.support.pipeline.BusinessProcess;
import com.java3y.austin.support.pipeline.ProcessController;
import com.java3y.austin.support.pipeline.ProcessTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class SendServiceImplTest {

    @Spy
    private ProcessController processController;

    @Mock
    private Map<String, ProcessTemplate> templateConfig;

    @Spy
    private ProcessTemplate processTemplate;

    @Mock
    private BusinessProcess businessProcess;

    @InjectMocks
    private SendServiceImpl sendServiceImplUnderTest;

    @Test
    void testSend() {

//        // params
//        final SendRequest sendRequest = new SendRequest("send", 1L,
//                new MessageParam("13711111111", new HashMap<>(), new HashMap<>()));
//
//        // predict result
//        final ProcessContext<SendTaskModel> processContext = new ProcessContext<>(sendRequest.getCode(), new SendTaskModel(), false, new BasicResultVO<>(
//                RespStatusEnum.SUCCESS, "data"));
//        final SendResponse expectedResult = new SendResponse(processContext.getResponse().getStatus(), processContext.getResponse().getMsg());
//
//
//        // stub
//        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
//        processTemplate.setProcessList(Arrays.asList(businessProcess));
//        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), processTemplate);
//
//        processController.setTemplateConfig(templateConfig);
//
//
//        // Run the test
//        final SendResponse result = sendServiceImplUnderTest.send(sendRequest);
//
//        // Verify the results
//        assertEquals(expectedResult, result);
    }

    @Test
    void testBatchSend() {
//        // Setup
//        final BatchSendRequest batchSendRequest = new BatchSendRequest("code", 0L,
//                Arrays.asList(new MessageParam("receiver", new HashMap<>(), new HashMap<>())));
//        final SendResponse expectedResult = new SendResponse("status", "msg");
//
//        // Configure ProcessController.process(...).
//        final ProcessContext processContext = new ProcessContext<>("code", null, false, new BasicResultVO<>(
//                RespStatusEnum.SUCCESS, "data"));
//        when(processController.process(new ProcessContext<>("code", null, false, new BasicResultVO<>(
//                RespStatusEnum.SUCCESS, "data")))).thenReturn(processContext);
//
//        // Run the test
//        final SendResponse result = sendServiceImplUnderTest.batchSend(batchSendRequest);
//
//        // Verify the results
//        assertEquals(expectedResult, result);
    }


    @Test
    void testMap() {
        LinkedHashMap<Integer, String> myHash = new LinkedHashMap<>();
        for (int i=0; i< 4; i++) {
            myHash.put(i, "word");
        }

        Set<Integer> it = myHash.keySet();
        for(Integer ele: it) {
            System.out.println("ele= "+ ele);
        }
    }

    class MyThread extends Thread {
        @Override
        public  void run() {
            for (int i=0; i< 100; i++){
                System.out.println("I'm "+i);
            }
        }
    };
    class MyRunnable implements Runnable {
        // 更常用
        @Override
        public  void run(){
            for (int i=0; i< 100; i++){
                System.out.println("Hel "+i);
            }
        }
    };
    @Test
    void testThread() {
        MyThread a1 = new MyThread();
        MyThread a2 = new MyThread();
        MyRunnable b1 = new MyRunnable();
        MyRunnable b2 = new MyRunnable();
        Thread a3 = new Thread(b1);
        Thread a4 = new Thread(b2);
        a1.start();
        a2.start();
        a3.start();
        a4.start();
    }
    
}
