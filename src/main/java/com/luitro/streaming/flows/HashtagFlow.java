package com.luitro.streaming.flows;

import com.luitro.streaming.model.Hashtag;
import com.luitro.streaming.model.Message;
import com.luitro.streaming.utils.Constants;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;

public class HashtagFlow {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = Constants.SIMPLE_DATE_FORMAT;

    public static DataStream<Hashtag> getHashtags(DataStream<Message> messages) {

        DataStream<Hashtag> hashtags= messages.flatMap(new FlatMapFunction<Message, Hashtag>() {
            @Override
            public void flatMap(Message value, Collector<Hashtag> out) throws Exception {
                value.getEntities().getHashtags().forEach(out::collect);
            }
        });

        return hashtags.map(new MapFunction<Hashtag, Hashtag>() {
            @Override
            public Hashtag map(Hashtag value) {
                try {
                    value.setCreated(new Date().toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    return value;
                }catch (Exception ex){
                    return null;
                }

            }
        }).filter(new FilterFunction<Hashtag>() {
            @Override
            public boolean filter(Hashtag value) throws Exception {
                return value != null;
            }
        });


    }
}
