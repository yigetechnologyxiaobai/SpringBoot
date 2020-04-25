package com.cyy.timing.task;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Configuration
public class DynamicScheduleTask implements SchedulingConfigurer {

    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    /**
     * 执行定时任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1、添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务：" + LocalDateTime.now().toLocalTime()),
                //设置执行周期(Trigger)
                triggerContext -> {
                    //从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    //合法性校验
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code
                    }
                    //返回执行周期（Date）
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
