package com.aifenxiang.entrancehall.task;

import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: zj
 * @create: 2018-08-24 02:49
 **/
@Component
public class TaskTest {


    @Autowired
    private EmailSendService templateMailSendService;
    @Autowired
    private EmailSendService attachmentMailSendService;
    @Autowired
    private EmailSendService simpleMailSendService;

    @Scheduled(fixedDelay = 3000)
    private void start(){
        EmailApplication emailApplication = new EmailApplication();
        emailApplication.setRecipientMail("mynameispanhao@163.com");
        emailApplication.setTitle("testSimpleEmailSend");
        emailApplication.setContext("testSuc");
        simpleMailSendService.sendMessage(emailApplication);
    }

}
