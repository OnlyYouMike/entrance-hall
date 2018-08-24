package com.aifenxiang.entrancehall.task;

import com.aifenxiang.pigeon.concurrent.SendMailUtil;
import com.aifenxiang.pigeon.server.EmailApplication;
import com.aifenxiang.pigeon.service.EmailSendService;
import com.aifenxiang.pigeon.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: zj
 * @create: 2018-08-24 15:12
 **/
@Component
public class Taskhandler {


    @Autowired
    @Qualifier("templateMailSendService")
    private EmailSendService  templateMailSendService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private SendMailUtil sendMailUtil;


    @Scheduled(fixedDelay = 1000000)
    public void test(){
        for (int i = 0;i<100;i++){
            EmailApplication emailApplication = new EmailApplication();
            emailApplication.setTitle("标题test"+i);
            emailApplication.setTemplateService(templateService);
            int ii = i;
            emailApplication.setContextTemplate(new HashMap<String,Object>(){
                {
                    put("verifyCode",ii);
                }
            });
            emailApplication.setRecipientMail("mynameispanhao@163.com");
            emailApplication.setEmailSendService(templateMailSendService);
            sendMailUtil.send(emailApplication);
        }


    }


}
