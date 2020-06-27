package com.luitro.streaming.config;


import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import com.typesafe.config.Config;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public class TwitterConfig {
    private final Config config;
    private final TwitterSource twitterSource;

    public TwitterConfig(Config config) {
        this.config = config;
        this.twitterSource = new TwitterSource(createTwitterProperties());
        List<String> ketwords = this.config.getStringList("keywords");
        TweetFilter customFilterInitializer = new TweetFilter(ketwords);
        twitterSource.setCustomEndpointInitializer(customFilterInitializer);
    }
    public static class TweetFilter implements TwitterSource.EndpointInitializer, Serializable {
        private final List<String> keywords;

        public TweetFilter(List<String> keywords) {
            this.keywords = keywords;
        }

        @Override
        public StreamingEndpoint createEndpoint() {
            StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
            endpoint.trackTerms(keywords);
            return endpoint;
        }


    }

    private Properties createTwitterProperties() {
        Properties twitterCredentials = new Properties();
        twitterCredentials.setProperty(TwitterSource.CONSUMER_KEY, config.getString("twitter.CONSUMER_KEY"));
        twitterCredentials.setProperty(TwitterSource.CONSUMER_SECRET, config.getString("twitter.CONSUMER_SECRET"));
        twitterCredentials.setProperty(TwitterSource.TOKEN, config.getString("twitter.TOKEN"));
        twitterCredentials.setProperty(TwitterSource.TOKEN_SECRET, config.getString("twitter.TOKEN_SECRET"));
        return twitterCredentials;
    }

    public TwitterSource getTwitterSource() {
        return this.twitterSource;
    }
}
