package com.luitro.streaming.model;

import java.util.List;

public class Entities {

    private List<Url> urls;
    private List<Hashtag> hashtags;
    private List<User> user_mentions;

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }

    public List<User> getUser_mentions() {
        return user_mentions;
    }

    public void setUser_mentions(List<User> user_mentions) {
        this.user_mentions = user_mentions;
    }
}
