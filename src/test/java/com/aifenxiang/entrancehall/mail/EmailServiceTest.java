package com.aifenxiang.entrancehall.mail;

import com.aifenxiang.entrancehall.EntranceHallTest;
import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: zj
 * @create: 2018-08-24 01:44
 **/
public class EmailServiceTest extends EntranceHallTest {

    @Autowired
    private EmailSendService templateMailSendService;
    @Autowired
    private EmailSendService attachmentMailSendService;
    @Autowired
    private EmailSendService simpleMailSendService;

    @Test
    public void simpleEmailSend(){
        EmailApplication emailApplication = new EmailApplication();
        emailApplication.setRecipientMail("mynameispanhao@163.com");
        emailApplication.setTitle("testSimpleEmailSend");
        emailApplication.setContext("testSuc");
        simpleMailSendService.sendMessage(emailApplication);
    }


}
