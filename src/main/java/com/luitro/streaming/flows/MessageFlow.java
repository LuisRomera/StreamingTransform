package com.luitro.streaming.flows;

import com.google.gson.Gson;
import com.luitro.streaming.model.Message;
import com.luitro.streaming.model.Geolocation;
import com.luitro.streaming.utils.Constants;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;

public class MessageFlow {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = Constants.SIMPLE_DATE_FORMAT;

    public static DataStream<Message> processMessages(DataStream<String> input) {
        DataStream<Message> map = input.map(new MapFunction<String, Message>() {
            @Override
            public Message map(String value) throws Exception {
                Gson gson = new Gson();
                try {
                    Message message = gson.fromJson(value, Message.class);
                    if (gson.fromJson(value, Message.class).getGeo() != null)
                        message.setGeolocation(new Geolocation(message.getGeo().getCoordinates().get(0), message.getGeo().getCoordinates().get(1)));

                    if (message.getPlace() != null) {
                        double lon = message.getPlace().getBounding_box().getCoordinates().get(0).get(0).get(0) +
                                Math.abs(message.getPlace().getBounding_box().getCoordinates().get(0).get(0).get(0) -
                                        message.getPlace().getBounding_box().getCoordinates().get(0).get(2).get(0)) / 2;
                        double lat = message.getPlace().getBounding_box().getCoordinates().get(0).get(0).get(1)
                                + Math.abs(message.getPlace().getBounding_box().getCoordinates().get(0).get(0).get(1) -
                                message.getPlace().getBounding_box().getCoordinates().get(0).get(2).get(1)) / 2;
                        message.setLocation(lat +","+ lon);
                    }


                    return message;
                } catch (Exception e) {
                    return null;
                }
            }
        }).filter(new FilterFunction<Message>() {
            @Override
            public boolean filter(Message value) throws Exception {
                return value != null;
            }
        });

        DataStream<Message> filterMessages = map.filter(new FilterFunction<Message>() {
            @Override
            public boolean filter(Message value) throws Exception {
                return value != null && value.getLang() != null && value.getLang().equals("es") && value.getText() != null;
            }
        });

        return filterMessages.map(new MapFunction<Message, Message>() {
            @Override
            public Message map(Message value) {
                try {
                    value.setCreated(SIMPLE_DATE_FORMAT.parse(value.getCreated_at()).toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    return value;
                } catch (Exception ex) {
                    return null;
                }
            }
        }).filter(new FilterFunction<Message>() {
            @Override
            public boolean filter(Message value) throws Exception {
                return value != null;
            }
        });


    }
}
