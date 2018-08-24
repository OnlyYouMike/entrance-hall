package com.aifenxiang.entrancehall;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zj
 * @create: 2018-08-24 01:40
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EntranceHallApplication.class})
@Configuration
public class EntranceHallTest {
}
