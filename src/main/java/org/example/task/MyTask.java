package org.example.task;

import org.example.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.ejb.Schedule;
@Component
public class MyTask {
    @Autowired
    private AdminMapper adminMapper;

    @Scheduled(cron = "59 59 23 * * ?")//秒 分 时 日 月 星期 （年可省略）
    public void initPasswordErrorCount(){
        adminMapper.initPasswordErrorCount();
    }
}
