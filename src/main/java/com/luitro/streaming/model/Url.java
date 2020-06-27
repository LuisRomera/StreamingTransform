package com.luitro.streaming.model;

public class Url {
    private String url;
    private String expanded_url;
    private String display_url;
    private int[] indices;
    private String created;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpanded_url() {
        return expanded_url;
    }

    public void setExpanded_url(String expanded_url) {
        this.expanded_url = expanded_url;
    }

    public String getDisplay_url() {
        return display_url;
    }

    public void setDisplay_url(String display_url) {
        this.display_url = display_url;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
