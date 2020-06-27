package com.luitro.streaming.flows;

import com.luitro.streaming.model.Message;
import com.luitro.streaming.model.Place;
import com.luitro.streaming.utils.Constants;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.Date;

public class PlaceFlow {
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = Constants.SIMPLE_DATE_FORMAT;

    public static DataStream<Place> getPlaces(DataStream<Message> messages) {

        DataStream<Place> places= messages.map(new MapFunction<Message, Place>() {
            @Override
            public Place map(Message value) throws Exception {
                return value.getPlace();
            }

        });

        return places.map(new MapFunction<Place, Place>() {
            @Override
            public Place map(Place value) {
                try {
                    value.setCreated(new Date().toInstant()
                            .atOffset(ZoneOffset.UTC).toString());
                    return value;
                }catch (Exception ex){
                    return null;
                }

            }
        }).filter(new FilterFunction<Place>() {
            @Override
            public boolean filter(Place value) throws Exception {
                return value != null;
            }
        });


    }
}
