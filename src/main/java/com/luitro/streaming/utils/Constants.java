package com.luitro.streaming.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Constants {
    public static final String TWITTER_FORMAT = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(TWITTER_FORMAT, Locale.ENGLISH);
}
