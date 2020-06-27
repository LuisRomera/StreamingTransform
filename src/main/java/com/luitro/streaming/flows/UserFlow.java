package com.luitro.streaming.flows;

import com.luitro.streaming.model.Message;
import com.luitro.streaming.model.User;
import com.luitro.streaming.utils.Constants;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.util.Collector;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserFlow {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = Constants.SIMPLE_DATE_FORMAT;

    public static DataStream<User> getUsers(DataStream<Message> messages) {
        DataStream<User> users = messages.flatMap(new FlatMapFunction<Message, User>() {
            @Override
            public void flatMap(Message value, Collector<User> out) throws Exception {
                List<User> users = new ArrayList<>();
                users.add(value.getUser());
                if (value.getRetweeted_status() != null && value.getRetweeted_status().getUser() != null)
                    users.add(value.getRetweeted_status().getUser());
                users.forEach(out::collect);
            }
        });


        return users.map(new MapFunction<User, User>() {
            @Override
            public User map(User value) {
                try {
                    value.setRegistered(SIMPLE_DATE_FORMAT.parse(value.getCreated_at()).toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    value.setCreated(new Date().toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    return value;
                }catch (Exception ex){
                    return null;
                }

            }
        }).filter(new FilterFunction<User>() {
            @Override
            public boolean filter(User value) throws Exception {
                return value != null;
            }
        });
    }
}
