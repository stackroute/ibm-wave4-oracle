package com.stackroute.timeseriesconversationservice.service;

import com.stackroute.timeseriesconversationservice.domain.Conversation;
import com.stackroute.timeseriesconversationservice.domain.TimeRange;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ConversationService {

    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdb.database}")
    private String dbName;

    @Autowired
    public ConversationService(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    public void saveConversation(Conversation conversation) {
        influxDBTemplate.createDatabase();
        final Point p = Point.measurement(conversation.getUserName() + "Conversation")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("Bot", conversation.getBot())
                .addField("Human", conversation.getUser())
                .build();
        influxDBTemplate.write(p);
    }

    public List<Conversation> getConversation(TimeRange timeRange) throws ParseException {
        List<Conversation> conversationList = new ArrayList<>();

        String toDateString = timeRange.getToDate();
        String fromDateString = timeRange.getFromDate();

        System.out.println(fromDateString);
        System.out.println(toDateString);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date toDate1 = dateFormat.parse(toDateString);
        Date fromDate1 = dateFormat.parse(fromDateString);

        System.out.println("fromDate :" + fromDate1);
        System.out.println("toDate :" + toDate1);

        long toUnixTime = toDate1.getTime() * 1000000;
        long fromUnixTime = fromDate1.getTime() * 1000000;

        System.out.println(fromUnixTime);
        System.out.println(toUnixTime);

        System.out.println(" database : " + dbName);

        Query query = new Query("select time, Bot, Human from " + timeRange.getUserName() + "Conversation" + " where time >= " + fromUnixTime + " and time <= " + toUnixTime, dbName);

        QueryResult queryResult = influxDBTemplate.query(query);

        //System.out.println(queryResult.getResults().get(0).getSeries().get(0).getValues().get(0).get(1).toString());

        if (queryResult.getResults().get(0).getSeries()!=null) {

            int rowSize = queryResult.getResults().get(0).getSeries().get(0).getValues().size();
            System.out.println("Size is : " + rowSize);

            for (int rowIndex = 0; rowIndex<rowSize; rowIndex++) {
                conversationList.add(new Conversation(timeRange.getUserName(),
                        queryResult.getResults().get(0).getSeries().get(0).getValues().get(rowIndex).get(0).toString(),
                        queryResult.getResults().get(0).getSeries().get(0).getValues().get(rowIndex).get(1).toString(),
                        queryResult.getResults().get(0).getSeries().get(0).getValues().get(rowIndex).get(2).toString()
                ));
            }
        }

        if (conversationList.isEmpty())
            conversationList.add(new Conversation(timeRange.getUserName(),"00-00-00","No Data Available","No Data Available"));

        return conversationList;

    }
}