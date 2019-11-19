package com.stackroute.timeseriesconversationservice.controller;

import com.stackroute.timeseriesconversationservice.service.ConversationService;
import com.stackroute.timeseriesconversationservice.domain.Conversation;
import com.stackroute.timeseriesconversationservice.domain.TimeRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class ConversationController {

    private ConversationService conversationService;

    @Autowired
    public ConversationController (ConversationService conversationService){
        this.conversationService = conversationService;
    }

    @PostMapping("/saveconversation")
    public ResponseEntity<String> saveConvo(@RequestBody Conversation conversation) {

        conversationService.saveConversation(conversation);

        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @PostMapping("/getconversation")
    public ResponseEntity<List<Conversation>> getConvo(@RequestBody TimeRange timeRange){
        List<Conversation> conversations = new ArrayList<>();

        try {
            conversations = conversationService.getConversation(timeRange);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(conversations,HttpStatus.ACCEPTED);
    }

}
