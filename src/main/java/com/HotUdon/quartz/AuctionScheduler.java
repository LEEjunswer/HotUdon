package com.HotUdon.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class AuctionScheduler {

    public static void main(String[] args) throws SchedulerException {
        //스케줄 팩토리 구현
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        
        //JobDetail 생성
        JobDetail jobDetail = JobBuilder.newJob(AuctionJob.class)
                .withIdentity("auctionEndJob","auctionGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("auctionEndJob","auctionGroup")
                // 이걸로 시간이나 날짜를 설정
                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(0, 0))
                .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

    }
}
