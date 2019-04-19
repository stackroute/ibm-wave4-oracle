package com.stackroute.manualservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Query {


    private String id;
    private String question;
    private String answer;
}
