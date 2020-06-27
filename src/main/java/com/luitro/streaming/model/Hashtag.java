package com.luitro.streaming.model;

import java.util.Arrays;

public class Hashtag {
    private String text;
    private int[] indices;
    private String created;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Override
    public String toString() {
        return "Hashtag{" +
                "text='" + text + '\'' +
                ", indices=" + Arrays.toString(indices) +
                ", created='" + created + '\'' +
                '}';
    }
}
