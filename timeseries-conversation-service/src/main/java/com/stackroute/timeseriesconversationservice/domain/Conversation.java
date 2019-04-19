package com.stackroute.timeseriesconversationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conversation {
    private String userName;
    private String time;
    private String user;
    private String bot;
}
