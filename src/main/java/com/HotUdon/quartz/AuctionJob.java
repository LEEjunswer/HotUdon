package com.HotUdon.quartz;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AuctionJob implements Job {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        /*여기에 경매 종료 시  실행될 작업을 구현*/

    }
    private void updateAuctionStatusDatabase(){
        /*db에 종료되는 구현*/
    }
}
