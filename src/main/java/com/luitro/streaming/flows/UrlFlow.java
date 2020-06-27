package com.luitro.streaming.flows;

import com.luitro.streaming.model.Message;
import com.luitro.streaming.model.Url;
import com.luitro.streaming.utils.Constants;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;

public class UrlFlow {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = Constants.SIMPLE_DATE_FORMAT;

    public static DataStream<Url> getUrls(DataStream<Message> messages) {

        DataStream<Url> urls= messages.flatMap(new FlatMapFunction<Message, Url>() {
            @Override
            public void flatMap(Message value, Collector<Url> out) throws Exception {
                value.getEntities().getUrls().forEach(out::collect);
            }
        });

        return urls.map(new MapFunction<Url, Url>() {
            @Override
            public Url map(Url value) {
                try {
                    value.setCreated(new Date().toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    return value;
                }catch (Exception ex){
                    return null;
                }

            }
        }).filter(new FilterFunction<Url>() {
            @Override
            public boolean filter(Url value) throws Exception {
                return value != null;
            }
        });


    }
}
