package com.webapp.core;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * Created by yxhe on 2017/12/5.
 */
@Component
public class Runner implements CommandLineRunner {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Runner.class);


    @Override
    public void run(String... strings) throws Exception {
        scheduler();
    }

    @Scheduled(cron="0 0 11 * * ?")
    public void scheduler() throws Exception {
        logger.info("0000");
    }

}