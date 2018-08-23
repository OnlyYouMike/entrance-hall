package com.aifenxiang.entrancehall.config;

import com.aifenxiang.pigeon.autoconfigartion.AiFenXIangMailProperties;
import com.aifenxiang.pigeon.autoconfigartion.AiFenXiangMailService;
import com.aifenxiang.pigeon.service.EmailSendService;
import com.aifenxiang.pigeon.service.TemplateService;
import com.aifenxiang.pigeon.service.impl.SendAttachmentMailServiceImpl;
import com.aifenxiang.pigeon.service.impl.SendSimpleMailServiceImpl;
import com.aifenxiang.pigeon.service.impl.SendTemplateMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author: zj
 * @create: 2018-08-24 01:29
 **/
@Configuration
@EnableConfigurationProperties
public class MailBeanConfig {

    @Bean(value = "simpleMailSendService")
    public EmailSendService simpleMailSendService(){
        return new SendSimpleMailServiceImpl();
    }

    @Bean(value = "attachmentMailSendService")
    public EmailSendService attachmentMailSendService(){
        return new SendAttachmentMailServiceImpl();
    }

    @Bean(value = "templateMailSendService")
    public EmailSendService templateMailSendService(){
        return new SendTemplateMailServiceImpl();
    }
}
