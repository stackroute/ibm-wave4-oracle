//package com.stackroute.botservice.config;
//
//import com.stackroute.botservice.domain.Query;
//import com.stackroute.botservice.domain.UserQuery;
//import com.stackroute.botservice.service.QueryServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///* Created on : 03/04/2019 by Subhajit Pal (@rahzex) */
//
///* Seed data from csv file, seeded into mongodb database every time on start up. */
//
//@Component
//class StartupSeedData implements ApplicationListener<ContextRefreshedEvent> {
//
//    private QueryServiceImpl queryServiceImpl;
//
//    @Autowired
//    public StartupSeedData(QueryServiceImpl queryServiceImpl){
//        this.queryServiceImpl = queryServiceImpl;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        UserQuery user
//        Query = new UserQuery();
//        Query query = new Query();
//
//        String line = "";
//        Path filePath = Paths.get("data.csv");
//
//
//        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath.toUri()).getAbsolutePath()))) {
//
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] quesAndAns = line.split(",");
//
//                // initializing "Query" and "UserQuery" objects
//                query.setId(quesAndAns[0]);
//                query.setConcept(quesAndAns[1]);
//                query.setQuestion(quesAndAns[2]);
//
//                StringBuilder answer = new StringBuilder();
//                for(int i = 3; i < quesAndAns.length ;i++){
//                    answer.append(quesAndAns[i]);
//                }
//                query.setAnswer(answer.toString());
//
//                userQuery.setQuery(query);
//                userQuery.setStatus(new Status(true,true));
//
//                // saving initialized object into mongodb
//                queryServiceImpl.saveQuery(userQuery);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
