package com.luitro.streaming.model;

import java.io.Serializable;
import java.util.List;

public class Message implements Serializable {
    private String text;
    private Long id;
    private String id_str;
    private String source;
    private Long timestamp_ms;
    private int[] display_text_range;
    private Boolean truncated;
    private Long in_reply_to_status_id;
    private String in_reply_to_status_id_str;
    private String in_reply_to_user_id;
    private String in_reply_to_screen_name;
    private Long quote_count;
    private Long reply_count;
    private Long retweet_count;
    private Long favorite_count;
    private String lang;
    private User user;
    private Message retweeted_status;
    private List<User> user_mentions;
    private Entities entities;
    private Coordinates coordinates;
    private String location;
    private Geo geo;
    private Geolocation geolocation;
    private Place place;
    private String created_at;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String created;

//    private Date created;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getTimestamp_ms() {
        return timestamp_ms;
    }

    public void setTimestamp_ms(Long timestamp_ms) {
        this.timestamp_ms = timestamp_ms;
    }

    public int[] getDisplay_text_range() {
        return display_text_range;
    }

    public void setDisplay_text_range(int[] display_text_range) {
        this.display_text_range = display_text_range;
    }

    public Boolean getTruncated() {
        return truncated;
    }

    public void setTruncated(Boolean truncated) {
        this.truncated = truncated;
    }

    public Long getIn_reply_to_status_id() {
        return in_reply_to_status_id;
    }

    public void setIn_reply_to_status_id(Long in_reply_to_status_id) {
        this.in_reply_to_status_id = in_reply_to_status_id;
    }

    public String getIn_reply_to_status_id_str() {
        return in_reply_to_status_id_str;
    }

    public void setIn_reply_to_status_id_str(String in_reply_to_status_id_str) {
        this.in_reply_to_status_id_str = in_reply_to_status_id_str;
    }

    public String getIn_reply_to_user_id() {
        return in_reply_to_user_id;
    }

    public void setIn_reply_to_user_id(String in_reply_to_user_id) {
        this.in_reply_to_user_id = in_reply_to_user_id;
    }

    public String getIn_reply_to_screen_name() {
        return in_reply_to_screen_name;
    }

    public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
        this.in_reply_to_screen_name = in_reply_to_screen_name;
    }

    public Long getQuote_count() {
        return quote_count;
    }

    public void setQuote_count(Long quote_count) {
        this.quote_count = quote_count;
    }

    public Long getReply_count() {
        return reply_count;
    }

    public void setReply_count(Long reply_count) {
        this.reply_count = reply_count;
    }

    public Long getRetweet_count() {
        return retweet_count;
    }

    public void setRetweet_count(Long retweet_count) {
        this.retweet_count = retweet_count;
    }

    public Long getFavorite_count() {
        return favorite_count;
    }

    public void setFavorite_count(Long favorite_count) {
        this.favorite_count = favorite_count;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Geolocation geolocation) {
        this.geolocation = geolocation;
    }

    public Message getRetweeted_status() {
        return retweeted_status;
    }

    public void setRetweeted_status(Message retweeted_status) {
        this.retweeted_status = retweeted_status;
    }

    public List<User> getUser_mentions() {
        return user_mentions;
    }

    public void setUser_mentions(List<User> user_mentions) {
        this.user_mentions = user_mentions;
    }

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
