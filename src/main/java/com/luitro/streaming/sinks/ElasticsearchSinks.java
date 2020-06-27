package com.luitro.streaming.sinks;

import com.google.gson.Gson;
import com.luitro.streaming.model.*;
import com.typesafe.config.Config;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;

import java.util.Map;

public class ElasticsearchSinks {


    /**
     * Messages ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<Message> getEsSinkBuilderMessages(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<Message> esSinkBuilder = new ElasticsearchSink.Builder<Message>(
                httpHosts,
                new ElasticsearchSinkFunction<Message>() {
                    public IndexRequest createIndexRequest(Message element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        json.put("source", "twitter");
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.messages.name"))
                                .type(config.getString("elastic.index.messages.type"))
                                .source(json);
                    }

                    @Override
                    public void process(Message data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }

    /**
     * Users ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<User> getEsSinkBuilderUsers(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<User> esSinkBuilder = new ElasticsearchSink.Builder<User>(
                httpHosts,
                new ElasticsearchSinkFunction<User>() {
                    public IndexRequest createIndexRequest(User element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.users.name"))
                                .type(config.getString("elastic.index.users.type"))
                                .source(json);
                    }

                    @Override
                    public void process(User data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }
    /**
     * Hashtags ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<Hashtag> getEsSinkBuilderHashtags(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<Hashtag> esSinkBuilder = new ElasticsearchSink.Builder<Hashtag>(
                httpHosts,
                new ElasticsearchSinkFunction<Hashtag>() {
                    public IndexRequest createIndexRequest(Hashtag element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.hashtags.name"))
                                .type(config.getString("elastic.index.hashtags.type"))
                                .source(json);
                    }

                    @Override
                    public void process(Hashtag data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }
    /**
     * Mentions ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<User> getEsSinkBuilderMentions(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<User> esSinkBuilder = new ElasticsearchSink.Builder<User>(
                httpHosts,
                new ElasticsearchSinkFunction<User>() {
                    public IndexRequest createIndexRequest(User element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.mentions.name"))
                                .type(config.getString("elastic.index.mentions.type"))
                                .source(json);
                    }

                    @Override
                    public void process(User data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }

    /**
     * Places ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<Place> getEsSinkBuilderPlaces(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<Place> esSinkBuilder = new ElasticsearchSink.Builder<Place>(
                httpHosts,
                new ElasticsearchSinkFunction<Place>() {
                    public IndexRequest createIndexRequest(Place element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.places.name"))
                                .type(config.getString("elastic.index.places.type"))
                                .source(json);
                    }

                    @Override
                    public void process(Place data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }

    /**
     * Urls ElasticSearch sink
     * @param config
     * @return
     */
    public static ElasticsearchSink.Builder<Url> getEsSinkBuilderUrls(Config config) {
        final java.util.List<org.apache.http.HttpHost> httpHosts = new ArrayList<HttpHost>();
        httpHosts.add(new HttpHost(config.getString("elastic.host.ip"), config.getInt("elastic.host.port"), config.getString("elastic.host.protocol")));

        ElasticsearchSink.Builder<Url> esSinkBuilder = new ElasticsearchSink.Builder<Url>(
                httpHosts,
                new ElasticsearchSinkFunction<Url>() {
                    public IndexRequest createIndexRequest(Url element) {
                        Gson gson = new Gson();
                        Map<String, Object> json = gson.fromJson(gson.toJson(element), Map.class);
                        return Requests.indexRequest()
                                .index(config.getString("elastic.index.urls.name"))
                                .type(config.getString("elastic.index.urls.type"))
                                .source(json);
                    }

                    @Override
                    public void process(Url data, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {
                        requestIndexer.add(createIndexRequest(data));
                    }
                }
        );
        esSinkBuilder.setBulkFlushMaxActions(1);
        return esSinkBuilder;
    }
}
