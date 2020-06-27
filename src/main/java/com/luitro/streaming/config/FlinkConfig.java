package com.luitro.streaming.config;

import com.typesafe.config.Config;
import org.apache.flink.api.java.ExecutionEnvironment;

public class FlinkConfig {

    private ExecutionEnvironment executionEnvironment;

    public FlinkConfig(Config config){
        this.executionEnvironment = ExecutionEnvironment.createCollectionsEnvironment();
    }

    public ExecutionEnvironment getExecutionEnvironment(){
        return this.executionEnvironment;
    }
}
