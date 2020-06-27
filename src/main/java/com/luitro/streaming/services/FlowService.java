package com.luitro.streaming.services;

import com.luitro.streaming.config.TwitterConfig;
import com.luitro.streaming.flows.MessageFlow;
import com.luitro.streaming.model.*;
import com.luitro.streaming.sinks.ElasticsearchSinks;
import com.typesafe.config.Config;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.luitro.streaming.flows.HashtagFlow.getHashtags;
import static com.luitro.streaming.flows.MentionsFlow.getMentions;
import static com.luitro.streaming.flows.PlaceFlow.getPlaces;
import static com.luitro.streaming.flows.UrlFlow.getUrls;
import static com.luitro.streaming.flows.UserFlow.getUsers;


public class FlowService {

    private Config config;
    public FlowService(Config config){
        this.config = config;
    }

    private static final Logger log = LoggerFactory.getLogger(FlowService.class);

    public void startJob() throws Exception {
        log.info("************ Init job ************");

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        log.info("Running {} .....", config.getString("name"));
        DataStream<String> input = env.addSource(new TwitterConfig(config).getTwitterSource());

        DataStream<Message> messages = MessageFlow.processMessages(input);
        DataStream<User> users = getUsers(messages);
        DataStream<Hashtag> hashtags = getHashtags(messages);
        DataStream<User> mentions = getMentions(messages);
        DataStream<Place> places = getPlaces(messages);
        DataStream<Url> urls = getUrls(messages);

        messages.addSink(ElasticsearchSinks.getEsSinkBuilderMessages(config).build());
        users.addSink(ElasticsearchSinks.getEsSinkBuilderUsers(config).build());
        hashtags.addSink(ElasticsearchSinks.getEsSinkBuilderHashtags(config).build());
        mentions.addSink(ElasticsearchSinks.getEsSinkBuilderMentions(config).build());
        places.addSink(ElasticsearchSinks.getEsSinkBuilderPlaces(config).build());
        urls.addSink(ElasticsearchSinks.getEsSinkBuilderUrls(config).build());

        env.execute("Twitter Analysis");

    }



}
