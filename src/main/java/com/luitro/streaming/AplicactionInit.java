package com.luitro.streaming;


import com.luitro.streaming.services.FlowService;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AplicactionInit {

    private static final Logger log = LoggerFactory.getLogger(AplicactionInit.class);

    public static void main(String[] args) throws Exception {

        Config config = ConfigFactory.parseResources(args[0]);

        log.info("Init flink {}", config.getString("name"));
        new FlowService(config).startJob();
    }

}
